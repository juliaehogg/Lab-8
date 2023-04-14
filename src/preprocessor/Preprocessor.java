package preprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import preprocessor.delegates.ImplicitPointPreprocessor;
import geometry_objects.Segment;

/**
 * Processes implicit points, implicit segments, minimal segments, and non minimal segments
 * from a given database of points and set of segments 
 * 
 * @author Grace Houser
 * @author Julia Hogg
 */
public class Preprocessor
{
	// The explicit points provided to us by the user.
	// This database will also be modified to include the implicit
	// points (i.e., all points in the figure).
	protected PointDatabase _pointDatabase;

	// Minimal ('Base') segments provided by the user
	protected Set<Segment> _givenSegments;

	// The set of implicitly defined points caused by segments
	// at implicit points.
	protected Set<Point> _implicitPoints;

	// The set of implicitly defined segments resulting from implicit points.
	protected Set<Segment> _implicitSegments;

	// Given all explicit and implicit points, we have a set of
	// segments that contain no other subsegments; these are minimal ('base') segments
	// That is, minimal segments uniquely define the figure.
	protected Set<Segment> _allMinimalSegments;

	// A collection of non-basic segments
	protected Set<Segment> _nonMinimalSegments;

	// A collection of all possible segments: maximal, minimal, and everything in between
	// For lookup capability, we use a map; each <key, value> has the same segment object
	// That is, key == value. 
	protected Map<Segment, Segment> _segmentDatabase;
	public Map<Segment, Segment> getAllSegments() { return _segmentDatabase; }

	public Preprocessor(PointDatabase points, Set<Segment> segments)
	{
		_pointDatabase  = points;
		_givenSegments = segments;
		
		_segmentDatabase = new HashMap<Segment, Segment>();
		
		analyze();
	}

	/**
	 * Invoke the pre-computation procedure.
	 */
	public void analyze()
	{
		//
		// Implicit Points
		//
		_implicitPoints = ImplicitPointPreprocessor.compute(_pointDatabase, _givenSegments.stream().toList());

		//
		// Implicit Segments attributed to implicit points
		//
		_implicitSegments = computeImplicitBaseSegments(_implicitPoints);

		//
		// Combine the given minimal segments and implicit segments into a true set of minimal segments
		//     *givenSegments may not be minimal
		//     * implicitSegmen
		//
		_allMinimalSegments = identifyAllMinimalSegments(_implicitPoints, _givenSegments, _implicitSegments);

		//
		// Construct all segments inductively from the base segments
		//
		_nonMinimalSegments = constructAllNonMinimalSegments(_allMinimalSegments);

		//
		// Combine minimal and non-minimal into one package: our database
		//
		_allMinimalSegments.forEach((segment) -> _segmentDatabase.put(segment, segment));
		_nonMinimalSegments.forEach((segment) -> _segmentDatabase.put(segment, segment));
	}
	
	
	
	/**
	 * Find all segments that stem from an implicit point 
	 */
	public Set<Segment> computeImplicitBaseSegments(Set<Point> implicitPoints)
	{
		Set<Segment> segments = new HashSet<Segment>();
		
		Set<Point> pointDatabaseSet = _pointDatabase.getPoints();
		
		// loop through all given segments 
		for (Segment segment : _givenSegments) {
			
			// get the implicit points on the segment and make into an array 
			SortedSet<Point> pointsOnSegment = segment.collectOrderedPointsOnSegment(pointDatabaseSet);
			
			Point[] pointArray = pointsOnSegment.toArray(new Point[pointsOnSegment.size()]);
			
			// add all split up segments to the list segments 
			for (int i=0; i<pointArray.length-1; i++) {
				
				segments.add(new Segment(pointArray[i], pointArray[i+1]));
			}
		}
		
		// remove all given segments 
		segments.removeAll(_givenSegments);
		
		return segments;
	}
	
	

	/**
	 * Returns a set of all minimal segments 
	 */
	public Set<Segment> identifyAllMinimalSegments(Set<Point> implicitPoints, Set<Segment> givenSegments, Set<Segment> implicitSegments) {
		
		Set<Segment> segments = new HashSet<Segment>();
		
		// go through all segments
		for (Segment segment : givenSegments) {
		
			// if the segment does not have implicit points, add the segment
			if (segment.collectOrderedPointsOnSegment(implicitPoints).size() == 0) {
				
				segments.add(segment);
			}
		}
		
		// add all implicit segments 
		segments.addAll(implicitSegments);
		
		return segments;
	}
	
	
	
	/**
	 * Returns a set of all non minimal segments 
	 */
	public Set<Segment> constructAllNonMinimalSegments(Set<Segment> allMinimalSegments) {
		
		Set<Segment> allNonMinimalSegments = new HashSet<Segment>();
		
		Queue<Segment> queue = new LinkedList<Segment>(allMinimalSegments);
		
		// check a segment on the queue with allMinimalSegments until the queue is empty
		while (!queue.isEmpty()) {
			
			Segment seg = queue.remove();
			
			// loop through all minimal segments to see if a longer segment can be made 
			for (Segment minSeg : allMinimalSegments) {
				
				Point shared = seg.sharedVertex(minSeg);
				
				// check if segments coincide without overlap and share a vertex
        		if (seg.coincideWithoutOverlap(minSeg) && shared != null) {
        			
        			Point start = seg.other(shared);
        			Point end = minSeg.other(shared);
        			
        			Segment found = new Segment(start, end);
        			
        			// add the longer segment to allNonMinimalSegments and the queue 
        			allNonMinimalSegments.add(found);
        			
        			queue.add(found);
        		} 
			}
		}

		return allNonMinimalSegments;
	}
	
}










