package geometry_objects.points;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Given a pair of coordinates; generate a unique name for it;
 * return that point object.
 *
 * Names go from A..Z..AA..ZZ..AAA...ZZZ  (a name such as ABA does not occur)
 * 
 * @author Hayes Brown, Grace Houser, Julia Hogg
 */




public class PointNamingFactory
{
	// Prefix associated with each generated name so those names are easily distinguishable
	private static final String _PREFIX = "*_";

    // Constants reflecting our naming characters for generated names.
	private static final char START_LETTER = 'A';
	private static final char END_LETTER = 'Z';

    //
    // the number of characters in the generated names:
	// "A" and 1 -> "A"
	// "B" and 3 -> "BBB"
	//
	private String _currentName = "A";
	private int _numLetters = 1;

	//
	// A hashed container for the database of points; this requires the Point
	// class implement equals based solely on the individual coordinates and
	// not a name. We need a get() method; HashSet doesn't offer one.
	// Each entry is a <Key, Value> pair where Key == Value
	//
	protected Map<Point, Point> _database;

	public PointNamingFactory()
	{
		_database = new LinkedHashMap<Point, Point>();
	}

	/**
	 * Initialize the database with points; must call put() to ensure all points are named
	 *
	 * @param points -- a list of points, named or not named
	 */
	public PointNamingFactory(List<Point> points)
	{
		_database = new HashMap<Point, Point>();
		for (Point p : points)
		{
			_database.put(p, p);
		}
	}

	/**
	 * Overloaded add / lookup mechanism for this database.
	 *
	 * @param pt -- a Point object (may or may not be named)
	 
	 * @return THE point object in the database corresponding to its coordinate pair
                    * the object in the database if it already exists or
					* a completely new point that has been added to the database
	 */
	public Point put(Point pt)
	{
		//names point
		if(pt.getName() == Point.ANONYMOUS)
			pt._name = getCurrentName();
		
		//adds point
		Point p = _database.put(pt, pt);
		
		//returns the point already at that address or a new 
		return (p != null) ?  p : pt;
	}

	/**
	 * Overloaded add / lookup mechanism for this database for an unnamed coordinate pair.
	 *
	 * @param x -- single coordinate
	 * @param y -- single coordinate

	 * @return THE point object in the database corresponding to its coordinate pair
                    * the object in the database if it already exists or
					* a completely new point that has been added to the database (with generated name)
	 */
	public Point put(double x, double y)
	{
		//adds default name
		String name = getCurrentName();
		
		Point p = _database.put(new Point(name, x, y), new Point(name, x, y));
		
		//if there is already a point there, return that, otherwise return input point
		if (p != null) return p;
		return new Point(x, y);
	}

	/**
	 * The 'main' overloaded add / lookup mechanism for this database.
	 * 
	 * @param name -- the name of the point 
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * 
	 * @return a point (if it already exists in the database) or a completely new point that
	 *         has been added to the database.
	 *         
	 *         If the point is in the database and the name differs from what
	 *         is given, nothing in the database will be changed; essentially
	 *         this means we use the first name given for a point.
	 *            e.g., a valid name cannot overwrite an existing valid name ;
	 *                  a generated name cannot be overwritten by another generated name
	 *         
	 *         The exception is that a valid name can overwrite an unnamed point.
	 */
	public Point put(String name, double x, double y)
	{
		// test to check if it overwrites unnamed
		//make object, then put in both
		Point p = _database.put(new Point(name, x, y), new Point(name, x, y));
		if (p != null) return p;
		return new Point(name, x, y);
	}    

	/**
	 * Strict access (read-only of the database)
	 * 
	 * @param x
	 * @param y
	 * @return stored database Object corresponding to (x, y) 
	 */
	public Point get(double x, double y)
	{
		//catches if not found
		try {
			return _database.get(new Point(x,y));
		}
		catch (NullPointerException e) {
			return null;
		}
	}	
	
	public Point get(Point pt)
	{
		//catches if not found
		try {
			return _database.get(pt);
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * @return simple containment; no updating
	 */
	public boolean contains(double x, double y) { return _database.containsKey(new Point(x, y)); }
	public boolean contains(Point p) { return _database.containsKey(p); }

	/**
	 * Constructs the next (complete with prefix) generated name.
	 * Names should be of the form PREFIX + current name
	 *
	 * This method should also invoke updating of the current name
	 * to reflect the 'next' name in the sequence.
     *	 
	 * @return the next complete name in the sequence including prefix.
	 */
	private String getCurrentName()
	{
        String name = _PREFIX + _currentName;
        updateName();
        return name;
	}

	/**
	 * Advances the current generated name to the next letter in the alphabet:
	 * 'A' -> 'B' -> 'C' -> 'Z' --> 'AA' -> 'BB'
	 */
	private void updateName()
	{
        char letter = _currentName.charAt(0);
        
        if(letter == END_LETTER)
        {
        	letter = START_LETTER;
        	_numLetters++;
        }
        else
        	letter++;
        
        String name = "";
        
        for(int i = 0; i < _numLetters; i++)
        	name += letter;
        
        _currentName = name;
	}

	/**
	 * @return The entire database of points.
	 */
	public  Set<Point> getAllPoints()
	{
		Set<Entry<Point, Point>> set = _database.entrySet();
		Set<Point> points = new HashSet<Point>();
		
		for (Entry<Point,Point> p : set)
			points.add(p.getKey());
		
		return points;
		
		
	}

	public void clear() { _database.clear(); }
	public int size() { return _database.size(); }

	@Override
	public String toString()
	{
        String data = "";
		
		Set<Point> allPoints = getAllPoints();
		
		for(Point point : allPoints)
			data += point.getName() + "(" + point.getX() + ", " + point.getY() + "), ";
		
		//gets rid of final comma
		return data.substring(0, data.length() - 2);
	}
}