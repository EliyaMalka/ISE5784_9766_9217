package primitives;

/**
 * The Material class represents the material of an object in a scene. It is
 * defined by its color, shininess, and reflectivity.
 */
public class Material {

	/**
	 * The diffuse reflection coefficient of the material.
	 */
	public Double3 kD = Double3.ZERO;;
	/**
	 * The specular reflection coefficient of the material.
	 */
	public Double3 kS = Double3.ZERO;
	/**
	 * The shininess of the material.
	 */
	public int nShininess = 0;

	/**
	 * Setter for kd.
	 *
	 * @param kD the kd to set
	 * @return the material itself for method chaining
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Setter for kd with regular double.
	 *
	 * @param kD the kd to set
	 * @return the material itself for method chaining
	 */
	public Material setKd(Double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * Setter for ks.
	 *
	 * @param kS the ks to set
	 * @return the material itself for method chaining
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * Setter for ks with regular double.
	 *
	 * @param kS the ks to set
	 * @return the material itself for method chaining
	 */
	public Material setKs(Double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * Setter for nShininess.
	 *
	 * @param nShininess the nShininess to set
	 * @return the material itself for method chaining
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
