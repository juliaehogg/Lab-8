/**
* the ToJSONVisitor class converts FigureNode objects to a JSONObject
*
* <p>Bugs: None
*
* @author Sam Luck-Leonard, Jake Shore, and Hayes Brown
* @date 03/17/2023
*/
// hello
package input.visitor;

import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;

public class ToJSONVisitor implements ComponentNodeVisitor {
	
	/**
	 * converts a FigureNode to a JSONObject
	 * @param figureNode - data is taken from here
	 * @param o - JSONObject is populated with data taken from figureNode
	 * @return Object - returns JSONObject populated with data from figureNode
	 */
	public Object visitFigureNode(FigureNode figureNode, Object o) {
		JSONObject figureWrapperObject = (JSONObject)o;
		JSONObject figureObject = new JSONObject();
		
		//converts attributes of figureNode
		figureObject.put("Description", figureNode.getDescription());
		figureNode.getPointsDatabase().accept(this, figureObject);
		figureNode.getSegments().accept(this, figureObject);
		
		//wraps attributes in a JSONObject labeled "Figure"
		figureWrapperObject.put("Figure", figureObject);
		return figureWrapperObject;
	}

	/**
	 * convers a PointNodeDatabase to a JSONArray and adds it to a JSONObject
	 * @param node - PointNodeDatabase from which data is extracted
	 * @param o - JSONObject is added to
	 * @return Object - JSONObject with PointNodeDatabase data
	 */
	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{
		
		JSONObject figureObject = (JSONObject)o;
		JSONArray pointArrayObject = new JSONArray();

		Set<PointNode> allPoints = node.getAllPoints();

		//goes through all points in the database and adds them to the JSONArray
		for(PointNode individualPoint : allPoints) 
			pointArrayObject.put(individualPoint.accept(this, new JSONObject()));
			
		//adds JSONArray to given JSONObject
		figureObject.put("Points", pointArrayObject);
		return figureObject;
	}

	/**
	 * converts PointNode to JSONObject
	 * @param pointNode - data is extracted from this
	 * @param o - JSONObject that data is added to
	 * @return Object - JSONObject containing PointNode data
	 */
	@Override
	public Object visitPointNode(PointNode pointNode, Object o) {
		JSONObject pointObject = (JSONObject)o;
		
		//converts data from the pointNode into the JSONObject
		pointObject.put("name", pointNode.getName());
		pointObject.put("x", pointNode.getX());
		pointObject.put("y", pointNode.getY());
		
		return pointObject;
	}
	
	/**
	 * converts SegmentNodeDatabase to JSONArray, then adds it to a given JSONObject
	 * @param node - SegmentNodeDatabase from which data is extracted
	 * @param o - JSONObject that data is transfered to
	 * @return Object - JSONObject containing converted data
	 */
	@Override
	public Object visitSegmentNodeDatabase(SegmentNodeDatabase node, Object o)
	{
		//initializes JSONObjects that data will be converted into
		JSONObject wrapperObject = (JSONObject)o;
		JSONArray segmentNodeDatabaseObject = new JSONArray();
		
		//extracts data from the given SegmentNodeDatabase
		Map<PointNode, Set<PointNode>> segments = node.getAdjLists();
		Set<PointNode> allSegmentKeys = segments.keySet();

		//goes through each key in the adjList, then through each value per key, and adds them to a JSONArray
		for(PointNode eachKey : allSegmentKeys) 
		{
			JSONArray segmentListObject = new JSONArray();
			JSONObject adjacencyListObject = new JSONObject();
			
			//converts each key-value pair into a JSONArray
			for(PointNode eachValue : segments.get(eachKey)) 
			{
				segmentListObject.put(eachValue.getName());
			}
			
			//adds key-value pairs to a JSONObject
			adjacencyListObject.put(eachKey.getName(), segmentListObject);
			segmentNodeDatabaseObject.put(adjacencyListObject);
		}

		//wraps the created JSONObject in a JSONObject labeled "Segments"
		return wrapperObject.put("Segments", segmentNodeDatabaseObject);
	}

	//this is not used
	/**
	 * converts SegmentNode to JSONObject
	 * @param segmentNode - SegmentNode from which data is extracted
	 * @param o - JSONObject that is added to
	 * @return Object - JSONObject containing data from segmentNode
	 */
	@Override
	public Object visitSegmentNode(SegmentNode segmentNode, Object o) {
		JSONObject segmentObject = (JSONObject)o;
		PointNode start = segmentNode.getPoint1();
		PointNode end = segmentNode.getPoint2();
		segmentObject.put(start.getName(), new String[] { end.getName() });
		return segmentObject;
	}
}