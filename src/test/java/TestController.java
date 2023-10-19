import org.junit.Before;
import org.junit.Test;
import org.marsrover.controllers.Controller;
import org.marsrover.models.*;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Obstacle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestController {
    private Controller controller;
    private Rover rover;

    @Before
    public void init()
    {
        Planet planet = new Planet(5, 5, new Obstacle(new Coordinates(2, 2)));
        Coordinates coordinates = new Coordinates(1, 2);
        rover = new Rover(coordinates, Direction.North, planet);
        controller = new Controller(rover);
    }

    @Test
    public void testSequenceNoObstacle()
    {
        List<String> sequence = new ArrayList<>();
        sequence.add("Z");
        sequence.add("D");
        sequence.add("S");
        sequence.add("Q");
        sequence.add("Z");

        rover = controller.processSequence(sequence);

        assertEquals(0, rover.getCurrentCoordinates().x());
        assertEquals(4, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleStopSequence()
    {
        List<String> sequence = new ArrayList<>();
        sequence.add("D");
        sequence.add("Z");
        sequence.add("Z");

        rover = controller.processSequence(sequence);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(2, rover.getCurrentCoordinates().y());
        assertEquals(Direction.East, rover.getCurrentDirection());
    }
}
