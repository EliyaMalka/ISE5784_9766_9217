package primitives;

import static primitives.Util.isZero;

/**
 * The Ray class represents a ray in a 3D space, defined by a point and a direction vector.
 */
public class Ray {
	
    /**
     * The head point of the ray.
     */
	private final Point head;
    /**
     * The direction vector of the ray.
     */
	private final Vector direction;
	

    /**
     * Constructs a new Ray with the given head point and direction vector.
     * 
     * @param P The head point of the ray.
     * @param V The direction vector of the ray.
     */
	public Ray(Point P, Vector V) {
		this.head = P;
		this.direction = V.normalize();
	}
	
    @Override
    public boolean equals(Object obj) {
	      if (this == obj) return true;
	      return (obj instanceof Ray other)
	            && (this.head == other.head)
	            && (this.direction == other.direction);
    }
    
    
    @Override
    public String toString() {
    	
    	return " ";
    	 }	   
    

    public Point getHead() {
    	
    	return head;
    	
    	 }	
    

    public Vector getDirection() {
    	
    	return direction;
    	
    	 }	
}

