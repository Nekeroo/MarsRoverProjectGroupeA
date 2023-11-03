import org.junit.Before;
import org.junit.Test;
import org.marsrover.planet.Planet;
import org.marsrover.rover.IRover;
import org.marsrover.rover.RoverController;
import org.marsrover.planet.PlanetWithObstacle;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.planet.Obstacle;
import org.marsrover.rover.LocalRover;
import utilities.RoverBuilder;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestLocalRoverController {
    private RoverController roverController;
    private IRover rover;

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

        rover = roverController.processSequence("ZDSQZ");

        assertEquals(0, rover.getCurrentCoordinates().x());
        assertEquals(4, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleStopSequence()
    {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(2, 2)));

        rover = new LocalRover(new Coordinates(1, 2), Direction.North, new PlanetWithObstacle(planet,obstacles));
        RoverController roverControllerTest = new RoverController(rover);

        rover = roverControllerTest.processSequence("DZZ");

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(2, rover.getCurrentCoordinates().y());
        assertEquals(Direction.East, rover.getCurrentDirection());
    }

    @Test
    public void testMoveForward()
    {
        rover = roverController.processSequence("Z");

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(3, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testMoveBack()
    {

        rover = roverController.processSequence("S");

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(1, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testTurnRight()
    {
        rover = roverController.processSequence("D");

        assertEquals(Direction.East, rover.getCurrentDirection());
    }

    @Test
    public void testTurnLeft()
    {

        rover = roverController.processSequence("Q");

        assertEquals(Direction.West, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleBackward() {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(1, 1)));

        rover = new LocalRover(new Coordinates(1, 2), Direction.North, new PlanetWithObstacle(planet,obstacles));
        RoverController roverControllerTest = new RoverController(rover);
        rover = roverControllerTest.processSequence("S");

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(2, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }
}
