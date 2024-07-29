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
	 * The transparency coefficient of the material.
	 */
	public Double3 kT = Double3.ZERO;

	/**
	 * The reflection coefficient of the material.
	 */
	public Double3 kR = Double3.ZERO;

	/**
	 * Setter for kD.
	 *
	 * @param kD the kD to set
	 * @return the material itself for method chaining
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Setter for kD with regular double.
	 *
	 * @param kD the kD to set
	 * @return the material itself for method chaining
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * Setter for kS.
	 *
	 * @param kS the kS to set
	 * @return the material itself for method chaining
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * Setter for kS with regular double.
	 *
	 * @param kS the kS to set
	 * @return the material itself for method chaining
	 */
	public Material setKs(double kS) {
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
	
	/**
	 * Setter for kT.
	 *
	 * @param kT the kT to set
	 * @return the material itself for method chaining
	 */
	public Material setKT(Double3 kT) {
		this.kT = kT;
		return this;
	}
	
	/**
	 * Setter for kT with regular double.
	 * 
	 * @param kT the kT to set
	 * @return the material itself for method chaining
	 */
	public Material setKT(double kT) {
		this.kT = new Double3(kT);
		return this;
	}
	
	/**
	 * Setter for kR.
	 * 
	 * @param kR the kR to set
	 * @return the material itself for method chaining
	 */
	public Material setKR(Double3 kR) {
		this.kR = kR;
        return this;
	}
	
	/**
	 * Setter for kR with regular double.
	 * 
	 * @param kR the kR to set
	 * @return the material itself for method chaining
	 */
    public Material setKR(double kR) {
		this.kR = new Double3(kR);
		return this;
    }
}
