package geometry_objects;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointNamingFactory;
class PointNamingFactoryTest {
	@Test
	void test_Constructor_empty() {
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
	}
	
	@Test
	void test_Constructor() {
		
		ArrayList<Point> list = new ArrayList<Point>();
		
		for (int i=0; i<100; i++) {
			list.add(new Point(i, i+2));
		}
		
		int size = list.size();
		
		PointNamingFactory factory = new PointNamingFactory(list);
		assertEquals(size, factory.size());
	}
	
	@Test
	void test_size() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		for (int i=0; i<100; i++) {
			factory.put(i, i+1);
			assertEquals(i+1, factory.size());
		}
	}
	
	@Test
	void test_clear() {
		
		ArrayList<Point> list = new ArrayList<Point>();
		
		for (int i=0; i<100; i++) {
			list.add(new Point(i, i+2));
		}
		
		int size = list.size();
		
		PointNamingFactory factory = new PointNamingFactory(list);
		assertEquals(size, factory.size());
		
		factory.clear();
		assertEquals(0, factory.size());
		
		for (int i=0; i<100; i++) {
			factory.put(i, i+1);
			assertEquals(i+1, factory.size());
		}
		
		factory.clear();
		assertEquals(0, factory.size());
	}
	
	
	@Test
	void test_put_nullInput() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		//assertNull(factory.put(null));
		//assertNull(factory.put(null, 1, 2));
	}
	
	@Test
	void test_put_byPoint() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		Point point1 = new Point(1, 2);
		Point point2 = new Point("named", 3, 4);
		Point point3 = Point.ORIGIN;
		
		Point point4 = new Point(1, 2);
		Point point5 = new Point("named", 3, 4);
		Point point6 = Point.ORIGIN;
		
		// test Point is not in factory
		assertFalse(factory.contains(point1));
		assertFalse(factory.contains(point2));
		assertFalse(factory.contains(point3));
		
		// test Point is in factory
		assertEquals(point1, factory.put(point4));
		assertTrue(factory.contains(point1));
		assertEquals(1, factory.size());
		
		assertEquals(point2, factory.put(point5));
		assertTrue(factory.contains(point2));
		assertEquals(2, factory.size());
		
		assertEquals(point3, factory.put(point6));
		assertTrue(factory.contains(point3));
		assertEquals(3, factory.size());
	}
	
	
	@Test
	void test_put_byXY() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		Point point1 = new Point(1, 2);
		Point point1_named = new Point("one", 1, 2);
		Point point2 = new Point(3, 4);
		Point point2_named = new Point("two", 3, 4);
		Point point3 = new Point(0, 0);
		Point origin = Point.ORIGIN;
		
		// test Point is not in factory
		assertFalse(factory.contains(point1));
		assertFalse(factory.contains(point2));
		assertFalse(factory.contains(point3));
		
		// test Point is in factory
		assertEquals(point1, factory.put(1, 2));
		assertEquals(point1_named, factory.put(1, 2));
		assertTrue(factory.contains(point1));
		assertTrue(factory.contains(point1_named));
		assertEquals(1, factory.size());
		
		assertEquals(point2, factory.put(3, 4));
		assertEquals(point2_named, factory.put(3, 4));
		assertTrue(factory.contains(point2));
		assertTrue(factory.contains(point2_named));
		assertEquals(2, factory.size());
		
		assertEquals(point3, factory.put(0, 0));
		assertEquals(origin, factory.put(0, 0));
		assertTrue(factory.contains(point3));
		assertTrue(factory.contains(origin));
		assertEquals(3, factory.size());
	}
	
	
	@Test
	void test_put_byNAMEXY() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		Point point1 = new Point(1, 2);
		Point point1_named = new Point("one", 1, 2);
		
		Point point2 = new Point(3, 4);
		Point point2_named = new Point("two", 3, 4);
		
		Point point3 = new Point(0, 0);
		Point origin = Point.ORIGIN;
		
		// test Point is not in factory
		assertFalse(factory.contains(point1));
		assertFalse(factory.contains(point2));
		assertFalse(factory.contains(point3));
		
		// test Point is in factory
		assertEquals(point1, factory.put("__UNNAMED", 1, 2));
		assertTrue(factory.contains(point1));
		assertEquals(1, factory.size());
		
		assertEquals(point1_named, factory.put("one", 1, 2));
		assertTrue(factory.contains(point1_named));
		assertEquals(1, factory.size());
		
		assertEquals(point2, factory.put("__UNNAMED", 3, 4));
		assertTrue(factory.contains(point2));
		assertEquals(2, factory.size());
		
		assertEquals(point2_named, factory.put("two", 3, 4));
		assertTrue(factory.contains(point2_named));
		assertEquals(2, factory.size());
		
		assertEquals(point3, factory.put("__UNNAMED", 0, 0));
		assertTrue(factory.contains(point3));
		assertEquals(3, factory.size());
		
		assertEquals(origin, factory.put("origin", 0, 0));
		assertTrue(factory.contains(origin));
		assertEquals(3, factory.size());
	}
	
	
	@Test
	void test_get_byPoint_null() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		Point point = new Point(1, 2);
		
		assertNull(factory.get(point));
	}
	
	
	@Test
	void test1_get_byPoint() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		Point point1 = new Point(1, 2);
		Point point2 = new Point("named", 3, 4);
		Point point3 = Point.ORIGIN;
		
		Point point4 = new Point(1, 2);
		Point point5 = new Point("named", 3, 4);
		Point point6 = Point.ORIGIN;
		
		// test Point is not in factory
		assertFalse(factory.contains(point1));
		assertFalse(factory.contains(point2));
		assertFalse(factory.contains(point3));
		
		// put Point in factory
		factory.put(point1);
		factory.put(point2);
		factory.put(point3);
		
		// test Point is in factory
		assertEquals(point1, factory.get(point4));
		assertEquals(point2, factory.get(point5));
		assertEquals(point3, factory.get(point6));
	}
	
	
	@Test
	void test2_get_byPoint() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		for (int i=0; i<100; i++) {
			Point point = new Point(i, i+1);
			factory.put(point);
			assertEquals(point, factory.get(point));
		}
	}
	
	
	@Test
	void test1_get_byXY() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		Point point1 = new Point(1, 2);
		Point point2 = new Point("named", 3, 4);
		Point point3 = Point.ORIGIN;
		
		Point point4 = new Point(1, 2);
		Point point5 = new Point("named", 3, 4);
		Point point6 = Point.ORIGIN;
		
		// test Point is not in factory
		assertFalse(factory.contains(point1));
		assertFalse(factory.contains(point2));
		assertFalse(factory.contains(point3));
		
		// put Point in factory
		factory.put(point1);
		factory.put(point2);
		factory.put(point3);
		
		// test Point is in factory
		assertEquals(point1, factory.get(1, 2));
		assertEquals(point4, factory.get(1, 2));
		assertEquals(point2, factory.get(3, 4));
		assertEquals(point5, factory.get(3, 4));
		assertEquals(point3, factory.get(0, 0));
		assertEquals(point6, factory.get(0, 0));
	}
	
	@Test
	void test2_get_byXY() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		for (int i=0; i<100; i++) {
			Point point = new Point(i, i+1);
			factory.put(point);
			assertEquals(point, factory.get(i, i+1));
		}
	}
	
	
	@Test
	void test_getAllPoints_null() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		Set<Point> point_set = factory.getAllPoints();
		assertEquals(point_set, new HashSet());
	}
	
	@Test
	void test_getAllPoints() {
		
		PointNamingFactory factory = new PointNamingFactory();
		assertEquals(0, factory.size());
		
		Set<Point> check_set = new HashSet<Point>();
		
		for (int i=0; i<100; i++) {
			Point point = new Point(i, i+1);
			check_set.add(point);
			factory.put(point);
			assertEquals(point, factory.get(point));
		}
		
		Set<Point> point_set = factory.getAllPoints();
		assertEquals(check_set, point_set);
	}
	
	
	@Test
	void test_naming() {
		
		PointNamingFactory test = new PointNamingFactory();
		
		char letter = 'A';
		
		//tests all letters
		for (int i=0; i<26; i++) {
			
			test.put(i, i);
			String actual_name = test.get(new Point(i, i)).getName();
			String test_name = "*_" + letter;
			assertEquals(actual_name, test_name);
			letter++;
		}
		
		//tests that it loops through after Z
		test.put(-1, -1);
		String name = test.get(new Point(-1, -1)).getName();
		assertEquals("*_AA", name);
		
	}
}






















