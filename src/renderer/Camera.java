package renderer;

import java.util.MissingResourceException;

import primitives.*;

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
		
		public Camera Build() {
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
	
	private Point p0;
	private Vector vTo;
	private Vector vUp;
	private Vector vRight;
	private double width = 0.0;
	private double height = 0.0;
	private double distance = 0.0;
	
	public Ray constructRay(int nX, int nY, int j, int i) {

		  double yI = -(i - (nY - 1) / 2.0) * Ry;
	        double xJ = (j - (nX - 1) / 2.0) * Rx;

	        Point3D pIJ = pCenter;

	        if (xJ != 0) {
	            pIJ = pIJ.add(vRight.scale(xJ));
	        }

	        if (yI != 0) {
	            pIJ = pIJ.add(vUp.scale(yI));
	        }

	        return pIJ;

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
