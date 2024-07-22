package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource {

	private double kC = 1;
	private double kL = 0;
	private double kQ = 0;
	protected Point position;

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

	public PointLight setKC(Double kC) {
		this.kC = kC;
		return this;
	}

	public PointLight setKl(Double kL) {
		this.kL = kL;
		return this;
	}

	public PointLight setKq(Double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		Double distance = position.distance(p);
		Double denominator = kC + kL * distance + kQ * distance * distance;
		return getIntensity().scale(1.0 / denominator);
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}
}
