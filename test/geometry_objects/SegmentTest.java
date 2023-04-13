package geometry_objects;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;

class SegmentTest {
	
	@Test
	void testHasSubSegment()
	{
		Segment one = new Segment(new Point(1, 1), new Point(5,1));
		Segment two = new Segment(new Point(2, 1), new Point(3, 1));
		
		// valid subsegment
		assertTrue(one.HasSubSegment(two));
		assertFalse(two.HasSubSegment(one));
		
		// empty subsegment
		two = new Segment(new Point(0,0), new Point(0,0));
		assertFalse(one.HasSubSegment(two));
		
		// overlapping subsegment
		Segment three = new Segment(new Point(4, 1), new Point(7,1));
		assertFalse(one.HasSubSegment(three));
		
		// different slopes
		Segment four = new Segment(new Point(1, 1), new Point(5,2));
		assertFalse(one.HasSubSegment(four));
		
		// empty set 
		assertFalse(two.HasSubSegment(one));
		
		
	}
	
	@Test
	void testCoincideWithoutOverlap()
	{
		Segment one = new Segment(new Point (1,1), new Point(10, 1));
		Segment two = new Segment(new Point(10,1), new Point(18,1));
		
		// touching vertex
		assertTrue(one.coincideWithoutOverlap(two));
		
		// untouching but parallel
		Segment three = new Segment(new Point(-5, 1), new Point(0, 1));
		assertTrue(one.coincideWithoutOverlap(three));
		
		// different y planes
		Segment four = new Segment(new Point(1,2), new Point(10,2));
		assertFalse(one.coincideWithoutOverlap(four));
		
		// different slopes
		Segment five = new Segment(new Point(11, 1), new Point(15, 5));
		assertFalse(one.coincideWithoutOverlap(five));
		
		// empty subsegment
		five = new Segment(new Point(0,0), new Point(0,0));
		assertFalse(one.coincideWithoutOverlap(five));
		
		// empty segment
		assertFalse(five.coincideWithoutOverlap(one));
	}
	
	@Test
	void testCollectOrderedPointsOnSegment()
	{
		Segment one = new Segment(new Point(1, 1), new Point(15, 1));
		
		Set<Point> unOrderedPoints = new TreeSet<Point>();
		unOrderedPoints.add(new Point(3, 1));
		unOrderedPoints.add(new Point(8,1));
		unOrderedPoints.add(new Point(1,1));
		unOrderedPoints.add(new Point(4, 1));
		unOrderedPoints.add(new Point(14, 1));
		unOrderedPoints.add(new Point(2, 1));
		
		Set<Point> orderedPoints = new TreeSet<Point>();
		orderedPoints.add(new Point(1,1));
		orderedPoints.add(new Point(2, 1));
		orderedPoints.add(new Point(3, 1));
		orderedPoints.add(new Point(4, 1));
		orderedPoints.add(new Point(8,1));
		orderedPoints.add(new Point(14, 1));
		
		// all valid points
		assertEquals(orderedPoints.size(), one.collectOrderedPointsOnSegment(unOrderedPoints).size());
		assertTrue(orderedPoints.containsAll(one.collectOrderedPointsOnSegment(unOrderedPoints)));
		
		// some valid points some not
		unOrderedPoints.add(new Point(4, 7));
		unOrderedPoints.add(new Point(2, 3));
		unOrderedPoints.add(new Point(5, 6));
		
		assertEquals(orderedPoints.size(), one.collectOrderedPointsOnSegment(unOrderedPoints).size());
		assertTrue(orderedPoints.containsAll(one.collectOrderedPointsOnSegment(unOrderedPoints)));
		
		
		// all unvalid
		Set<Point> invalid = new TreeSet<Point>();
		invalid.add(new Point(4, 7));
		invalid.add(new Point(2, 3));
		invalid.add(new Point(5, 6));
	
		
		assertEquals(0, one.collectOrderedPointsOnSegment(invalid).size());
		
		// empty input
		invalid = new TreeSet<Point>();
		assertEquals(0, one.collectOrderedPointsOnSegment(invalid).size());
		
	}

}
