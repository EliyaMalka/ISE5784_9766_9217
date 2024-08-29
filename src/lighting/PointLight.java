package lighting;

import primitives.*;

/**
 * The PointLight class represents a light source in a 3D space that emits light
 * in all directions from a single point.
 */
public class PointLight extends Light implements LightSource {

	/** The constant attenuation factor. */
	private double kC = 1;
	/** The linear attenuation factor. */
	private double kL = 0;
	/** The quadratic attenuation factor. */
	private double kQ = 0;
	/*** The position of the light. */
	protected final Point position;

	/**
	 * Constructs a new PointLight with the specified intensity and position.
	 * 
	 * @param intensity The intensity of the light.
	 * @param position  The position of the light.
	 */
	public PointLight(Color intensity, Point position) {
		super(intensity);
		this.position = position;
	}

	/**
	 * Sets the constant attenuation factor.
	 *
	 * @param kC the constant attenuation factor
	 * @return the {@code PointLight} object itself for method chaining
	 */
	public PointLight setKC(Double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * Sets the linear attenuation factor.
	 *
	 * @param kL the linear attenuation factor
	 * @return the {@code PointLight} object itself for method chaining
	 */
	public PointLight setKl(Double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * Sets the quadratic attenuation factor.
	 *
	 * @param kQ the quadratic attenuation factor
	 * @return the {@code PointLight} object itself for method chaining
	 */
	public PointLight setKq(Double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		Double distance = position.distance(p);
		Double denominator = kC + kL * distance + kQ * distance * distance;
		return intensity.scale(1.0 / denominator);
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}

	@Override
	public double getDistance(Point point) {
		return point.distance(position);
	}
}
