package preprocessor.delegates;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

public class ImplicitPointPreprocessor
{
	/**
	 * It is possible that some of the defined segments intersect
	 * and points that are not named; we need to capture those
	 * points and name them.
	 * 
	 * Algorithm:
	 *    TODO
	 */
	public static Set<Point> compute(PointDatabase givenPoints, List<Segment> givenSegments)
	{
		Set<Point> implicitPoints = new LinkedHashSet<Point>();
		// need more efficient search

		// search all segments
        for (Segment s : givenSegments)
        {
        	for (Segment s2 : givenSegments)
        	{
        		// save point that intersects
        		Point p = s.segmentIntersection(s2);
        		// if point is null, break
        		if (p != null) break;
        		// if the given point is not a vertex of a segment, then it will return null, so add to implicitPoints
        		if (s.other(p) == null)
        		{
        			implicitPoints.add(p);
        			givenPoints.put(p.getName(), p.getX(), p.getY());
        			
        			// come back and verify name :)
        		}
        	}
        }
        
		return implicitPoints;
	}

}
