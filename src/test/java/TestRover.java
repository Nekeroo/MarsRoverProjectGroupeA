import org.junit.Before;
import org.junit.Test;
import org.marsrover.models.*;

import static org.junit.Assert.assertEquals;

public class TestRover {
    private Rover rover;

    private Planet planet;

    @Before
    public void init()
    {
        planet = new Planet(5, 5, new Obstacle(new Coordinates(2,2)));
        Coordinates coordinates = new Coordinates(1, 2);
        rover = new Rover(coordinates, Direction.North, planet);
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
        Planet planet = new Planet(1, 1, new Obstacle(new Coordinates(2,2)));
        rover = new Rover(new Coordinates(0, 0), rover.getCurrentDirection(), planet);
        rover = rover.moveForward();
        assertEquals(0, rover.getCurrentCoordinates().x());
        assertEquals(0, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }
}
