package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The SpotLight class represents a spot light source in a 3D space. It is a
 * subclass of the PointLight class.
 */
public class SpotLight extends PointLight {

	/*** The direction of the spot light. */
	private Vector direction;

	/**
	 * Constructs a new SpotLight with the specified intensity, position, and
	 * direction.
	 * 
	 * @param intensity The intensity of the light.
	 * @param position  The position of the light.
	 * @param direction The direction of the light.
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point point) {

		return (super.getIntensity(point)).scale(Math.max(0, direction.dotProduct(getL(point))));
	}

	@Override
	public PointLight setKC(Double kC) {
		return (SpotLight) super.setKC(kC);
	}

	@Override
	public PointLight setKl(Double kL) {
		return (SpotLight) super.setKl(kL);

	}

	@Override
	public PointLight setKq(Double kQ) {
		return (SpotLight) super.setKq(kQ);
	}

}
