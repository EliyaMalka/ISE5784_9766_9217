package renderer;

import java.util.List;
import primitives.*;
import scene.*;
import geometries.Intersectable.GeoPoint;

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
		//TODO להשלים את הפונקציה על פי הקורס התיאורטי.
		return Color.BLACK;
	}
	
	/**
	 * Calculates the diffuse reflection at an intersection point.
	 *
	 * @param point The intersection point and associated geometry.
	 * @param ray   The ray that intersected with the geometry.
	 * @return The color due to diffuse reflection.
	 */
	private Color calcDiffuse(GeoPoint point, Ray ray) {
		//TODO להשלים את הפונקציה על פי הקורס התיאורטי.
		return Color.BLACK;	
	}
	
	/**
	 * Calculates the specular reflection at an intersection point.
	 *
	 * @param point The intersection point and associated geometry.
	 * @param ray   The ray that intersected with the geometry.
	 * @return The color due to specular reflection.
	 */
	private Color calcSpecular(GeoPoint point, Ray ray) {
		//TODO להשלים את הפונקציה על פי הקורס התיאורטי.
		return Color.BLACK;
	}
}
