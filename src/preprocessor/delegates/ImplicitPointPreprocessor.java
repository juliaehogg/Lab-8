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
		
		// search all segments
        for (int i=0; i<givenSegments.size(); i++) {
        	
        	// get the first segment 
        	Segment seg = givenSegments.get(i);
        	
        	for (int j=i+1; j<givenSegments.size(); j++) {
        		
        		// get the second segment 
            	Segment seg2 = givenSegments.get(j);
        		
        		// save point that intersects the segments 
        		Point point = seg.segmentIntersection(seg2);
        		
        		// if there is no intersecting point, break
        		if (point == null) break;
        		
        		// if the given point is not a vertex of a segment, then it will return null, so add to implicitPoints
        		if (seg.other(point) == null)
        		{
        			implicitPoints.add(point);
        			givenPoints.put(point.getName(), point.getX(), point.getY());
        			
        			// come back and verify name :)
        		}
        	}
        }
        
		return implicitPoints;
	}

}
