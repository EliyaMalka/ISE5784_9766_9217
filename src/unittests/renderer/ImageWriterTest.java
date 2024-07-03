package unittests.renderer;

import renderer.*;
import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the ImageWriter class.
 */
class ImageWriterTest {

    /**
     * Tests the functionality of the ImageWriter class by creating an image with a grid pattern.
     */
    @Test
    void imageTest() {
        // Create an ImageWriter object with a name "test" and dimensions 800x500
        ImageWriter imageWriter = new ImageWriter("test", 800, 500);

        // Iterate over the pixels of the image
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                // If the pixel is on a grid line or on the border, color it blue
                if (i % 50 == 0 || j % 50 == 0 || i == 799 || j == 499)
                    imageWriter.writePixel(i, j, new Color(1, 57, 183));
                // Otherwise, color it white
                else
                    imageWriter.writePixel(i, j, new Color(255, 255, 255));
            }
        }
        // Write the image to a file
        imageWriter.writeToImage();
    }
}
