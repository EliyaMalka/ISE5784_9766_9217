package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;

public interface Intersectable {

	// להוסיף תיעוד
	public List<Point> findIntsersections(Ray ray); 
}
