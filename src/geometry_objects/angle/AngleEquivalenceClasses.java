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

	public AngleEquivalenceClasses(Comparator <Angle> comp)
	{
		super(comp);
	}


	@Override
	public boolean add (Angle a)
	{		
		// check if null
		if (a == null) return false;

		// get index of angle 
		int index = indexOfClass(a);

		// if it belongs somewhere, add it 
		if (index != -1) { 
			
			System.out.println("added to rest: " + a);
			System.out.println("classes: " + _classes);
			
			return _classes.get(index).add(a); 
		
		
		}


		// add angle to new class 
		AngleLinkedEquivalenceClass newClass = new AngleLinkedEquivalenceClass(_comparator);
		newClass.add(a);

		System.out.println(a);

		// PROBLEM IS BELOW - NOT ADDING TO 
		//return _classes.add(newClass);
		_classes.add(newClass);

		System.out.println("classes: " + _classes);
		return true;

	}
}