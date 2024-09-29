package unittests.renderer;

import org.junit.jupiter.api.Disabled;
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
public class DepthOfFieldTest {
	/** Scene for the tests */
	private final Scene scene = new Scene("Test scene");

	/**
	 * Test method 1 for depth of field
	 */
	@Test
	public void focusCloseSphere() {

		Camera.Builder camera = Camera.getBuilder().setDirection(new Vector(0, 350, -100), new Vector(1, 6, 21))
				.setRayTracer(new SimpleRayTracer(scene)).setNumOfRaysDOF(33).setApertureSize(1.5)
				.setfocalPlaneDistance(230).setThreadsCount(4).setMax(4).setUseAdaptive(true);

		scene.geometries.add(
				new Triangle(new Point(-660, -440, 0), new Point(-660, 440, 0), new Point(660, 440, 0))
						.setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(5).setKr(0).setKt(0)),
				new Triangle(new Point(-660, -440, 0), new Point(660, 440, 0), new Point(660, -440, 0))
						.setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(5).setKr(0).setKt(0)),
				new Sphere(new Point(27.5, 110, 10), 10).setEmission(new Color(255, 0, 0))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(37.5, 170, 7.5), 7.5).setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(15, 40, 15), 15).setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(0, -50, 20), 20).setEmission(new Color(255, 0, 0))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(-15, -120, 25), 25).setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(-25, -170, 30), 30).setEmission(new Color(255, 0, 0))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)));

		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-400, 350, 500), new Vector(400, -400, -470))
				.setKl(0.0004).setKq(0.0000006));

		camera.setLocation(new Point(0, -400, 130)).setVpDistance(1000).setVpSize(350, 350)
				.setImageWriter(new ImageWriter("focusCloseSphereWithASS", 500, 500)).build().renderImage().writeToImage();

	}

	/**
	 * Test method 2 for depth of field
	 */
	@Test
	@Disabled
	public void focusFarSphere() {

		Camera.Builder camera = Camera.getBuilder().setDirection(new Vector(0, 350, -100), new Vector(1, 6, 21))
				.setRayTracer(new SimpleRayTracer(scene)).setNumOfRaysDOF(100).setApertureSize(1)
				.setfocalPlaneDistance(565);

		scene.geometries.add(
				new Triangle(new Point(-660, -440, 0), new Point(-660, 440, 0), new Point(660, 440, 0))
						.setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(5).setKr(0).setKt(0)),
				new Triangle(new Point(-660, -440, 0), new Point(660, 440, 0), new Point(660, -440, 0))
						.setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(5).setKr(0).setKt(0)),
				new Sphere(new Point(37.5, 170, 7.5), 7.5).setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(27.5, 110, 10), 10).setEmission(new Color(255, 0, 0))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(15, 40, 15), 15).setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(0, -50, 20), 20).setEmission(new Color(255, 0, 0))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(-15, -120, 25), 25).setEmission(new Color(0, 0, 255))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)),
				new Sphere(new Point(-25, -170, 30), 30).setEmission(new Color(255, 0, 0))
						.setMaterial(new Material().setKd(0.6).setShininess(30).setKs(0.2).setKt(0).setKr(0.1)));

		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-400, 350, 500), new Vector(400, -400, -470))
				.setKl(0.0004).setKq(0.0000006));

		camera.setLocation(new Point(0, -400, 130)).setVpDistance(1000).setVpSize(350, 350)
				.setImageWriter(new ImageWriter("focusFarSphere", 500, 500)).build().renderImage().writeToImage();

	}

}
