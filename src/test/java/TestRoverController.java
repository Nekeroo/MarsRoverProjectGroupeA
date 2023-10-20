import org.junit.Before;
import org.junit.Test;
import org.marsrover.abstract_class.Planet;
import org.marsrover.controllers.RoverController;
import org.marsrover.models.*;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Direction;
import org.marsrover.records.Obstacle;
import utilities.RoverBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestRoverController {
    private RoverController roverController;
    private Rover rover;

    private Planet planet;

    @Before
    public void init()
    {
        planet = new PlanetWithoutObstacles(5, 5);
        rover =  new RoverBuilder().looking(Direction.North)
                        .onPlanet(planet)
                        .build();
        roverController = new RoverController(rover);
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

        rover = roverController.processSequence(sequence);

        assertEquals(0, rover.getCurrentCoordinates().x());
        assertEquals(4, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleStopSequence()
    {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(2, 2)));

        rover = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithObstacle(planet,obstacles));
        RoverController roverControllerTest = new RoverController(rover);

        List<String> sequence = new ArrayList<>();
        sequence.add("D");
        sequence.add("Z");
        sequence.add("Z");

        rover = roverControllerTest.processSequence(sequence);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(2, rover.getCurrentCoordinates().y());
        assertEquals(Direction.East, rover.getCurrentDirection());
    }

    @Test
    public void testMoveForward()
    {
        List<String> sequence = new ArrayList<>();
        sequence.add("Z");

        rover = roverController.processSequence(sequence);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(3, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testMoveBack()
    {
        List<String> sequence = new ArrayList<>();
        sequence.add("S");

        rover = roverController.processSequence(sequence);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(1, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testTurnRight()
    {
        List<String> sequence = new ArrayList<>();
        sequence.add("D");

        rover = roverController.processSequence(sequence);

        assertEquals(Direction.East, rover.getCurrentDirection());
    }

    @Test
    public void testTurnLeft()
    {
        List<String> sequence = new ArrayList<>();
        sequence.add("Q");

        rover = roverController.processSequence(sequence);

        assertEquals(Direction.West, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleBackward() {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(1, 1)));

        rover = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithObstacle(planet,obstacles));
        RoverController roverControllerTest = new RoverController(rover);
        List<String> sequence = List.of("S");

        rover = roverControllerTest.processSequence(sequence);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(2, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }
}
