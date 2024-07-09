package renderer;

import scene.*;
import primitives.*;

/**
 * The RayTracerBase class serves as an abstract base class for ray tracing
 * algorithms. It provides a foundation for tracing rays through a scene and
 * determining the color at each intersection point.
 */
public abstract class RayTracerBase {

	/**
	 * The scene to be rendered.
	 */
	protected Scene scene;

	/**
	 * Constructs a new RayTracerBase with the specified scene.
	 *
	 * @param scene The scene to be rendered.
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Traces a ray through the scene and determines the color at the intersection
	 * point. This method must be implemented by subclasses.
	 *
	 * @param ray The ray to trace.
	 * @return The color at the intersection point of the ray and the scene.
	 */
	public abstract Color traceRay(Ray ray);
}
