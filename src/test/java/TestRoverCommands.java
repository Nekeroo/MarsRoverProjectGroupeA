import org.junit.Before;
import org.junit.Test;
import org.marsrover.abstract_class.Planet;
import org.marsrover.models.*;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Direction;
import org.marsrover.records.Obstacle;
import utilities.RoverBuilder;

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
        planet = new PlanetWithoutObstacle(5, 5);
        rover =  new RoverBuilder().looking(Direction.North)
                        .onPlanet(planet)
                        .build();
    }


    @Test
    public void testSequenceNoObstacle()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(MoveForwardCommand);
        sequence.add(TurnRightCommand);
        sequence.add(MoveBackCommand);
        sequence.add(TurnLeftCommand);
        sequence.add(MoveForwardCommand);

        rover = execute(rover, sequence);

        assertEquals(0, rover.getCurrentCoordinates().x());
        assertEquals(4, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleStopSequence()
    {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(2, 2)));

        rover = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithObstacle(planet,obstacles));

        List<Character> sequence = new ArrayList<>();
        sequence.add(TurnRightCommand);
        sequence.add(MoveForwardCommand);
        sequence.add(MoveForwardCommand);

        rover = execute(rover, sequence);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(2, rover.getCurrentCoordinates().y());
        assertEquals(Direction.East, rover.getCurrentDirection());
    }

    @Test
    public void testMoveForward()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(MoveForwardCommand);

        rover = execute(rover, sequence);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(3, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testMoveBack()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(MoveBackCommand);

        rover = execute(rover, sequence);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(1, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testTurnRight()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(TurnRightCommand);

        rover = execute(rover, sequence);

        assertEquals(Direction.East, rover.getCurrentDirection());
    }

    @Test
    public void testTurnLeft()
    {
        List<Character> sequence = new ArrayList<>();
        sequence.add(TurnLeftCommand);

        rover = execute(rover, sequence);

        assertEquals(Direction.West, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleBackward() {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(1, 1)));

        rover = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithObstacle(planet,obstacles));
        List<Character> sequence = new ArrayList<>();
        sequence.add(MoveBackCommand);

        rover = execute(rover, sequence);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(2, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }
}
