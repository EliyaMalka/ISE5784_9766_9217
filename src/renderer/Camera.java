package renderer;

import java.util.LinkedList;
import java.util.stream.*;
import java.util.List;
import java.util.MissingResourceException;
import primitives.*;
import static primitives.Util.*;

/**
 * The Camera class represents a camera used for rendering a 3D image. The
 * camera is defined by its position, direction vectors, and the size of the
 * view plane.
 */
public class Camera implements Cloneable {

	// -------------------- Objects for camera
	// -----------------------------------------------------

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

	// -------------------- Objects for images
	// -----------------------------------------------------

	/**
	 * The ImageWriter instance for the camera.
	 */
	private ImageWriter imageWriter;

	/**
	 * The RayTracerBase instance for the camera.
	 */
	private RayTracerBase rayTracer;

	// -------------------- Objects for DOF
	// -----------------------------------------------------

	/**
	 * The aperture radius size for depth of field effects.
	 */
	private double apertureSizeRadius = 0.0;

	/**
	 * The number of rays for depth of field effects.
	 */
	private int numOfRaysDOF = 0;

	/**
	 * The distance between the camera and the focal plane.
	 */
	private double focalPlaneDistance = 0;

	// -------------------- Objects for multi-threading
	// -----------------------------------------------------

	/**
	 * Pixel manager for supporting multi-threading
	 */
	private PixelManager pixelManager;

	/**
	 * print time interval in seconds, 0 if printing is not required
	 */
	double interval = 5;

	/**
	 * number of threads
	 */
	int threadsCount = 0;

	// -------------------- Builder
	// -----------------------------------------------------

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
		 * @param pointOfCamera the position of the camera
		 * @return the current Builder instance for further configuration
		 */
		public Builder setLocation(Point pointOfCamera) {
			camera.p0 = pointOfCamera;
			return this;
		}

		/**
		 * Sets the direction vectors of the camera.
		 *
		 * @param vTo the direction vector towards the image
		 * @param vUp the up direction vector
		 * @return the current Builder instance for further configuration
		 * @throws IllegalArgumentException if the vectors are not perpendicular
		 */
		public Builder setDirection(Vector vTo, Vector vUp) {
			if (!isZero(vTo.dotProduct(vUp)))
				throw new IllegalArgumentException("Vectors vUp and vTo are not verticals");
			camera.vTo = vTo.normalize();
			camera.vUp = vUp.normalize();
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
			if (alignZero(width) <= 0 || alignZero(height) <= 0)
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
			if (alignZero(distance) <= 0)
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
		 * Set the aperture of the camera
		 *
		 * @param aperture the aperture of the camera
		 * @return the camera builder
		 */
		public Builder setApertureSize(double aperture) {
			camera.apertureSizeRadius = aperture;
			return this;
		}

		/**
		 * Set the number of rays in the grid for the depth of field
		 *
		 * @param numberRays the number of rays in the grid
		 * @return the camera builder
		 */
		public Builder setNumOfRaysDOF(int numberRays) {
			camera.numOfRaysDOF = numberRays;
			return this;
		}

		/**
		 * Set the depth of field of the camera
		 *
		 * @param distanceFocal the depth of field of the camera
		 * @return the camera builder
		 */
		public Builder setfocalPlaneDistance(double distanceFocal) {
			camera.focalPlaneDistance = distanceFocal;
			return this;
		}

		/**
		 * Set the interval for printing progress
		 *
		 * @param interval the interval for printing progress
		 * @return the camera builder
		 */
		public Builder setInterval(double interval) {
			camera.interval = interval;
			return this;
		}

		/**
		 * Set the number of threads for rendering
		 * 
		 * @param threadsCount the number of threads for rendering
		 * @return the camera builder
		 */
		public Builder setThreadsCount(int threadsCount) {
			camera.threadsCount = threadsCount;
			return this;
		}

		/**
		 * Builds and returns the configured Camera object.
		 *
		 * @return a new Camera object based on the configured parameters
		 * @throws MissingResourceException if any required parameters are missing
		 */
		public Camera build() {
			// View Plane data
			if (alignZero(camera.width) <= 0)
				throw new MissingResourceException("Renderer data missing", "Camera", "width missing");
			if (alignZero(camera.height) <= 0)
				throw new MissingResourceException("Renderer data missing", "Camera", "height missing");
			if (alignZero(camera.distance) <= 0)
				throw new MissingResourceException("Renderer data missing", "Camera", "distance missing");

			// Camera location and direction data
			if (camera.vTo == null)
				throw new MissingResourceException("Renderer data missing", "Camera", "vTo missing");
			if (camera.vUp == null)
				throw new MissingResourceException("Renderer data missing", "Camera", "vUp missing");
			if (alignZero(camera.vTo.dotProduct(camera.vUp)) != 0.0)
				throw new IllegalArgumentException("vUp and vTo are not verticals");
			camera.vRight = (camera.vTo.crossProduct(camera.vUp)).normalize();

			// Helper objects data
			if (camera.imageWriter == null)
				throw new MissingResourceException("Renderer data missing", "Camera", "imageWriter missing");
			if (camera.rayTracer == null)
				throw new MissingResourceException("Renderer data missing", "Camera", "rayTracer missing");

			// Camera location data
			if (camera.p0 == null)
				throw new MissingResourceException("Renderer data missing", "Camera", "p0 missing");

			try {
				return (Camera) camera.clone();
			} catch (CloneNotSupportedException e) {
				throw new AssertionError(e);
			}
		}
	}

	// -------------- Geters -------------------------------------------------------

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
	 * Returns a new Builder instance for constructing a Camera.
	 *
	 * @return a new Builder instance
	 */
	public static Builder getBuilder() {
		return new Builder();
	}

	/**
	 * Private constructor to prevent direct instantiation of the Camera class.
	 */
	private Camera() {
	}

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
		double rY = height / nY;
		double rX = width / nX;

		double yI = (((nY - 1) / 2.0) - i) * rY;
		double xJ = (j - (nX - 1) / 2.0) * rX;

		Point pIJ = p0.add(vTo.scale(distance));
		if (!isZero(xJ))
			pIJ = pIJ.add(vRight.scale(xJ));
		if (!isZero(yI))
			pIJ = pIJ.add(vUp.scale(yI));

		return new Ray(p0, pIJ.subtract(p0));
	}

	/**
	 * Renders the image by casting rays through each pixel and calculating the
	 * color.
	 *
	 * @return the current Camera instance
	 */
	public Camera renderImage() {
		final int nX = imageWriter.getNx();
		final int nY = imageWriter.getNy();

		pixelManager = new PixelManager(nY, nX, interval);
		if (threadsCount == 0) {
			for (int j = 0; j < nY; j++) {
				for (int i = 0; i < nX; i++) {
					castRay(nX, nY, j, i);
				}
			}
		} else {
			IntStream.range(0, nY).parallel().forEach(i -> IntStream.range(0, nX).parallel() // for each row:
					.forEach(j -> castRay(nX, nY, j, i))); // for each column:
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
	 * Construct a shifted point from a pixel
	 *
	 * @param nX        the number of pixels in the x direction
	 * @param nY        the number of pixels in the y direction
	 * @param j         the x index of the pixel
	 * @param i         the y index of the pixel
	 * @param pixelSize the size of the pixel
	 * @return the shifted point
	 */
	private Point constructShiftedPoint(int nX, int nY, double j, double i, double pixelSize) {
		double shiftedX = Util.random(-0.5, 0.5);
		double shiftedY = Util.random(-0.5, 0.5);
		Point pIJ = p0;

		double yI = -(i + shiftedY - (nY - 1) / 2.0) * pixelSize;
		double xJ = (j + shiftedX - (nX - 1) / 2.0) * pixelSize;

		if (!isZero(xJ))
			pIJ = pIJ.add(vRight.scale(xJ));
		if (!isZero(yI))
			pIJ = pIJ.add(vUp.scale(yI));

		return pIJ;
	}

	/**
	 * Construct a grid of rays from the camera
	 *
	 * @param numberRaysGrid the number of rays in the grid
	 * @param rayCamera      the ray from the camera
	 * @return the list of rays in the circle
	 */
	public List<Ray> constructRaysGridFromCamera(int numberRaysGrid, Ray rayCamera) {
		List<Ray> raysOfGrid = new LinkedList<>();

		double highFocusPoint = focalPlaneDistance / (vTo.dotProduct(rayCamera.getDirection()));

		Point focusPoint = rayCamera.getPoint(highFocusPoint);

		double pixelSize = (apertureSizeRadius * 2) / numberRaysGrid;

		for (int i = 0; i < numberRaysGrid; i++) {
			for (int j = 0; j < numberRaysGrid; j++) {
				Point tmpPoint = constructShiftedPoint(numberRaysGrid, numberRaysGrid, j, i, pixelSize);
				Ray tmpRay = new Ray(tmpPoint, focusPoint.subtract(tmpPoint));

				if (tmpRay.getHead().distanceSquared(p0) <= apertureSizeRadius * apertureSizeRadius)
					raysOfGrid.add(tmpRay);
			}
		}

		return raysOfGrid;
	}

	/**
	 * Casts a ray through a specific pixel and writes the traced color to the
	 * image.
	 *
	 * @param nX     the number of pixels in the x-axis
	 * @param nY     the number of pixels in the y-axis
	 * @param column the column index of the pixel
	 * @param row    the row index of the pixel
	 */
	private void castRay(int nX, int nY, int column, int row) {
		Ray ray = constructRay(nX, nY, column, row);

		// Depth of field
		if (apertureSizeRadius > 0 && focalPlaneDistance > 0 && numOfRaysDOF > 0) {
			Color accumulatedColor = Color.BLACK;
			List<Ray> rays = constructRaysGridFromCamera(numOfRaysDOF, ray);
			for (Ray rayFromList : rays) {
				accumulatedColor = accumulatedColor.add(rayTracer.traceRay(rayFromList));
			}
			accumulatedColor = accumulatedColor.scale(1d / rays.size());
			imageWriter.writePixel(column, row, accumulatedColor);
			pixelManager.pixelDone();
		} else {
			imageWriter.writePixel(column, row, rayTracer.traceRay(ray));
			pixelManager.pixelDone();
		}
	}
}
