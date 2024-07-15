package unittests.renderer;

import renderer.*;
import primitives.*;
import org.junit.jupiter.api.Test;
import static java.awt.Color.*;

/**
 * Unit test for the ImageWriter class.
 */
class ImageWriterTest {

	/**
	 * Tests the functionality of the ImageWriter class by creating an image with a
	 * grid pattern.
	 */
	@Test
	void imageTest() {
		final Color gridColor = new Color(1, 57, 183);
		final Color bgColor = new Color(WHITE);
		final int nX = 801, ny = 501, step = 50;
		// Create an ImageWriter object with a name "test" and dimensions 800x500
		final ImageWriter imageWriter = new ImageWriter("test", nX, ny);

		// Iterate over the pixels of the image
		for (int i = 0; i < nX; i++)
			for (int j = 0; j < ny; j++)
				// If the pixel is on a grid line or on the border, color it blue
				imageWriter.writePixel(i, j, i % step == 0 || j % step == 0 ? gridColor : bgColor);

		// Write the image to a file
		imageWriter.writeToImage();
	}
}
