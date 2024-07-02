package renderer;

import java.util.List;

import primitives.*;
import scene.*;

public class SimpleRayTracer extends RayTracerBase {

	@Override
	public Color traceRay(Ray ray) {
        List<Point> intersectionPoints = scene.geometries.findIntersections(ray);
		if (intersectionPoints == null)
			return scene.background;
		Point closestPoint = ray.findClosestPoint(intersectionPoints);
		return (calcColor(closestPoint));
	}
	
	public SimpleRayTracer(Scene scene) {
		super(scene);
	}
	
	private Color calcColor(Point point) {
		return scene.ambientLight.getIntensity();
	}

}
