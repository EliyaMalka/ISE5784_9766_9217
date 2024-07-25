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
			if (verticalFactor2 * verticalFactor1 > 0 && unshaded(geoPoint, lightSourceVector, normalOfGeometry, verticalFactor2, lightSource)) { // sign(verticalFactor1 // == // sign(verticalFactor2)
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
     * Determines if a given point on a geometry is unshaded by checking for intersections between
     * the point and a light source.
     *
     * @param geoPoint the geometric point on the geometry being tested
     * @param lightDirection the direction vector from the point to the light source
     * @param normalVector the normal vector at the point on the geometry
     * @param dotProduct the dot product of the normal vector and the light direction vector
     * @param lightSource the light source being considered
     * @return {@code true} if the point is unshaded (i.e., no objects block the light); {@code false} otherwise
     */
	private boolean unshaded(GeoPoint geoPoint, Vector lightDirection  , Vector normalVector, double dotProduct, LightSource lightSource) {
		Point point = geoPoint.point.add(normalVector.scale(alignZero(dotProduct) > 0 ? DELTA : -DELTA));
		Ray lightRay = new Ray(point, lightDirection);
		var intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)
			return true;
		double lightDistance = lightSource.getDistance(geoPoint.point);
		for (GeoPoint gp : intersections) {
			if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0 && gp.geometry.getMaterial().nShininess == 0)
				return false;
		}
		return true;
	}
}
