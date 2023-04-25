package geometry_objects.angle;
import utilities.EquivalenceClasses;
import utilities.LinkedEquivalenceClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//import geometry_objects.angle.comparators.AngleStructureComparator;
//import utilities.eq_classes.EquivalenceClasses;

/**
 * Given the figure below:
 * 
 *    A-------B----C-----------D
 *     \
 *      \
 *       \
 *        E
 *         \
 *          \
 *           F
 * 
 * Equivalence classes structure we want:
 * 
 *   canonical = BAE
 *   rest = BAF, CAE, DAE, CAF, DAF
 */
public class AngleEquivalenceClasses extends EquivalenceClasses<Angle>
{
	protected Comparator<Angle>		_comparator;
	protected List <LinkedEquivalenceClass<Angle>>		_classes;
	
	
	public AngleEquivalenceClasses(Comparator <Angle> comp)
	{
		super(comp);
		_classes = new ArrayList <LinkedEquivalenceClass<Angle>> ();
	}
	
	
	@Override
	public boolean add (Angle a)
	{
		int index = indexOfClass(a);
		if (index == -1) {
			LinkedEquivalenceClass<Angle> newClass = new LinkedEquivalenceClass<Angle>(_comparator);
			newClass.add(a);
	        return _classes.add(newClass);
	     } 
	     return _classes.get(index).add(a);
	     
	}
}