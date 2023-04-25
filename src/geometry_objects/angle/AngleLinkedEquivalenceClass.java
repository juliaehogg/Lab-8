package geometry_objects.angle;

import java.util.Comparator;
import java.util.LinkedList;

import geometry_objects.angle.comparators.AngleStructureComparator;
//import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.LinkedEquivalenceClass;

/**
 * This implementation requires greater knowledge of the implementing Comparator.
 * 
 * According to our specifications for the AngleStructureComparator, we have
 * the following cases:
 *
 *    Consider Angles A and B
 *    * Integer.MAX_VALUE -- indicates that A and B are completely incomparable
                             STRUCTURALLY (have different measure, don't share sides, etc. )
 *    * 0 -- The result is indeterminate:
 *           A and B are structurally the same, but it is not clear one is structurally
 *           smaller (or larger) than another
 *    * 1 -- A > B structurally
 *    * -1 -- A < B structurally
 *    
 *    We want the 'smallest' angle structurally to be the canonical element of an
 *    equivalence class.
 * 
 * @author XXX
 */
public class AngleLinkedEquivalenceClass extends LinkedEquivalenceClass<Angle>
{
	public AngleLinkedEquivalenceClass(Comparator<Angle> comp)
	{
		super(comp);
	}

	@Override
	public boolean add(Angle a)
	{
		// check if the angle is null 
		if (a == null) return false;

		// check if canonical exists, if not, angle is the new canonical
		if (_canonical == null)
		{
			_canonical = a;
			return true;
		}

		// check if the angle belongs in the class
		if (!belongs(a)) return false;

		// check if the angle already exists in the class
		if (contains(a)) return false;
		
		// check if it's the smallest angle - has the smallest rays 
		if (_canonical.compareTo(a) >= 0) { 
			
			super.demoteAndSetCanonical(a); 
			return true;
		}

		// add angle to rest 
		_rest.add(a);
		return true;
	}

	@Override
    public boolean contains(Angle a)
    {
		// check if the angle is null
		if (a == null) return false;
		
		
		
    	//canonical does not exist
        if (_canonical == null) {
            return false;
        }
        //if it exist, check if canonical is comparator to target
        if (_comparator.compare(_canonical, a) == 0) {
            return true;
        }
        //check if target is contained in the rest
        return _rest.contains(a);
    
    }
    
    @Override
    public boolean belongs(Angle a)
    {
    	if (_canonical == null) return false; 
    	
        //canonical compared to the target
        return _comparator.compare(_canonical, a) != AngleStructureComparator.STRUCTURALLY_INCOMPARABLE;
    }
    

}