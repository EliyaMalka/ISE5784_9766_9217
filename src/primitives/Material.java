package primitives;

/**
 * The Material class represents the material of an object in a scene. It is
 * defined by its color, shininess, and reflectivity.
 */
public class Material {

	public Double3 kD = Double3.ZERO;;
	public Double3 kS = Double3.ZERO;
	int nShininess = 0;

	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	public Material setKd(Double kD) {
		this.kS = new Double3(kD);
		return this;
	}

	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}

	public Material setKs(Double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
