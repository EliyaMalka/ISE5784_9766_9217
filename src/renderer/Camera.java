package renderer;

import java.util.MissingResourceException;
import primitives.*;

/**
 * The Camera class represents a camera used for rendering a 3D image. The
 * camera is defined by its position, direction vectors, and the size of the
 * view plane.
 */
public class Camera implements Cloneable {

	/**
	 * Builder class for Camera, allowing for incremental construction and
	 * configuration of a Camera object.
	 */
	public static class Builder {

		/**
		 * The Camera instance being built.
		 */
		private final Camera camera = new Camera();

		/**
		 * Sets the location of the camera.
		 *
		 * @param p the position of the camera
		 * @return the current Builder instance for further configuration
		 */
		public Builder setLocation(Point p) {
			camera.p0 = p;
			return this;
		}

		/**
		 * Sets the direction vectors of the camera.
		 *
		 * @param vto the direction vector towards the image
		 * @param vup the up direction vector
		 * @return the current Builder instance for further configuration
		 * @throws IllegalArgumentException if the vectors are not perpendicular
		 */
		public Builder setDirection(Vector vto, Vector vup) {
			if (!Util.isZero(vto.dotProduct(vup)))
				throw new IllegalArgumentException("Vectors vUp and vTo are not verticals");
			camera.vTo = vto.normalize();
			camera.vUp = vup.normalize();
			return this;
		}

		/**
		 * Sets the size of the view plane.
		 *
		 * @param width  the width of the view plane
		 * @param height the height of the view plane
		 * @return the current Builder instance for further configuration
		 * @throws IllegalArgumentException if the width or height is non-positive
		 */
		public Builder setVpSize(double width, double height) {
			if (width <= 0 || height <= 0)
				throw new IllegalArgumentException("Width and height can't be negative");
			camera.height = height;
			camera.width = width;
			return this;
		}

		/**
		 * Sets the distance between the camera and the view plane.
		 *
		 * @param distance the distance to the view plane
		 * @return the current Builder instance for further configuration
		 * @throws IllegalArgumentException if the distance is non-positive
		 */
		public Builder setVpDistance(double distance) {
			if (distance <= 0)
				throw new IllegalArgumentException("Distance can't be negative");
			camera.distance = distance;
			return this;
		}

		/**
		 * Sets the ImageWriter for the camera.
		 *
		 * @param imageWriter the ImageWriter instance
		 * @return the current Builder instance for further configuration
		 */
		public Builder setImageWriter(ImageWriter imageWriter) {
			camera.imageWriter = imageWriter;
			return this;
		}

		/**
		 * Sets the RayTracer for the camera.
		 *
		 * @param rayTracer the RayTracerBase instance
		 * @return the current Builder instance for further configuration
		 */
		public Builder setRayTracer(RayTracerBase rayTracer) {
			camera.rayTracer = rayTracer;
			return this;
		}

		/**
		 * Builds and returns the configured Camera object.
		 *
		 * @return a new Camera object based on the configured parameters
		 * @throws MissingResourceException if any required parameters are missing
		 */
		public Camera build() {
			if (camera.width == 0)
				throw new MissingResourceException("Renderer data missing", "Camera", "width missing");
			if (camera.height == 0)
				throw new MissingResourceException("Renderer data missing", "Camera", "height missing");
			if (camera.distance == 0)
				throw new MissingResourceException("Renderer data missing", "Camera", "distance missing");
			if (camera.vTo == null)
				throw new MissingResourceException("Renderer data missing", "Camera", "vTo missing");
			if (camera.vUp == null)
				throw new MissingResourceException("Renderer data missing", "Camera", "vUp missing");
			if (camera.imageWriter == null)
				throw new MissingResourceException("Renderer data missing", "Camera", "imageWriter missing");
			if (camera.rayTracer == null)
				throw new MissingResourceException("Renderer data missing", "Camera", "rayTracer missing");
			camera.vRight = (camera.vTo.crossProduct(camera.vUp)).normalize();
			try {
				return (Camera) camera.clone();
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * The location of the camera.
	 */
	private Point p0;

	/**
	 * The direction vector towards the image.
	 */
	private Vector vTo;

	/**
	 * The up direction vector.
	 */
	private Vector vUp;

	/**
	 * The right direction vector, calculated from vTo and vUp.
	 */
	private Vector vRight;

	/**
	 * The width of the view plane.
	 */
	private double width = 0.0;

	/**
	 * The height of the view plane.
	 */
	private double height = 0.0;

	/**
	 * The distance between the camera and the view plane.
	 */
	private double distance = 0.0;

	/**
	 * The ImageWriter instance for the camera.
	 */
	private ImageWriter imageWriter;

	/**
	 * The RayTracerBase instance for the camera.
	 */
	private RayTracerBase rayTracer;

	/**
	 * Constructs a ray from the camera through a specific pixel in the view plane.
	 *
	 * @param nX the number of pixels in the x-axis
	 * @param nY the number of pixels in the y-axis
	 * @param j  the column index of the pixel
	 * @param i  the row index of the pixel
	 * @return the constructed ray
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		double Ry = height / nY; // the distance between tick marks in the Y-axis
		double Rx = width / nX; // the distance between tick marks in the X-axis

		double yI = (((nY - 1) / 2.0) - i) * Ry;
		double xJ = (j - (nX - 1) / 2.0) * Rx;

		Point pIJ = p0.add(vTo.scale(distance)); // the center of the view plane

		if (!Util.isZero(xJ)) {
			pIJ = pIJ.add(vRight.scale(xJ));
		}

		if (!Util.isZero(yI)) {
			pIJ = pIJ.add(vUp.scale(yI));
		}

		return new Ray(p0, pIJ.subtract(p0)); // return the ray from the camera
	}

	/**
	 * Returns the location of the camera.
	 *
	 * @return the location of the camera
	 */
	public Point getp0() {
		return p0;
	}

	/**
	 * Returns the direction vector towards the image.
	 *
	 * @return the direction vector towards the image
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * Returns the up direction vector.
	 *
	 * @return the up direction vector
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * Returns the right direction vector.
	 *
	 * @return the right direction vector
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * Returns the width of the view plane.
	 *
	 * @return the width of the view plane
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the height of the view plane.
	 *
	 * @return the height of the view plane
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Returns the distance between the camera and the view plane.
	 *
	 * @return the distance between the camera and the view plane
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Private constructor to prevent direct instantiation of the Camera class.
	 */
	private Camera() {
	}

	/**
	 * Returns a new Builder instance for constructing a Camera.
	 *
	 * @return a new Builder instance
	 */
	public static Builder getBuilder() {
		return new Builder();
	}

	/**
	 * Renders the image by casting rays through each pixel and calculating the
	 * color.
	 *
	 * @return the current Camera instance
	 */
	public Camera renderImage() {
		for (int j = 0; j < imageWriter.getNy(); j++) {
			for (int i = 0; i < imageWriter.getNx(); i++) {
				castRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
			}
		}
		return this;
	}

	/**
	 * Prints a grid on the image with a specified interval and color.
	 *
	 * @param interval the interval between grid lines
	 * @param color    the color of the grid lines
	 * @return the current Camera instance
	 */
	public Camera printGrid(int interval, Color color) {
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();
		for (int i = 0; i < nX; i++) {
			for (int j = 0; j < nY; j++) {
				if (i % interval == 0 || j % interval == 0 || i == nX - 1 || j == nY - 1)
					imageWriter.writePixel(i, j, color);
			}
		}
		return this;
	}

	/**
	 * Writes the rendered image to a file.
	 *
	 * @return the current Camera instance
	 */
	public Camera writeToImage() {
		imageWriter.writeToImage();
		return this;
	}

	/**
	 * Casts a ray through a specific pixel and writes the traced color to the
	 * image.
	 *
	 * @param Nx     the number of pixels in the x-axis
	 * @param Ny     the number of pixels in the y-axis
	 * @param column the column index of the pixel
	 * @param row    the row index of the pixel
	 * @return the current Camera instance
	 */
	private Camera castRay(int Nx, int Ny, int column, int row) {
		Ray ray = constructRay(Nx, Ny, column, row);
		Color color = rayTracer.traceRay(ray);
		imageWriter.writePixel(column, row, color);
		return this;
	}

}
