package preprocessor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleEquivalenceClasses;
import geometry_objects.angle.comparators.AngleStructureComparator;

public class AngleIdentifier
{
	protected AngleEquivalenceClasses _angles;
	protected Map<Segment, Segment> _segments; // The set of ALL segments for this figure

	public AngleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}

	/*
	 * Compute the figure triangles on the fly when requested; memorize results for subsequent calls.
	 */
	public AngleEquivalenceClasses getAngles()
	{
		if (_angles != null) return _angles;

		// need to add comparator in argument 
		
		AngleStructureComparator comp = new AngleStructureComparator(); 
		_angles = new AngleEquivalenceClasses(comp);

		computeAngles();

		return _angles;
	}

	private void computeAngles()
	{
		ArrayList<Segment> segments = new ArrayList<Segment>(_segments.keySet());
		
		for(int i = 0; i < _segments.size() - 1; i++)
		{
			Segment seg1 = segments.get(i);
			for(int j = i + 1; j < _segments.size(); j++)
			{
				Segment seg2 = segments.get(j);
				try
				{
					_angles.add(new Angle(seg1, seg2));
				}
				catch(Exception e)
				{
					
				}
			}
		}
	}
}
