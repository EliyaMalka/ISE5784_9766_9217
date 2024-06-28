package renderer;

import java.util.MissingResourceException;

import primitives.*;
import primitives.Util.*;

public class Camera implements Cloneable {

	public static class Builder{
		private final Camera camera = new Camera();
		
		public Builder setLocation(Point p) {
			camera.p0 = p;
			return this;
		}
		
		public Builder setDirection(Vector vto, Vector vup) {
			if(vto.dotProduct(vup) != 0)
				throw new IllegalArgumentException("Vecrors vUp and vTo are nor verticals");
			camera.vTo = vto.normalize();
			camera.vUp = vup.normalize();
			return this;
		}
		
		public Builder setVpSize(double w, double h) {
			if(w <= 0 || h <= 0)
				throw new IllegalArgumentException("width and height can't be negative");
			camera.height = h;
			camera.width = w;
			return this;
		}
		
		public Builder setVpDistance(double d) {
			if(d <= 0)
				throw new IllegalArgumentException("distance can't be negative");
			camera.distance = d;
			return this;
		}
		
		public Camera build() {
			if(camera.width == 0) 
				throw new MissingResourceException("rendere data missing","Camera","width missing");
			if(camera.height == 0) 
				throw new MissingResourceException("rendere data missing","Camera","height missing");
			if(camera.distance == 0)
				throw new MissingResourceException("rendere data missing","Camera","distance missing");
			if(camera.vTo == null)
				throw new MissingResourceException("rendere data missing","Camera","vTo missing");
			if(camera.vUp == null)
				throw new MissingResourceException("rendere data missing","Camera","vUp missing");
			camera.vRight = (camera.vUp.crossProduct(camera.vTo)).normalize();
			try {
				return (Camera) camera.clone();
			} 
			catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
				}
		}
	}
	
	private Point p0; // point location of the camera
	private Vector vTo; // vector to the image
	private Vector vUp; 
	private Vector vRight;
	private double width = 0.0; // width of the view plane
	private double height = 0.0; // height of the view plane
	private double distance = 0.0; // distance between the camera and the view plane
	
	public Ray constructRay(int nX, int nY, int j, int i) {
		
		double Ry = height / nY; // Ry is the distance between one "tick mark" to another in Y-axis.
		double Rx = width / nX; // Rx is the distance between one "tick mark" to another in X-axis.

		  double yI = ( ((nY - 1) / 2.0) - i ) * Ry;
	        double xJ = (j - (nX - 1) / 2.0) * Rx;

	        Point pIJ = p0.add(vTo.scale(distance)); // pIJ now equal to the point of the center view plane

	        if (!Util.isZero(xJ)) {
	            pIJ = pIJ.add(vRight.scale(xJ));
	        }

	        if (!Util.isZero(yI)) {
	            pIJ = pIJ.add(vUp.scale(yI));
	        }

	        return new Ray(p0, pIJ.subtract(p0));

	}
	
	public Point getp0() {
		return p0;
	}
	
	public Vector getvTo() {
		return vTo;
	}
	
	public Vector getvUp() {
		return vUp;
	}
	
	public Vector getvRight() {
		return vRight;
	}
	
	public double getWitgh() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getDistance() {
		return distance;
	}
	
	private Camera() {
		
	}
	
	public static Builder getBuilder() {
		return new Builder();
	}
	

}
