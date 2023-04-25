/**
 * Write a succinct, meaningful description of the class here. You should avoid wordiness    
 * and redundancy. If necessary, additional paragraphs should be preceded by <p>,
 * the html tag for a new paragraph.
 *
 * <p>Bugs: (a list of bugs and / or other problems)
 *
 * @author <your name>
 * @date   <date of completion>
 */

package geometry_objects.angle;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.points.Point;
import utilities.math.MathUtilities;

public class Angle implements Comparable<Angle>
{
	protected Segment _ray1;
	protected Segment _ray2;
	public Segment getRay1() { return _ray1; }
	public Segment getRay2() { return _ray2; }
	
	protected Point _ray1Endpoint;
	protected Point _vertex;
	protected Point _ray2Endpoint;
	
	protected double _measure; 
	public double getMeasure() { return _measure; }

	public Point getVertex() { return _vertex; }
	
	public Angle(Segment ray1, Segment ray2)  throws FactException
	{
		Point vertex = ray1.sharedVertex(ray2);
		
		if (vertex == null) throw new FactException("Shared endpoint not found.");
		
		initAngle(vertex, ray1, ray2);
	}
	
	/**
	 * Common initialization routine for angles
	 * @param a -- A point defining the angle.
	 * @param b --  A point defining the angle. This is the vertex point.
	 * @param c --  A point defining the angle.
	 */
	private void initAngle(Point vertex, Segment r1, Segment r2) throws FactException
	{
		Point other1 = r1.other(vertex);
		Point other2 = r2.other(vertex);
		
		if (vertex.equals(other1) || vertex.equals(other2) || other1.equals(other2))
			throw new FactException("Angle constructed with redundant vertices.");

		_ray1Endpoint = other1;
		_vertex = vertex;
		_ray2Endpoint = other2;

		_ray1 = r1;
		_ray2 = r2;

		_measure = Math.toDegrees(findAngle(_ray1Endpoint, _vertex, _ray2Endpoint));

		if (_measure <= 0) throw new FactException("Measure of " + this.toString() + " is ZERO");
	}
	
	/**
	 * Find the measure of the angle (in radians) specified by the three points.
	 * Uses Law of Cosines to compute angle
	 * 
	 * @param a -- A point defining the angle.
	 * @param b --  A point defining the angle. This is the vertex of the angle
	 * @param c -- A point defining the angle.
	 * @return The measure of the angle (in radians) specified by the three points.
	 */
	public static double findAngle(Point a, Point b, Point c)
	{
		double v1x = a.getX() - b.getX();
		double v1y = a.getY() - b.getY();
		double v2x = c.getX() - b.getX();
		double v2y = c.getY() - b.getY();
		double dotProd = v1x * v2x + v1y * v2y;
		double cosAngle = dotProd / (Point.distance(a, b) * Point.distance(b, c));

		// Avoid minor calculation issues and retarget the given value to specific angles. 
		// 0 or 180 degrees
		if (MathUtilities.doubleEquals(Math.abs(cosAngle), 1) ||
			MathUtilities.doubleEquals(Math.abs(cosAngle), -1))
		{
			cosAngle = cosAngle < 0 ? -1 : 1;
		}

		// 90 degrees
		if (MathUtilities.doubleEquals(cosAngle, 0)) cosAngle = 0;

		return Math.acos(cosAngle);
	}
	
	


	/**
	 * An angle is foremost distinct from an another angle based on their measures.
	 * 
	 * Secondarily, an angle is smaller than another angle if the constituent rays
	 * are shorter than another.
	 * 
	 *          C
	 *         /
	 *        /
	 *       B
	 *      /
	 *     /
	 *    A------X------Y
	 *    
	 *    BAX = XAB
	 *    BAX < CAX
	 *    CAX > BAX
	 *    
	 *    CAX = BAY since this implies that 
	 */
	@Override
	public int compareTo(Angle that)
	{
		return (int)(_measure - that._measure);
	}

    /*
	 * @param ray -- a ray
	 * @return true / false whether the @ray overlays one of this angle's rays 
	 */
    public boolean overlays(Angle that)
    {
		// Same vertex
		if (!_vertex.equals(that.getVertex())) return false;
		
    	Segment overlays1 = overlayingRay(that._ray1);
    	Segment overlays2 = overlayingRay(that._ray2);
    	
    	// Segment 1 and 2 do NOT align with that angle
    	return overlays1 != null && overlays2 != null;
    }
	
    /*
	 * @param ray -- a ray
	 * @return true / false whether the @ray overlays one of this angle's rays 
	 */
    public Segment overlayingRay(Segment that)
    {
    	// Must share a vertex
    	Point shared = that.other(_vertex);
    	if (shared == null) return null;

    	// Individual overlaying
    	if (Segment.overlaysAsRay(_ray1, that)) return _ray1;
    	
    	if (Segment.overlaysAsRay(_ray2, that)) return _ray2;

    	// No overlaying segment
    	return null;
    }
	
	@Override
	public String toString()
	{
		return "Angle( m" + _ray1Endpoint.getName() +
				            _vertex.getName() +
				            _ray2Endpoint.getName() +
				            " = " + String.format("%1$.3f", _measure) + ")";
	}

	@Override
	public boolean equals(Object obj)
	{
		// check that the given object is an Angle 
		if (! (obj instanceof Angle)) { return false; }
		Angle angle2 = (Angle) obj;
		
		// check if the angle vertex is the same and if the end points are unique 
		if (_vertex.equals(angle2.getVertex()) && !(_ray1Endpoint.equals(_ray2Endpoint))) {
			
			// check if this end point equals that end point 
			if (_ray1Endpoint.equals(angle2._ray1Endpoint) || _ray1Endpoint.equals(angle2._ray2Endpoint)) {
				
				if (_ray2Endpoint.equals(angle2._ray1Endpoint) || _ray1Endpoint.equals(angle2._ray2Endpoint)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	// do we need this method ??? 
	public boolean equivalent(Object obj)
	{
		// check that the given object is an Angle 
		if (! (obj instanceof Angle)) { return false; }
		Angle angle2 = (Angle) obj;
		
		// check if the angle overlaps 
		if (!overlays(angle2)) { return false; }
		
		if(this.getVertex().compareTo(angle2.getVertex()) == 0) return true;
				
		return false;
	}
	
	
	
	
}