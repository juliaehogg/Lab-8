package geometry_objects.angle;

import java.util.Comparator;
import java.util.LinkedList;

import geometry_objects.angle.comparators.AngleStructureComparator;
import geometry_objects.LinkedEquivalenceClass;

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
	private Angle canonical;
	private Comparator<Angle> comparator;
	private LinkedList rest; 
	
    public AngleLinkedEquivalenceClass(Comparator<Angle> comp)
    {
    	super();
    	this.comparator = comp;
    }
}