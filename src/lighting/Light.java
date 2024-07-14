package lighting;

import primitives.*;

/**
 * The Light class represents a light source in a 3D space. It is an abstract
 * class that is extended by specific types of lights.
 */

abstract class Light {

	/**
	 * Constructs a new Light with the given intensity.
	 * 
	 * @param intensity The intensity of the light.
	 */
	protected Color intensity;

	/**
	 * Constructs a new Light with the given intensity.
	 * 
	 * @param intensity The intensity of the light.
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * Returns the intensity of the light.
	 * 
	 * @return The intensity of the light.
	 */
	public Color getIntensity() {
		return intensity;
	}
}
