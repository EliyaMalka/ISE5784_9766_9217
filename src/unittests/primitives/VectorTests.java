/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Checkbox;
import java.lang.classfile.instruction.NewMultiArrayInstruction;

import javax.naming.spi.DirStateFactory.Result;

import primitives.Double3;
import primitives.Vector;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class VectorTests {
	
	   private final double DELTA = 0.000001;
	   
	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
	      // ============ Equivalence Partitions Tests ==============
		
		// check if lengthSquared() return correct value
		Vector v = new Vector(2,4,-4);
		assertEquals(36, v.lengthSquared(), DELTA ,"lengthSquared() is not return correct value");
	}
	
	/**
	 * Test method for {@link primitives.Vector#Vector()}.
	 */
	@Test
	void testVector() {
		
		
	      // ============ Equivalence Partitions Tests ==============
		Vector v = new Vector(1,2,3);
		Double3 xyzToCheck = new Double3(1,2,3);
		assertTrue(v.equals(new Vector(xyzToCheck)), "the constractor is not reset with correct values");
		assertDoesNotThrow(()-> new Vector(0,0,1), "constractor throw exception with correct values");
		assertDoesNotThrow(()-> new Vector(0,1,0), "constractor throw exception with correct values");
		assertDoesNotThrow(()-> new Vector(1,0,0), "constractor throw exception with correct values");
		
	      // =============== Boundary Values Tests ==================

		Double3 xyz = new Double3(0,0,0);
		assertThrows(IllegalAccessException.class, () -> new Vector(0,0,0), "constractor does't throw Ilegal exception to zero vector");
		assertThrows(IllegalAccessException.class, () -> new Vector(xyz), "constractor does't throw Ilegal exception to zero vector");

	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
	      // ============ Equivalence Partitions Tests ==============
		// check if length() return correct value
		Vector v = new Vector(2,4,-4);
		assertEquals(6, v.length(),DELTA , "length() is not return correct value");
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(2,3,4);
		Vector result = new Vector(3,5,7);
		Vector negativV1 = new Vector(-1,-2,-3);
	      // ============ Equivalence Partitions Tests ==============
		assertTrue(result.equals(v1.add(v2)),"add() dosn't return correct values");
		assertDoesNotThrow( ()->v1.add(v2) ,"add() throw exception with correct values");
	      // =============== Boundary Values Tests ==================
		assertThrows(IllegalAccessException.class,() -> v1.add(negativV1), "add() dosn't return exception that add negative vactors");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		Vector v = new Vector(1,2,3);
		Double x = 2.0;
		Double y = -2.0;
		Vector resultX = new Vector(2,4,6);
		Vector resultY = new Vector(-2,-4,-6);
		// ============ Equivalence Partitions Tests ==============
		assertTrue(resultX.equals(v.scale(x)),"scale() dosn't return correct values");
		assertTrue(resultY.equals(v.scale(y)),"scale() dosn't return correct values");
		assertDoesNotThrow(()-> v.scale(x));
	      // =============== Boundary Values Tests ==================
		assertThrows(IllegalAccessException.class, ()->v.scale(0), "scale() dosn't throw exception with scalar 0");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(2,3,4);
		Vector unitVector = new Vector(0,0,1);
		Double result = 20.0;
		Vector vertical = new Vector(-1,-1,1);

		// ============ Equivalence Partitions Tests ==============
		assertEquals(result,v1.dotProduct(v2),DELTA ,"dotProduct() dosn't return correct values");
	      // =============== Boundary Values Tests ==================
		assertEquals(0, v1.dotProduct(vertical),DELTA ,"dotProduct() dosn't return correct values with verticals vectors");
		assertEquals(3, v1.dotProduct(unitVector),DELTA ,"dotProduct() dosn't return correct values with unit vector");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		Vector v1 = new Vector(3,2,1);
		Vector v2 = new Vector(6,5,4);
		Vector twiceV1 = new Vector(6,4,2);
		Vector negativTwiceV1 = new Vector(-6,-4,-2);
		Vector negativeV1 = new Vector(-3,-2,-1);		
		// ============ Equivalence Partitions Tests ==============
		assertTrue(v1.dotProduct(v1.crossProduct(v2)) == 0,"crossProduct() dosn't return correct values");
		assertTrue(v2.dotProduct(v2.crossProduct(v1)) == 0,"crossProduct() dosn't return correct values");
		// =============== Boundary Values Tests ==================
		assertThrows(IllegalAccessException.class,() -> v1.crossProduct(twiceV1), "crossProduct() dosn't throw exception with same direction vectors");
		assertThrows(IllegalAccessException.class,() -> v1.crossProduct(negativTwiceV1), "crossProduct() dosn't throw exception with negative direction");
		assertThrows(IllegalAccessException.class,() -> v1.crossProduct(negativeV1), "crossProduct() dosn't throw exception with same negative vector");
		assertThrows(IllegalAccessException.class,() -> v1.crossProduct(v1), "crossProduct() dosn't throw exception with same vector");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		Vector v = new Vector(1,2,3);
		Vector unitVector1 = new Vector(1,0,0);
		Vector unitVector2 = new Vector(0,1,0);
		Vector unitVector3 = new Vector(0,0,1);
		// ============ Equivalence Partitions Tests ==============
		assertTrue(v.normalize().length() == 1, "normalize() dosn't return correct values");
		// =============== Boundary Values Tests ==================	
		assertTrue(unitVector1.normalize().length() == 1, "normalize() dosn't return correct values with unit vector");
		assertTrue(unitVector2.normalize().length() == 1, "normalize() dosn't return correct values with unit vector");
		assertTrue(unitVector3.normalize().length() == 1, "normalize() dosn't return correct values with unit vector");
      }
	

}



