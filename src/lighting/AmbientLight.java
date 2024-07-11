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
	public static AmbientLight NONE = new AmbientLight(Color.BLACK, 0.0);

	/**
	 * Constructs an AmbientLight with the specified color and attenuation factor.
	 *
	 * @param IA the color of the ambient light
	 * @param KA the attenuation factor as a Double3
	 */
	public AmbientLight(Color IA, Double3 KA) {
		super(IA.scale(KA));
	}

	/**
	 * Constructs an AmbientLight with the specified color and attenuation factor.
	 *
	 * @param IA the color of the ambient light
	 * @param KA the attenuation factor as a double
	 */
	public AmbientLight(Color IA, Double KA) {
		super(IA.scale(KA));
	}
}
