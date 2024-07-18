package lighting;

import primitives.*;

/**
 * The AmbientLight class represents ambient lighting in a scene. It is defined
 * by its intensity and color.
 */
public class AmbientLight extends Light {

	/**
	 * A constant representing no ambient light.
	 */
	public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

	/**
	 * Constructs an AmbientLight with the specified color and attenuation factor.
	 *
	 * @param iA the color of the ambient light
	 * @param kA the attenuation factor as a Double3
	 */
	public AmbientLight(Color iA, Double3 kA) {
		super(iA.scale(kA));
	}

	/**
	 * Constructs an AmbientLight with the specified color and attenuation factor.
	 *
	 * @param iA the color of the ambient light
	 * @param kA the attenuation factor as a double
	 */
	public AmbientLight(Color iA, Double kA) {
		super(iA.scale(kA));
	}
}
