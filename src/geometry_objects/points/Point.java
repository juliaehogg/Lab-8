package geometry_objects.points;

import utilities.math.MathUtilities;

/**
 * A 2D Point (x, y) only.
 * 
 * Points are ordered lexicographically (thus implementing the Comparable interface)
 * 
 * @author Hayes Brown, Grace Houser, Julia Hogg
 */
public class Point implements Comparable<Point>
{
	public static final String ANONYMOUS = "__UNNAMED";

	public static final Point ORIGIN;
	static
	{
		ORIGIN = new Point("origin", 0, 0);
	}

	protected double _x;
	public double getX() { return this._x; }

	protected double _y; 
	public double getY() { return this._y; }

	protected String _name; 
	public String getName() { return _name; }

	// BasicPoint objects are named points (from input)
	// ImpliedPoint objects are unnamed points (from input)
	public boolean isGenerated() { return false; }

	/**
	 * Create a new Point with the specified coordinates.
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public Point(double x, double y)
	{
		this(ANONYMOUS, x, y);
	}

	/**
	 * Create a new Point with the specified coordinates.
	 * @param name -- The name of the point. (Assigned by the UI)
	 * @param x -- The X coordinate
	 * @param y -- The Y coordinate
	 */
	public Point(String name, double x, double y)
	{
		_name = (name == null || name == "") ? ANONYMOUS : name;
		this._x = x;
		this._y = y;
	}

	/**
	 * @return if this point has not user-defined name associated with it
	 */
	public boolean isUnnamed()
	{
		return _name == ANONYMOUS;
	}

	@Override
	public int hashCode()
	{
		return Double.valueOf(MathUtilities.removeLessEpsilon(_x)).hashCode() +
			   Double.valueOf(MathUtilities.removeLessEpsilon(_y)).hashCode();
	}

	/**
	 * 
	 * @param p1 Point 1
	 * @param p2 Point 2
	 * @return Lexicographically: p1 < p2 return -1 : p1 == p2 return 0 : p1 > p2 return 1
	 *         Order of X-coordinates first; order of Y-coordinates second
	 */
	public static int LexicographicOrdering(Point p1, Point p2)
	{
		//checks x equality
		if(MathUtilities.doubleEquals(p1.getX(), p2.getX()))
		{
			//compares y
			if(MathUtilities.doubleEquals(p1.getY(), p2.getY()))
				return 0;
			
			if(p1.getY() > p2.getY())
				return 1;
			
			return -1;
		}
		
		//compares x
		else if(p1.getX() > p2.getX())
			return 1;
		else
			return -1;
	}

	@Override
	public int compareTo(Point that)
	{
		if (that == null) return 1;

		return Point.LexicographicOrdering(this, that);
	}
	
	@Override
	public boolean equals(Object obj)
	{
       if(!(obj instanceof Point))
    	   return false;
       
		Point pointObj = (Point)obj;
		
		return LexicographicOrdering(this, pointObj) == 0;
	}
	
	@Override
	public String toString()
	{
		return "(" + _x + ", " + _y + ")";
	}

	public static int distance(Point b, Point c) {
		// TODO Auto-generated method stub
		return 0;
	}
}