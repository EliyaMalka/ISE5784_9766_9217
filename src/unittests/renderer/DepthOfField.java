package unittests.renderer;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.SpotLight;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

/**
 * Testing depth of field Case
 * 
 */
public class DepthOfField {
	/** Scene for the tests */
	private final Scene scene = new Scene("Test scene");

	@Test
	public void tableWithSphere() {

		Camera.Builder camera = Camera.getBuilder().setDirection(new Vector(0, 350, -100), new Vector(1,6,21))
				.setRayTracer(new SimpleRayTracer(scene)).setAmountOfRaysDOF(100).setAperture(3).setDepthOfField(50);

		scene.geometries.add(new Triangle(new Point(-180, -90, 0), new Point(-180, 90, 0), new Point(180, 90, 0))
				.setEmission(new Color(0, 8, 255)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
				new Triangle(new Point(-180, -90, 0), new Point(180, 90, 0), new Point(180, -90, 0))
						.setEmission(new Color(0, 8, 255))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
				new Sphere(new Point(0, 0, 30), 10).setEmission(new Color(255,0,0))
						.setMaterial(new Material().setKd(0.2).setShininess(30).setKs(0.6)),
				new Sphere(new Point(-20, -50, 30), 20).setEmission(new Color(255,0,0))
						.setMaterial(new Material().setKd(0.2).setShininess(30).setKs(0.6)));

		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-600, 300, 300), new Vector(40, -20, -10))
				.setKl(0.0004).setKq(0.0000006));

		camera.setLocation(new Point(0, -350, 130)).setVpDistance(1000).setVpSize(200, 200)
				.setImageWriter(new ImageWriter("tableWithSphereDepthOfFeild", 500, 500)).build().renderImage().writeToImage();

	}

}
