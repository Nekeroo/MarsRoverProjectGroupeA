import org.junit.Before;
import org.junit.Test;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.topologie.Direction;
import org.marsrover.rover.LocalRover;
import utilities.RoverBuilder;

import static org.junit.Assert.assertEquals;

public class TestLocalRover {
    private LocalRover localRover;

    @Before
    public void init()
    {
        PlanetWithoutObstacles planetWithoutObstacles = new PlanetWithoutObstacles(5, 5);
        localRover = new RoverBuilder().looking(Direction.North)
                        .onPlanet(planetWithoutObstacles)
                        .build();
    }

    @Test
    public void testMoveForward()
    {
        localRover = localRover.moveForward();
        assertEquals(1, localRover.getCurrentCoordinates().x());
        assertEquals(3, localRover.getCurrentCoordinates().y());
        assertEquals(Direction.North, localRover.getCurrentDirection());
    }

    @Test
    public void testMoveBack()
    {
        localRover = localRover.moveBack();
        assertEquals(1, localRover.getCurrentCoordinates().x());
        assertEquals(1, localRover.getCurrentCoordinates().y());
        assertEquals(Direction.North, localRover.getCurrentDirection());
    }

    @Test
    public void testTurnRight()
    {
        localRover = localRover.turnRight();
        assertEquals(Direction.East, localRover.getCurrentDirection());
    }

    @Test
    public void testTurnLeft()
    {
        localRover = localRover.turnLeft();
        assertEquals(Direction.West, localRover.getCurrentDirection());
    }

    @Test
    public void testPath()
    {
        for (int i = 0; i < 2; i++){
            localRover = localRover.moveBack();
        }
        assertEquals(1, localRover.getCurrentCoordinates().x());
        assertEquals(0, localRover.getCurrentCoordinates().y());
        assertEquals(Direction.North, localRover.getCurrentDirection());
    }

    @Test
    public void testPath2()
    {
        for (int i = 0; i < 3; i++){
            localRover = localRover.moveBack();
        }
        assertEquals(1, localRover.getCurrentCoordinates().x());
        assertEquals(4, localRover.getCurrentCoordinates().y());
        assertEquals(Direction.North, localRover.getCurrentDirection());
    }

    @Test
    public void testPlanetOne()
    {
        PlanetWithoutObstacles planetWithoutObstacles = new PlanetWithoutObstacles(1, 1);
        localRover = new RoverBuilder().looking(Direction.North)
                        .onPlanet(planetWithoutObstacles)
                        .onThisPosition(0, 0)
                        .build();
        localRover = localRover.moveForward();
        assertEquals(0, localRover.getCurrentCoordinates().x());
        assertEquals(0, localRover.getCurrentCoordinates().y());
        assertEquals(Direction.North, localRover.getCurrentDirection());
    }
}
