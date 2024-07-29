package lighting;

import primitives.*;

/**
 * The DirectionalLight class represents a light source in a 3D space that emits
 * light in a specific direction.
 */
public class DirectionalLight extends Light implements LightSource {

	/*** The direction of the light. */
	private Vector direction;

	/**
	 * Constructs a new DirectionalLight with the specified intensity and direction.
	 * 
	 * @param intensity The intensity of the light.
	 * @param direction The direction of the light.
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		return intensity;
	}

	@Override
	public Vector getL(Point p) {
		return direction;
	}

	@Override
	public double getDistance(Point point) {
		return Double.POSITIVE_INFINITY;
	}
}
