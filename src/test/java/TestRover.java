import org.junit.Before;
import org.junit.Test;
import org.marsrover.models.*;
import org.marsrover.records.Direction;
import utilities.RoverBuilder;

import static org.junit.Assert.assertEquals;

public class TestRover {
    private Rover rover;

    @Before
    public void init()
    {
        PlanetWithoutObstacle planetWithoutObstacles = new PlanetWithoutObstacle(5, 5);
        rover = new RoverBuilder().looking(Direction.North)
                        .onPlanet(planetWithoutObstacles)
                        .build();
    }

    @Test
    public void testMoveForward()
    {
        rover = rover.moveForward();
        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(3, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testMoveBack()
    {
        rover = rover.moveBack();
        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(1, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testTurnRight()
    {
        rover = rover.turnRight();
        assertEquals(Direction.East, rover.getCurrentDirection());
    }

    @Test
    public void testTurnLeft()
    {
        rover = rover.turnLeft();
        assertEquals(Direction.West, rover.getCurrentDirection());
    }

    @Test
    public void testPath()
    {
        for (int i = 0; i < 2; i++){
            rover = rover.moveBack();
        }
        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(0, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testPath2()
    {
        for (int i = 0; i < 3; i++){
            rover = rover.moveBack();
        }
        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(4, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testPlanetOne()
    {
        PlanetWithoutObstacle planetWithoutObstacle = new PlanetWithoutObstacle(1, 1);
        rover = new RoverBuilder().looking(Direction.North)
                        .onPlanet(planetWithoutObstacle)
                        .onThisPosition(0, 0)
                        .build();
        rover = rover.moveForward();
        assertEquals(0, rover.getCurrentCoordinates().x());
        assertEquals(0, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }
}
