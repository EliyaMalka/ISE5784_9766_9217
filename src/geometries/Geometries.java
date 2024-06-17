package geometries;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

public class Geometries implements Intersectable {

	private final List<Intersectable> geometries = new LinkedList<Intersectable>();

	public Geometries() {
	}

	public Geometries(Intersectable... geometries) {

		this.add(geometries);
	}

	public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
