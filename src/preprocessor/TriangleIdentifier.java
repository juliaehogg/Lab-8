package preprocessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;
import geometry_objects.angle.Angle;

public class TriangleIdentifier
{
	protected Set<Triangle>         _triangles;
	protected Map<Segment, Segment> _segments; // The set of ALL segments for this figure.

	public TriangleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}

	/*
	 * Compute the figure triangles on the fly when requested;
	 * memorize results for subsequent calls.
	 */
	public Set<Triangle> getTriangles()
	{
		if (_triangles != null) return _triangles;

		_triangles = new HashSet<Triangle>();

		computeTriangles();

		return _triangles;
	}

	private void computeTriangles()
	{
		ArrayList<Segment> segments = new ArrayList<Segment>(_segments.keySet());
		
		for(int i = 0; i < _segments.size() - 1; i++)
		{
			Segment seg1 = segments.get(i);
			for(int j = 1; j < _segments.size(); j++)
			{
				Segment seg2 = segments.get(j);
				
				for (int k = 2; k < segments.size(); k++)
				{
					Segment seg3 = segments.get(k);
					List<Segment> segs = new ArrayList<Segment>();
					segs.add(seg1);
					segs.add(seg2);
					segs.add(seg3);
				try
				{
					_triangles.add(new Triangle(segs));
				}
				catch(Exception e)
				{
				
				}
				}
			}
				
		}
		
	}
}
