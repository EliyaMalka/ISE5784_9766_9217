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
	 * @param ray   The ray that intersected with the geometry.
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
				color = color.add(colorLightSource.scale(calcDiffusive(material.kD, verticalFactor2)
						.add(calcSpecular(material.kS, lightSourceVector, normalOfGeometry, verticalFactor2, directionAxis, material.nShininess))));
			}
		}
		return color;

	}

	private Double3 calcDiffusive(Double3 kd, double nl) {
		return kd.scale(Math.abs(nl));
	}

	private Double3 calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int shininess) {
		Vector r = l.add(n.scale(-2 * nl));;
		double vr = -alignZero(v.dotProduct(r));
		if (vr <= 0)
			return Double3.ZERO;
		return ks.scale(Math.pow(vr, shininess));
	}
	

}
