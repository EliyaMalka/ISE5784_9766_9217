package unittests.renderer;


import renderer.*;
import primitives.*;

import org.junit.jupiter.api.Test;

class ImageWriterTest {
	@Test
	void imageTest() {
		ImageWriter imageWriter = new ImageWriter("test", 800, 500);

		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 500; j++) {
				if (i % 50 == 0 || j % 50 == 0|| i == 799 || j == 499)
					imageWriter.writePixel(i, j, new Color(1, 57, 183));
				else
					imageWriter.writePixel(i, j, new Color(255, 255, 255));
			}
		}
		imageWriter.writeToImage();
	}

}
