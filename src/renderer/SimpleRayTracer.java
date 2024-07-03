package renderer;

import java.util.List;
import primitives.*;
import scene.*;

/**
 * The SimpleRayTracer class extends the RayTracerBase and implements a basic ray tracing algorithm.
 * It calculates the color at the intersection points of rays with scene geometries.
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

    /**
     * Traces a ray through the scene and determines the color at the closest intersection point.
     * If no intersection is found, the background color of the scene is returned.
     *
     * @param ray The ray to trace.
     * @return The color at the closest intersection point of the ray and the scene.
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersectionPoints = scene.geometries.findIntersections(ray);
        if (intersectionPoints == null)
            return scene.background;
        Point closestPoint = ray.findClosestPoint(intersectionPoints);
        return calcColor(closestPoint);
    }

    /**
     * Calculates the color at a given point in the scene.
     * Currently, it only returns the ambient light intensity.
     *
     * @param point The point at which to calculate the color.
     * @return The color at the specified point.
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
