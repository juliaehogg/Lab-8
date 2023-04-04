package input;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;
import input.components.FigureNode;
import geometry_objects.*;
import geometry_objects.points.PointDatabase;
class InputFacadeTest {
	
	
	void test_extractFigure(String filename) {
		
		FigureNode node = InputFacade.extractFigure(filename);
		
		assertFalse(node.getDescription().isEmpty());
		assertFalse(node.getPointsDatabase().getAllPoints().isEmpty());
		assertFalse(node.getSegments().asSegmentList().isEmpty());
	}
	
	@Test
	void test_extractFigure() {
		
		test_extractFigure("collinear_line_segments.json");
		test_extractFigure("crisscross_square.json");
		test_extractFigure("crossing_symmetric_triangle.json");
		test_extractFigure("decagon_wheel.json");
		test_extractFigure("fully_connected_irregular_polygon.json");
		test_extractFigure("single_square.json");
		test_extractFigure("single_triangle.json");
		test_extractFigure("snake.json");
		test_extractFigure("intersecting_star.json");
	}
	
	
	void test_toGeometryRepresentation(String filename) {
		
		FigureNode node = InputFacade.extractFigure(filename);
		
		Map.Entry<PointDatabase, Set<Segment>> entry = InputFacade.toGeometryRepresentation(node);
		PointDatabase database = entry.getKey();
		Set<Segment> segments = entry.getValue();
		
		assertFalse(database.getPoints().isEmpty());
		assertFalse(segments.isEmpty());
	}
	@Test
	void test_toGeometryRepresentation() {
		
		test_toGeometryRepresentation("collinear_line_segments.json");
		test_toGeometryRepresentation("crisscross_square.json");
		test_toGeometryRepresentation("crossing_symmetric_triangle.json");
		test_toGeometryRepresentation("decagon_wheel.json");
		test_toGeometryRepresentation("fully_connected_irregular_polygon.json");
		test_toGeometryRepresentation("single_square.json");
		test_toGeometryRepresentation("single_triangle.json");
		test_toGeometryRepresentation("snake.json");
		test_toGeometryRepresentation("intersecting_star.json");
	}
}
