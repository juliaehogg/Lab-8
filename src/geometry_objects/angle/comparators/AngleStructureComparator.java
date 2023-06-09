/**
 * Comparator for two Angles. 
 *
 * @author Grace Houser
 * @author Julia Hogg
 * @date   25 April 2023 
 */

package geometry_objects.angle.comparators;

import java.util.Comparator;

import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.points.Point;
import utilities.math.MathUtilities;
import utilities.math.analytic_geometry.GeometryUtilities;

public class AngleStructureComparator implements Comparator<Angle>
{
	public static final int STRUCTURALLY_INCOMPARABLE = Integer.MAX_VALUE;
	
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
	 * What we care about is the fact that angle BAE is the smallest angle (structurally)
	 * and DAF is the largest angle (structurally). 
	 * 
	 * If one angle X has both rays (segments) that are subsegments of an angle Y, then X < Y.
	 * 
	 * If only one segment of an angle is a subsegment, then no conclusion can be made.
	 * 
	 * So:
	 *     BAE < CAE
   	 *     BAE < DAF
   	 *     CAF < DAF

   	 *     CAE inconclusive BAF
	 * 
	 * @param left -- an angle
	 * @param right -- an angle
	 * @return -- according to the algorithm above:
 	 *              Integer.MAX_VALUE will refer to our error result
 	 *              0 indicates an inconclusive result
	 *              -1 for less than
	 *              1 for greater than
	 */
	@Override
	public int compare(Angle left, Angle right)
	{
		// check for null
		if (left == null || right == null) return STRUCTURALLY_INCOMPARABLE;
		
		// check if left and right overlay each other
		if (!left.overlays(right)) return STRUCTURALLY_INCOMPARABLE;
		
		// check if the left and right angle measurements are the same 
		if (!MathUtilities.doubleEquals(left.getMeasure(), right.getMeasure())) return STRUCTURALLY_INCOMPARABLE;
			
		// check if left and right angles are the same 
		if (left.equals(right)) return 0;
	
		
		// get Segments from left and right angles
		Segment leftSeg1 = left.getRay1();
		Segment leftSeg2 = left.getRay2();
		Segment rightSeg1 = right.getRay1();
		Segment rightSeg2 = right.getRay2();
		
		// check if the left angle is greater than or equal to the right angle 
		if (leftSeg1.length() >= rightSeg1.length() && leftSeg2.length() >= rightSeg2.length()) {
			return 1;
		}
		
		// check if the left angle is less than or equal to the right angle 
		if (leftSeg1.length() <= rightSeg1.length() && leftSeg2.length() <= rightSeg2.length()) {
			return -1;
		}
		
		return 0;
	}
}
























