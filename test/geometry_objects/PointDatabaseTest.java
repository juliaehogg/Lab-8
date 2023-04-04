package geometry_objects;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
class PointDatabaseTest {
	@Test
	void test_Constructor_empty() {
		
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
	}
	
	
	@Test
	void test_Constructor() {
		
		ArrayList<Point> list = new ArrayList<Point>();
		
		for (int i=0; i<100; i++) {
			list.add(new Point(i, i+2));
		}
		
		int size = list.size();
		
		PointDatabase pd = new PointDatabase(list);
		assertEquals(size, pd.size());
	}
	
	
	
	
	
	
	
	
	@Test
	void test1_put() {
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		for (int i=0; i<100; i++) {
			pd.put("name" + i, i, i+2);
			assertEquals(i+1, pd.size());
		}
		
		assertEquals(100, pd.size());
	}
	
	
	@Test
	void test2_put() {
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		for (int i=0; i<100; i++) {
			pd.put("name" + i, Math.random(), Math.random());
			assertEquals(i+1, pd.size());
		}
		
		assertEquals(100, pd.size());
	}
	
	
	
	
	
	
	
	@Test
	void test1_getName_byXY() {
		
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		// test Point is not in the database
		assertNull(pd.getPoint(new Point(1, 1)));
		
		pd.put(null, 1, 2);
		pd.put("named", 3, 4);
		
		// test Point is in the database
		assertEquals("__UNNAMED", pd.getName(1, 2));
		assertEquals("named", pd.getName(3, 4));
	}
	
	
	@Test
	void test2_getName_byXY() {
		
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		for (int i=0; i<100; i++) {
			if (i % 2 == 0) {
				//assertNull(pd.getName(i, i));
				pd.put(null, i, i);
				assertEquals("__UNNAMED", pd.getName(i, i));
			}
			else {
				//assertNull(pd.getName(i, i));
				pd.put("named" + i, i, i);
				assertEquals("named" + i, pd.getName(i, i));
			}
		}
		assertEquals(100, pd.size());
	}
	
	
	@Test
	void test_getName_byPoint() {
		
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		Point point1 = new Point(1, 2);
		Point point2 = new Point("named", 3, 4);
		Point point3 = Point.ORIGIN;
		
		// test Point is not in the database
		assertNull(pd.getPoint(point1));
		assertNull(pd.getPoint(point2));
		assertNull(pd.getPoint(point3));
		
		pd.put(null, 1, 2);
		pd.put("named", 3, 4);
		pd.put("origin", 0, 0);
		
		// test Point is in the database
		assertEquals("__UNNAMED", pd.getName(point1));
		assertEquals("named", pd.getName(point2));
		assertEquals("origin", pd.getName(point3));
	}
	
	
	
	
	
	
	
	@Test
	void test_getPoint_byName() {
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		Point point1 = new Point(1, 2);
		Point point2 = new Point("named", 3, 4);
		Point point3 = Point.ORIGIN;
		
		// test Point is not in the database
		assertNull(pd.getPoint(point1));
		assertNull(pd.getPoint(point2));
		assertNull(pd.getPoint(point3));
		
		pd.put(null, 1, 2);
		pd.put("named", 3, 4);
		pd.put("origin", 0, 0);
		
		// test Point is in the database
		assertEquals(point1, pd.getPoint("__UNNAMED"));
		assertEquals(point2, pd.getPoint("named"));
		assertEquals(point3, pd.getPoint("origin"));
	}
	
	@Test
	void test1_getPoint_byXY() {
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		Point point1 = new Point(1, 2);
		Point point2 = new Point("named", 3, 4);
		Point point3 = Point.ORIGIN;
		
		Point point4 = new Point(1, 2);
		Point point5 = new Point("named", 3, 4);
		Point point6 = Point.ORIGIN;
		
		// test Point is not in the database
		assertNull(pd.getPoint(point1));
		assertNull(pd.getPoint(point2));
		assertNull(pd.getPoint(point3));
		assertNull(pd.getPoint(point4));
		assertNull(pd.getPoint(point5));
		assertNull(pd.getPoint(point6));
		
		pd.put(null, 1, 2);
		pd.put("named", 3, 4);
		pd.put("origin", 0, 0);
		
		// test Point is in the database
		assertEquals(point1, pd.getPoint(1, 2));
		assertEquals(point4, pd.getPoint(1, 2));
		assertEquals(point2, pd.getPoint(3, 4));
		assertEquals(point5, pd.getPoint(3, 4));
		assertEquals(point3, pd.getPoint(0, 0));
		assertEquals(point6, pd.getPoint(0, 0));
	}
	
	
	@Test
	void test2_getPoint_byXY() {
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		Point point1 = new Point(Math.E, Math.PI);
		Point point2 = new Point(3, Math.sqrt(2));
		Point point3 = Point.ORIGIN;
		
		Point point4 = new Point(Math.E, Math.PI);
		Point point5 = new Point(3, Math.sqrt(2));
		Point point6 = Point.ORIGIN;
		
		// test Point is not in the database
		assertNull(pd.getPoint(point1));
		assertNull(pd.getPoint(point2));
		assertNull(pd.getPoint(point3));
		assertNull(pd.getPoint(point4));
		assertNull(pd.getPoint(point5));
		assertNull(pd.getPoint(point6));
		
		pd.put(null, Math.E, Math.PI);
		pd.put(null, 3, Math.sqrt(2));
		pd.put("origin", 0, 0);
		
		// test Point is in the database
		assertEquals(point1, pd.getPoint(Math.E, Math.PI));
		assertEquals(point4, pd.getPoint(Math.E, Math.PI));
		assertEquals(point2, pd.getPoint(3, Math.sqrt(2)));
		assertEquals(point5, pd.getPoint(3, Math.sqrt(2)));
		assertEquals(point3, pd.getPoint(0, 0));
		assertEquals(point6, pd.getPoint(0, 0));
	}
	
	@Test
	void test_getPoint_byPoint() {
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		Point point1 = new Point(1, 2);
		Point point2 = new Point("named", 3, 4);
		Point point3 = Point.ORIGIN;
		
		Point point4 = new Point(1, 2);
		Point point5 = new Point("named", 3, 4);
		Point point6 = Point.ORIGIN;
		
		// test point is not in the database
		assertNull(pd.getPoint(point1));
		assertNull(pd.getPoint(point2));
		assertNull(pd.getPoint(point3));
		assertNull(pd.getPoint(point4));
		assertNull(pd.getPoint(point5));
		assertNull(pd.getPoint(point6));
		
		pd.put(null, 1, 2);
		pd.put("named", 3, 4);
		pd.put("origin", 0, 0);
		
		// test Point is in the database
		assertEquals(point1, pd.getPoint(point4));
		assertEquals(point4, pd.getPoint(point1));
		assertEquals(point2, pd.getPoint(point5));
		assertEquals(point5, pd.getPoint(point2));
		assertEquals(point3, pd.getPoint(point6));
		assertEquals(point6, pd.getPoint(point3));
	}
	
	
	@Test
	void test2_getPoint_byPoint() {
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
		
		Point point1 = new Point(Math.E, Math.PI);
		Point point2 = new Point(3, Math.sqrt(2));
		Point point3 = Point.ORIGIN;
		
		Point point4 = new Point(Math.E, Math.PI);
		Point point5 = new Point(3, Math.sqrt(2));
		Point point6 = Point.ORIGIN;
		
		// test Point is not in the database
		assertNull(pd.getPoint(point1));
		assertNull(pd.getPoint(point2));
		assertNull(pd.getPoint(point3));
		assertNull(pd.getPoint(point4));
		assertNull(pd.getPoint(point5));
		assertNull(pd.getPoint(point6));
		
		pd.put(null, Math.E, Math.PI);
		pd.put(null, 3, Math.sqrt(2));
		pd.put("origin", 0, 0);
		
		// test Point is in the database
		assertEquals(point1, pd.getPoint(point4));
		assertEquals(point4, pd.getPoint(point1));
		assertEquals(point2, pd.getPoint(point5));
		assertEquals(point5, pd.getPoint(point2));
		assertEquals(point3, pd.getPoint(point6));
		assertEquals(point6, pd.getPoint(point3));
	}
	
}