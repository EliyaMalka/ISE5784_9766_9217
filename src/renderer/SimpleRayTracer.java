package renderer;

import primitives.*;
import scene.*;
import  geometries.*;
import geometries.Intersectable.GeoPoint;
import lighting.*;
import static primitives.Util.*;

import java.util.List;

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
	 * constant for the maximum number of levels of recursion for the calculation of
	 * the color
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/**
	 * constant for the minimum amount of color to be calculated
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;

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
	 * Calculates the color at a given point in the scene. Currently, it only
	 * returns the ambient light intensity.
	 *
	 * @param point  The point at which to calculate the color.
	 * @param ray The ray that intersected with the geometry.
	 * @return The color at the specified point.
	 */
	private Color calcColor(GeoPoint point, Ray ray) {

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
			// sign(verticalFactor1 == sign(verticalFactor2)
			if (verticalFactor2 * verticalFactor1 > 0
					&& unshaded(geoPoint, lightSource, lightSourceVector, normalOfGeometry, verticalFactor2)) {
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
		double minusVR = -alignZero(v.dotProduct(r));
		return minusVR <= 0 ? Double3.ZERO : kS.scale(Math.pow(minusVR, shininess));
	}

	/**
	 * Determines if a given point on a geometry is unshaded by checking for
	 * intersections between the point and a light source.
	 *
	 * @param geoPoint       the geometric point on the geometry being tested
	 * @param light          the light source being considered
	 * @param directionLight the direction vector from the point to the light source
	 * @param normalVector   the normal vector at the point on the geometry
	 *                       direction vector
	 * @param dotProduct     the dot product of the normal vector and the direction
	 * @return {@code true} if the point is unshaded (i.e., no objects block the
	 *         light); {@code false} otherwise
	 */
	private boolean unshaded(GeoPoint geoPoint, LightSource light, Vector directionLight, Vector normalVector,
			double dotProduct) {
        Vector lightDirection = directionLight.scale(-1); // from point to light source
        Ray ray = new Ray(geoPoint.point, lightDirection, normalVector);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return true;
        for (GeoPoint intersection : intersections)
            if (Double3.ZERO.equals(intersection.geometry.getMaterial().kT))
                return false;
        return true;
	}
	
	/**
	 * calculate the ray of reflection
	 *
	 * @param geoPoint      the point on geomtry
	 * @param ray           The ray that intersected with the geometry.
	 * @return              the reflection ray
	 */
	private Ray constructReflectedRay(GeoPoint geoPoint, Ray ray) {
		Vector normal = geoPoint.geometry.getNormal(geoPoint.point);
		Vector direction = ray.getDirection();
		Vector r = direction.subtract(normal.scale(2 * direction.dotProduct(normal)));
		return new Ray(geoPoint.point, r, normal);
	}
	
	/**
	 * calculate the ray of transparent
	 * 
	 * @param geoPoint     the point on geomtry
	 * @param ray          The ray that intersected with the geometry.
	 * @return             the transparent ray        
	 */
	private Ray constructTransparentRay(GeoPoint geoPoint, Ray ray) {
	//TODO 
	}
	
    /**
     * calculate the global effects (transparency and reflection) of light on a point
     *
     * @param ray   the ray that intersects the point
     * @param kx    the attenuation factor of material
     * @param level the recursive level
     * @param k     the reduced attenuation factor
     * @return the Color of the global effects on the point
     */
    private Color calcColorGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx))
                .scale(kx);
    }

}
