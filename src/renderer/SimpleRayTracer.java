package renderer;

import java.util.List;
import primitives.*;
import scene.*;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;

import static primitives.Util.alignZero;

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
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);

		if (intersections == null) {
			return scene.background;
		}
		GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);

		return calcColor(closestPoint, ray);
	}

	/**
	 * Calculates the color at a given point in the scene. Currently, it only
	 * returns the ambient light intensity.
	 *
	 * @param point The point at which to calculate the color.
	 * @return The color at the specified point.
	 */
	private Color calcColor(GeoPoint point, Ray ray) {
		return scene.ambientLight.getIntensity().add(point.geometry.getEmission()).add(calcLocalEffects(point, ray));
	}

	/**
	 * Calculates the local effects at an intersection point, such as diffuse and
	 * specular reflections.
	 *
	 * @param point The intersection point and associated geometry.
	 * @param ray   The ray that intersected with the geometry.
	 * @return The color due to local effects.
	 */
	private Color calcLocalEffects(GeoPoint point, Ray ray) {
		Color color = Color.BLACK;
		Vector v = ray.getDirection();
		Vector n = point.geometry.getNormal(point.point);

		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;

		Material material = point.geometry.getMaterial();
		int shininess = material.nShininess;

		Double3 kd = material.kD;
		Double3 ks = material.kS;

		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(point.point);
			if (l == null)
				continue;
			double nl = alignZero(n.dotProduct(l));

			if (nl * nv > 0) { // sign(nl) == sign(nv)
				Color lightIntensity = lightSource.getIntensity(point.point);
				Double3 factor = calcDiffuse(kd, nl).add(calcSpecular(ks, l, n, nl, v, shininess));
				color = color.add(lightIntensity.scale(factor));
			}
		}
		return color;
	}

	private Double3 calcDiffuse(Double3 kd, double nl) {
		return kd.scale(Math.abs(nl));
	}

	private Double3 calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int shininess) {
		Vector r = l.subtract(n.scale(2 * nl));
		double vr = -alignZero(v.dotProduct(r));
		if (vr <= 0)
			return Double3.ZERO;
		return ks.scale(Math.pow(vr, shininess));
	}
}
