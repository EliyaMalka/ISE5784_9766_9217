package primitives;

import static primitives.Util.isZero;

public class Ray {
	
	private final Point head;
	private final Vector direction;
	
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
}

