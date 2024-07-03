package lighting;

import primitives.*;

/**
 * The AmbientLight class represents ambient lighting in a scene. It is defined by its intensity and color.
 */
public class AmbientLight {

    private final Color intensity; // the intensity of the ambient light

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
        intensity = IA.scale(KA);
    }

    /**
     * Constructs an AmbientLight with the specified color and attenuation factor.
     *
     * @param IA the color of the ambient light
     * @param KA the attenuation factor as a double
     */
    public AmbientLight(Color IA, Double KA) {
        intensity = IA.scale(KA);
    }

    /**
     * Returns the intensity of the ambient light.
     *
     * @return the intensity of the ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
