import org.junit.Before;
import org.junit.Test;
import org.marsrover.abstract_class.Planet;
import org.marsrover.models.*;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Obstacle;
import utilities.RoverBuilder;
import utilities.TestConstants;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.marsrover.controllers.RoverController.*;

public class TestRoverCommands {
    private Rover rover;
    private Planet planet;

    @Before
    public void init()
    {
        planet = new PlanetWithoutObstacle(new Height(5), new Width(5));
        rover =  new RoverBuilder().lookingDirection(TestConstants.North)
                        .onPlanet(planet)
                        .build();
    }

    @Test
    public void testSequenceNoObstacle()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(TestConstants.MoveForwardCommand);
        sequence.add(TestConstants.TurnRightCommand);
        sequence.add(TestConstants.MoveBackCommand);
        sequence.add(TestConstants.TurnLeftCommand);
        sequence.add(TestConstants.MoveForwardCommand);

        rover = execute(rover, sequence);

        assertEquals(Integer.valueOf(0), rover.getCurrentCoordinates().x());
        assertEquals(Integer.valueOf(4), rover.getCurrentCoordinates().y());
        assertEquals(TestConstants.North, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleStopSequence()
    {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(2, 2)));

        rover = new Rover(new Coordinates(1, 2), TestConstants.North, new PlanetWithObstacle(planet,obstacles));

        List<Character> sequence = new ArrayList<>();
        sequence.add(TestConstants.TurnRightCommand);
        sequence.add(TestConstants.MoveForwardCommand);
        sequence.add(TestConstants.MoveForwardCommand);

        rover = execute(rover, sequence);

        assertEquals(Integer.valueOf(1), rover.getCurrentCoordinates().x());
        assertEquals(Integer.valueOf(2), rover.getCurrentCoordinates().y());
        assertEquals(TestConstants.East, rover.getCurrentDirection());
    }

    @Test
    public void testMoveForward()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(TestConstants.MoveForwardCommand);

        rover = execute(rover, sequence);

        assertEquals(Integer.valueOf(1), rover.getCurrentCoordinates().x());
        assertEquals(Integer.valueOf(3), rover.getCurrentCoordinates().y());
        assertEquals(TestConstants.North, rover.getCurrentDirection());
    }

    @Test
    public void testMoveBack()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(TestConstants.MoveBackCommand);

        rover = execute(rover, sequence);

        assertEquals(Integer.valueOf(1), rover.getCurrentCoordinates().x());
        assertEquals(Integer.valueOf(1), rover.getCurrentCoordinates().y());
        assertEquals(TestConstants.North, rover.getCurrentDirection());
    }

    @Test
    public void testTurnRight()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(TestConstants.TurnRightCommand);

        rover = execute(rover, sequence);

        assertEquals(TestConstants.East, rover.getCurrentDirection());
    }

    @Test
    public void testTurnLeft()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(TestConstants.TurnLeftCommand);

        rover = execute(rover, sequence);

        assertEquals(TestConstants.West, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleBackward() {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(1, 1)));

        rover = new Rover(new Coordinates(1, 2), TestConstants.North, new PlanetWithObstacle(planet,obstacles));
        List<Character> sequence = new ArrayList<>();
        sequence.add(TestConstants.MoveBackCommand);

        rover = execute(rover, sequence);

        assertEquals(Integer.valueOf(1), rover.getCurrentCoordinates().x());
        assertEquals(Integer.valueOf(2), rover.getCurrentCoordinates().y());
        assertEquals(TestConstants.North, rover.getCurrentDirection());
    }
}
