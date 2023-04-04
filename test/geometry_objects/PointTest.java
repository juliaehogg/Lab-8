package geometry_objects;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
class PointTest {
	@Test
	void test_Constructor() {
		
		//tests getX, getY, and getName
		
		//tests an unnamed point
		Point test1 = new Point(1, 2);
		
		assertEquals(test1.getX(), 1);
		assertEquals(test1.getY(), 2);
		assertEquals(test1.getName(), "__UNNAMED");
		
		//tests a named point
		Point test2 = new Point("test point", 3, 4);
		
		assertEquals(test2.getX(), 3);
		assertEquals(test2.getY(), 4);
		assertEquals(test2.getName(), "test point");
		
		//tests the origin
		Point origin = Point.ORIGIN;
		
		assertEquals(origin.getX(), 0);
		assertEquals(origin.getY(), 0);
		assertEquals(origin.getName(), "origin");
		
	}
	
	@Test
	void test_LexicographicOrdering()
	{
		
		Point least = new Point(0, 0);
		Point middle = new Point(0, 1);
		Point most = new Point (1, 0);
		
		assertEquals(Point.LexicographicOrdering(least, middle), -1);
		assertEquals(Point.LexicographicOrdering(least, most), -1);
		assertEquals(Point.LexicographicOrdering(middle, least), 1);
		assertEquals(Point.LexicographicOrdering(middle, most), -1);
		assertEquals(Point.LexicographicOrdering(most, middle), 1);
		assertEquals(Point.LexicographicOrdering(most, least), 1);
		assertEquals(Point.LexicographicOrdering(least, least), 0);
		assertEquals(Point.LexicographicOrdering(middle, middle), 0);
		assertEquals(Point.LexicographicOrdering(most, most), 0);
		
	}
	
	
	@Test
	void test_IsUnnamed() {
		
		Point test1 = new Point(-10, -20);
		assertTrue(test1.isUnnamed());
		
		Point test2 = new Point(23, 40);
		assertTrue(test2.isUnnamed());
		
		Point test3 = new Point(Math.E, Math.sqrt(2));
		assertTrue(test3.isUnnamed());
	}
	
	
	@Test
	void test_IsNotUnnamed() {
		
		Point origin = Point.ORIGIN;
		assertFalse(origin.isUnnamed());
		
		Point test1 = new Point("A", -10, -20);
		assertFalse(test1.isUnnamed());
		
		Point test2 = new Point("Name", 23, 40);
		assertFalse(test2.isUnnamed());
		
		Point test3 = new Point("Fancy", Math.E, Math.sqrt(2));
		assertFalse(test3.isUnnamed());
	}
	
	
	@Test
	void test_ComapreTo_nulls() {
		
		Point origin = Point.ORIGIN;
		Point test1 = new Point(1, 2);
		Point test2 = null;
		
		assertEquals(1, origin.compareTo(null));
		assertEquals(1, test1.compareTo(null));
		assertEquals(1, origin.compareTo(test2));
		assertEquals(1, test1.compareTo(test2));
	}
	
	
	@Test
	void test_ComapreTo() {
		
		Point least = new Point(0, 0);
		Point middle = new Point(0, 5);
		Point most = new Point (5, 0);
		
		Point least2 = new Point(0, 0);
		Point middle2 = new Point(0, 5);
		Point most2 = new Point (5, 0);
		
		assertEquals(-1, least.compareTo(middle));
		assertEquals(-1, least.compareTo(most));
		assertEquals(-1, middle.compareTo(most));
		
		assertEquals(1, middle.compareTo(least));
		assertEquals(1, most.compareTo(middle));
		assertEquals(1, most.compareTo(least));
		
		assertEquals(0, least.compareTo(least));
		assertEquals(0, middle.compareTo(middle));
		assertEquals(0, most.compareTo(most));
		assertEquals(0, least.compareTo(least2));
		assertEquals(0, middle.compareTo(middle2));
		assertEquals(0, most.compareTo(most2));
	}
	
	
	@Test
	void test2_ComapreTo() {
		
		Point least = new Point(-10, -3);
		Point middle = new Point(1, 2);
		Point most = new Point (5, 1);
		
		Point least2 = new Point(-10, -3);
		Point middle2 = new Point(1, 2);
		Point most2 = new Point (5, 1);
		
		assertEquals(-1, least.compareTo(middle));
		assertEquals(-1, least.compareTo(most));
		assertEquals(-1, middle.compareTo(most));
		
		assertEquals(1, middle.compareTo(least));
		assertEquals(1, most.compareTo(middle));
		assertEquals(1, most.compareTo(least));
		
		assertEquals(0, least.compareTo(least));
		assertEquals(0, middle.compareTo(middle));
		assertEquals(0, most.compareTo(most));
		assertEquals(0, least.compareTo(least2));
		assertEquals(0, middle.compareTo(middle2));
		assertEquals(0, most.compareTo(most2));
	}
	
	
	@Test
	void test3_ComapreTo() {
		
		Point least = new Point(-100, -Math.E);
		Point middle = new Point(-2, -2);
		Point most = new Point (Math.PI, 3.14);
		
		Point least2 = new Point(-100, -Math.E);
		Point middle2 = new Point(-2, -2);
		Point most2 = new Point (Math.PI, 3.14);
		
		assertEquals(-1, least.compareTo(middle));
		assertEquals(-1, least.compareTo(most));
		assertEquals(-1, middle.compareTo(most));
		
		assertEquals(1, middle.compareTo(least));
		assertEquals(1, most.compareTo(middle));
		assertEquals(1, most.compareTo(least));
		
		assertEquals(0, least.compareTo(least));
		assertEquals(0, middle.compareTo(middle));
		assertEquals(0, most.compareTo(most));
		assertEquals(0, least.compareTo(least2));
		assertEquals(0, middle.compareTo(middle2));
		assertEquals(0, most.compareTo(most2));
	}
}

