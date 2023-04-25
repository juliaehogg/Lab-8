package geometry_objects.angle.comparators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.points.Point;

class AngleStructureComparatorTest {
	
	// test various input to the compareTo method
	public static final int STRUCTURALLY_INCOMPARABLE = Integer.MAX_VALUE;
	
	@Test
	void test_incomparable_sameAngleDiffLocations() throws FactException {
		
		// same angle measure, different locations
		// vertices 
		Point vertex1 = new Point(1, 1);
		Point vertex2 = new Point(4, 3);

		// make the angles
		Angle one = new Angle(new Segment(vertex1, new Point(1, 4)), new Segment(vertex1, new Point(5, 1)));
		Angle two = new Angle(new Segment(vertex2, new Point(5, 3)), new Segment(vertex2, new Point(4, 10)));

		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(STRUCTURALLY_INCOMPARABLE, comp.compare(one, two));
	}
	
	
	@Test
	void test_incomparable_diffAngleSameLocation() throws FactException {
		
		// different angle measure, same location
		Point vertex = new Point(1, 1);

		// make the angles
		Angle one = new Angle(new Segment(vertex, new Point(1, 4)), new Segment(vertex, new Point(5, 1)));
		Angle two = new Angle(new Segment(vertex, new Point(4, 3)), new Segment(vertex, new Point(5, 1)));

		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(STRUCTURALLY_INCOMPARABLE, comp.compare(one, two));
	}
	
	
	@Test
	void test_incomparable_diffAngleDiffLocations() throws FactException {
		
		// different angle measure, different locations
		// vertices 
		Point vertex1 = new Point(1, 1);
		Point vertex2 = new Point(4, 3);

		// make the angles
		Angle one = new Angle(new Segment(vertex1, new Point(1, 4)), new Segment(vertex1, new Point(5, 1)));
		Angle two = new Angle(new Segment(vertex2, new Point(6, 5)), new Segment(vertex2, new Point(4, 10)));

		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(STRUCTURALLY_INCOMPARABLE, comp.compare(one, two));
	}

	
	@Test
	void test1_equals() throws FactException {
		
		// vertex 
		Point vertex = new Point(1, 1);
		
		// make the angles
		Angle one = new Angle(new Segment(vertex, new Point(1, 4)), new Segment(vertex, new Point(5, 1)));
		Angle two = new Angle(new Segment(vertex, new Point(1, 4)), new Segment(vertex, new Point(5, 1)));
		
		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(0, comp.compare(one, two));
	}
	
	@Test
	void test2_equals() throws FactException {
		
		// vertex 
		Point vertex = new Point(-2, -3);
		
		// make the angles
		Angle one = new Angle(new Segment(vertex, new Point(1, 5)), new Segment(vertex, new Point(8, 9)));
		Angle two = new Angle(new Segment(vertex, new Point(1, 5)), new Segment(vertex, new Point(8, 9)));
		
		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(0, comp.compare(one, two));
	}
	
	@Test
	void test_leftLongAndEqual() throws FactException {
		
		// vertex 
		Point vertex = new Point(1, 1);
		
		// make the angles
		Angle one = new Angle(new Segment(vertex, new Point(1, 6)), new Segment(vertex, new Point(5, 1)));
		Angle two = new Angle(new Segment(vertex, new Point(1, 4)), new Segment(vertex, new Point(5, 1)));
		
		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(1, comp.compare(one, two));
	}
	
	@Test
	void test_leftLong() throws FactException {
		
		// vertex 
		Point vertex = new Point(1, 1);
		
		// make the angles
		Angle one = new Angle(new Segment(vertex, new Point(1, 6)), new Segment(vertex, new Point(7, 1)));
		Angle two = new Angle(new Segment(vertex, new Point(1, 4)), new Segment(vertex, new Point(5, 1)));
		
		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(1, comp.compare(one, two));
	}
	
	@Test
	void test_rightLong() throws FactException {
		
		// vertex 
		Point vertex = new Point(1, 1);
		
		// make the angles
		Angle one = new Angle(new Segment(vertex, new Point(1, 4)), new Segment(vertex, new Point(5, 1)));
		Angle two = new Angle(new Segment(vertex, new Point(1, 6)), new Segment(vertex, new Point(7, 1)));
		
		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(-1, comp.compare(one, two));
	}
	
	@Test
	void test_rightLongAndEqual() throws FactException {
		
		// vertex 
		Point vertex = new Point(1, 1);
		
		// make the angles
		Angle one = new Angle(new Segment(vertex, new Point(1, 4)), new Segment(vertex, new Point(5, 1)));
		Angle two = new Angle(new Segment(vertex, new Point(1, 6)), new Segment(vertex, new Point(5, 1)));
		
		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(-1, comp.compare(one, two));
	}
	
	@Test
	void test_mismatch() throws FactException {
		
		// vertex 
		Point vertex = new Point(1, 1);
		
		// make the angles
		Angle one = new Angle(new Segment(vertex, new Point(1, 4)), new Segment(vertex, new Point(7, 1)));
		Angle two = new Angle(new Segment(vertex, new Point(1, 6)), new Segment(vertex, new Point(5, 1)));
		
		AngleStructureComparator comp = new AngleStructureComparator();
		
		assertEquals(0, comp.compare(one, two));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
