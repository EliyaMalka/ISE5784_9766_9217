package renderer;

import primitives.*;
import scene.*;
import static geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import static primitives.Util.*;

/**
 * The SimpleRayTracer class extends the RayTracerBase and implements a basic
 * ray tracing algorithm. It calculates the color at the intersection points of
 * rays with scene geometries.
 */
public class SimpleRayTracer extends RayTracerBase {

	/**
	 * constant for the amount of ray head movement for shading rays
	 */
	private static final double DELTA = 0.1;

	/**
	 * Constructs a new SimpleRayTracer with the specified scene.
	 *
	 * @param scene The scene to be rendered.
	 */
	public SimpleRayTracer(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		var closestPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
		return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
	}

	/**
	 * Calculates the color at a given point in the scene. Currently, it only
	 * returns the ambient light intensity.
	 *
	 * @param point The point at which to calculate the color.
	 * @param ray   The ray that intersected with the geometry.
	 * @return The color at the specified point.
	 */
	private Color calcColor(GeoPoint point, Ray ray) {
		return scene.ambientLight.getIntensity() //
				.add(calcLocalEffects(point, ray));
	}

	/**
	 * Calculates the local effects at an intersection point, such as diffuse and
	 * specular reflections.
	 *
	 * @param geoPoint The intersection point and associated geometry.
	 * @param ray      The ray that intersected with the geometry.
	 * @return The color due to local effects.
	 */
	private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
		Vector normalOfGeometry = geoPoint.geometry.getNormal(geoPoint.point);
		Vector directionAxis = ray.getDirection();
		double verticalFactor1 = alignZero(normalOfGeometry.dotProduct(directionAxis));
		if (verticalFactor1 == 0)
			return Color.BLACK;
		Material material = geoPoint.geometry.getMaterial();
		Color color = geoPoint.geometry.getEmission();
		for (LightSource lightSource : scene.lights) {
			Vector lightSourceVector = lightSource.getL(geoPoint.point);
			double verticalFactor2 = alignZero(normalOfGeometry.dotProduct(lightSourceVector));
			if (verticalFactor2 * verticalFactor1 > 0) { // sign(verticalFactor1) == sign(verticalFactor2)
				Color colorLightSource = lightSource.getIntensity(geoPoint.point);
				color = color.add(colorLightSource.scale(
						calcDiffusive(material.kD, verticalFactor2).add(calcSpecular(material.kS, lightSourceVector,
								normalOfGeometry, verticalFactor2, directionAxis, material.nShininess))));
			}
		}
		return color;

	}

	/**
	 * Calculates the diffusive reflection at an intersection point.
	 *
	 * @param kd The diffusive reflection coefficient of the geometry.
	 * @param nl The dot product of the normal vector and the light vector.
	 * @return The color due to diffusive reflection.
	 */
	private Double3 calcDiffusive(Double3 kd, double nl) {
		return kd.scale(Math.abs(nl));
	}

	/**
	 * Calculates the specular reflection at an intersection point.
	 *
	 * @param kS        The specular reflection coefficient of the geometry.
	 * @param l         The vector from the light source to the intersection point.
	 * @param n         The normal vector at the intersection point.
	 * @param nl        The dot product of the normal vector and the light vector.
	 * @param v         The vector from the intersection point to the camera.
	 * @param shininess The shininess of the geometry.
	 * @return The color due to specular reflection.
	 */
	private Double3 calcSpecular(Double3 kS, Vector l, Vector n, double nl, Vector v, int shininess) {
		Vector r = l.add(n.scale(-2 * nl));
		;
		double vr = -alignZero(v.dotProduct(r));
		if (vr <= 0)
			return Double3.ZERO;
		return kS.scale(Math.pow(vr, shininess));
	}

	/**
	 * Checks if a point is shaded by another geometry.
	 *
	 * @param geoPoint       The point to check if it is shaded.
	 * @param lightSource             The light source to check if it is shaded.
	 * @param lightDirection The direction of the light source.
	 * @param n              The normal vector at the intersection point.
	 * @return true if the point is not shaded, false otherwise.
	 */
	private boolean unshaded(GeoPoint geoPoint, LightSource lightSource, Vector lightDirection, Vector n) {
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		Point point = geoPoint.point.add(delta);
		Ray shadowRay = new Ray(point, lightDirection);
		var intersections = scene.geometries.findGeoIntersections(shadowRay);
		if (intersections == null)
			return true;
		double lightDistance = lightSource.getDistance(geoPoint.point);
		for (var geo : intersections) {
			if (alignZero(geo.point.distance(geoPoint.point) - lightDistance) <= 0)
				return false;
		}
		return true;
	}

		
    }
