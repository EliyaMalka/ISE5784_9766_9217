package unittests.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.Camera;

import java.util.Iterator;
import java.util.List;

class CameraSphereIntersectoin {
	
	public List<Point> intersectionNumber(Geometries groupOfGeometrics, Camera camera){
	
		
		
		
	}

	@Test
	void testIntersction() {

		Point centerPoint = new Point(0, 0 ,-3);
		Sphere sphere = new Sphere(centerPoint, 1.0);
		Camera camera = Camera.getBuilder()
				.setLocation(new Point(0,0,0))
				.setDirection(new Vector(0,0,-1), new Vector(0,1,0))
				.setVpSize(3, 3)
				.setVpDistance(1)
				.Build();
		
		
		// TC01
assertEquals(2, ););

	}

}
