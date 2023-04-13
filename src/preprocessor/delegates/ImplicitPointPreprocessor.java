package preprocessor.delegates;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

/**
 * Processes implicit points from given points and segments 
 * 
 * @author Grace Houser
 * @author Julia Hogg
 */
public class ImplicitPointPreprocessor
{
	/**
	 * It is possible that some of the defined segments intersect
	 * and points that are not named; we need to capture those
	 * points and name them.
	 * 
	 * Algorithm:
	 *    Go through all segment pairs and add if there is an intersecting point  
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
        		
        		// if there is an intersecting point, and 
        		// if the given point is not a vertex of a segment, add to implicitPoints
        		if (point != null && seg.other(point) == null)
        		{
        			implicitPoints.add(point);
        			
        			// updating the name of the point 
        			givenPoints.put(point.getName(), point.getX(), point.getY());
        		}
        	}
        }
        
		return implicitPoints;
	}
}