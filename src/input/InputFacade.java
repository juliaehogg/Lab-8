package input;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import geometry_objects.Segment;
import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.segment.SegmentNode;
import input.parser.JSONParser;

public class InputFacade
{
	/**
	 * A utility method to acquire a figure from the given JSON file:
	 *     Constructs a parser
	 *     Acquries an input file string.
	 *     Parses the file.
     *
	 * @param filepath -- the path/name defining the input file
	 * @return a FigureNode object corresponding to the input file.
	 */
	public static FigureNode extractFigure(String filepath)
	{
        JSONParser parser = new JSONParser(new GeometryBuilder());
        
        String figureStr = utilities.io.FileUtilities.readFileFilterComments(filepath);
        
        return (FigureNode)parser.parse(figureStr);
        
        
	}
	
	/**
	 * 1) Convert the PointNode and SegmentNode objects to a Point and Segment objects 
	 *    (those classes have more meaningful, geometric functionality).
	 * 2) Return the points and segments as a pair.
     *
	 * @param fig -- a populated FigureNode object corresponding to a geometry figure
	 * @return a point database and a set of segments
	 */
	public static Map.Entry<PointDatabase, Set<Segment>> toGeometryRepresentation(FigureNode fig)
	{
		PointDatabase allPoints = new PointDatabase();
		Set<Segment> allSegments = new HashSet<Segment>();
		
		
		Set<PointNode> pointNodeSet = fig.getPointsDatabase().getAllPoints();
		
		for(PointNode pointNode : pointNodeSet)
			allPoints.put(pointNode.getName(), pointNode.getX(), pointNode.getY());
		
		List<SegmentNode> segmentNodeSet = fig.getSegments().asSegmentList();
		
		for(SegmentNode segmentNode : segmentNodeSet)
		{
			allSegments.add(convertToSegment(segmentNode));
		}
		
		return new AbstractMap.SimpleEntry<PointDatabase, Set<Segment>>(allPoints, allSegments);
	}

	
	private static Point convertToPoint(PointNode pointNode) 
	{ 
		return new Point(pointNode.getName(), pointNode.getX(), pointNode.getY()); 
	}
	
	private static Segment convertToSegment(SegmentNode segmentNode)
	{
		return new Segment(convertToPoint(segmentNode.getPoint1()), convertToPoint(segmentNode.getPoint2()));
	}
}
