/**
* 
* This file implements a Visitor (design pattern) with 
* the intent of building an unparsed, String representation
* of a geometry figure.
*
* <p>Bugs: None
*
* @author Sam Luck-Leonard, Jake Shore, and Hayes Brown
* @date 03/17/2023
*/

package input.visitor;

import java.util.AbstractMap;
import input.components.*;
import input.components.point.*;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import utilities.io.StringUtilities;


public class UnparseVisitor implements ComponentNodeVisitor
{
	
	// Sample output: 
	// Figure
	// {
	// Description: _____________
	// Points
	// {
	// method call
	// }
	// Segments:
	//{
	// method call
	// }
	// }
	/**
	 * constructs a StringBuilder containing data from a given FigureNode
	 * @param node - data is extracted from givenFigureNode
	 * @param o - AbstractMap contains a the StringBuilder that will be built and an integer assigning indentation level
	 * @return Object - StringBuilder containing data from node
	 */
	@Override
	public Object visitFigureNode(FigureNode node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair 
			= (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		sb.append("Figure" + "\n" + StringUtilities.indent(level) + "{");

		//increment level for proper indentation when methods are called
		level++;

		//adds description and formatting for Points
		sb.append("\n" + StringUtilities.indent(level) + "Description : " + node.getDescription());
		sb.append("\n" + StringUtilities.indent(level) + "Points: \n" + 
				StringUtilities.indent(level) + "{");

		//method call to add points to the sb
		node.getPointsDatabase().accept(this, new AbstractMap.SimpleEntry
				<StringBuilder, Integer>(sb, level + 1));

		//adds formatting for Segments
		sb.append("\n" + StringUtilities.indent(level) + "}");
		sb.append("\n" + StringUtilities.indent(level) + "Segments: \n" + 
				StringUtilities.indent(level) + "{");

		//method call to add segments to the sb
		node.getSegments().accept(this, new AbstractMap.SimpleEntry
				<StringBuilder, Integer>(sb, level + 1));

		sb.append("\n" + StringUtilities.indent(level) + "}");

		//decrement level for proper indentation when methods are called
		level--;

		sb.append("\n" + StringUtilities.indent(level) + "}");

		return sb;
	}

	// Sample output
	// A : B C 
	// B : A C 
	// C : A B 
	/**
	 * adds data from a given SegmentNodeDatabase to a StringBuilder
	 * @param node - SegmentNodeDatabase that data is extracted from
	 * @param o - AbstractMap containing StringBuilder and int describing indentation
	 * @return Object - AbstractMap containing the StringBuilder with new data and int containing indentation information
	 */
	@Override
	public Object visitSegmentNodeDatabase(SegmentNodeDatabase node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair 
			= (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		//nested loop to get all point combinations
		for(PointNode dEdge : node.getAdjLists().keySet())
		{
			// adds name to the sb, wont be printed with the nested loop
			sb.append("\n" + StringUtilities.indent(level) + dEdge.getName() + " : ");

			for(PointNode uEdge : node.getAdjLists().get(dEdge))
			{
				//append each undirected edge for a given dEdge
				sb.append(uEdge.getName() + " ");
			}
		}

		return new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, level);
	}

	/**
	 * This method should NOT be called since the segment database
	 * uses the Adjacency list representation
	 */
	@Override
	public Object visitSegmentNode(SegmentNode node, Object o)
	{
		return null;
	}

	// Sample output: 
	// Point(A)(0.0, 0.0)
	// Point(B)(1.0, 1.0)
	// Point(C)(1.0, 0.0)
	/**
	 * converts data from a given PointNodeDatabase into a StringBuilder
	 * @param node - PointNodeDatabase from which data is extracted
	 * @param o - AbstractMap contains StringBuilder that will be added to and int describing indentation level
	 * @return Object - returns AbstractMap containing the added-to StringBuilder and indentation level
	 */
	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair 
			= (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		//adds all PointNodes
		for(PointNode point : node.getAllPoints())
			sb.append("\n" + StringUtilities.indent(level) + "Point(" + 
					  point.getName() + ")(" + point.getX() + ", " + point.getY() + ")");

		return new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, level);
	}

	/**
	 * converts data from a given PointNode into a StringBuilder
	 * @param node - PointNode from which data is extracted
	 * @param o - AbstractMap contains StringBuilder that will be added to and int describing indentation level
	 * @return Object - returns AbstractMap containing the added-to StringBuilder and indentation level
	 */
	@Override
	public Object visitPointNode(PointNode node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair 
			= (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		//adds PointNode to sb
		sb.append("\n" + StringUtilities.indent(level) + "Point(" + 
				node.getName() + ")(" + node.getX() + ", " + node.getY() + ")");

		return new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, level);
	}
}