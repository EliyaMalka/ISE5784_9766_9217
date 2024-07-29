package lighting;

import primitives.*;

/**
 * The LightSource interface represents a light source in a 3D space. It is
 * implemented by specific types of light sources.
 */
public interface LightSource {

	/**
	 * Returns the intensity of the light at the specified point.
	 * 
	 * @param p The point at which to calculate the intensity of the light.
	 * @return The intensity of the light at the point.
	 */
	public Color getIntensity(Point p);

	/**
	 * Returns the vector from the light source to the specified point.
	 * 
	 * @param p The point at which to calculate the vector from the light source.
	 * @return The vector from the light source to the point.
	 */
	public Vector getL(Point p);

	/**
	 * Returns the distance from the light source to the specified point.
	 * 
	 * @param point The point at which to calculate the distance from the light
	 *              source.
	 * @return The distance from the light source to the point.
	 */
	public double getDistance(Point point);
}
