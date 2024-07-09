package scene;

import primitives.*;
import lighting.*;
import geometries.*;

/**
 * The Scene class represents a 3D scene containing geometries, lighting, and
 * background settings.
 */
public class Scene {

	/**
	 * The name of the scene.
	 */
	public String name;

	/**
	 * The background color of the scene. Default is black.
	 */
	public Color background = Color.BLACK;

	/**
	 * The ambient light of the scene. Default is none.
	 */
	public AmbientLight ambientLight = AmbientLight.NONE;

	/**
	 * The collection of geometries in the scene.
	 */
	public Geometries geometries = new Geometries();

	/**
	 * Constructs a new Scene with the specified name.
	 *
	 * @param name The name of the scene.
	 */
	public Scene(String name) {
		this.name = name;
	}

	/**
	 * Sets the background color of the scene.
	 *
	 * @param background The background color to set.
	 * @return The current Scene instance for further configuration.
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * Sets the ambient light of the scene.
	 *
	 * @param ambientLight The ambient light to set.
	 * @return The current Scene instance for further configuration.
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * Sets the geometries of the scene.
	 *
	 * @param geometries The geometries to set.
	 * @return The current Scene instance for further configuration.
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
}
