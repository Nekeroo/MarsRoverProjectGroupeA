import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.marsrover.planet.Planet;
import org.marsrover.rover.IRover;
import org.marsrover.rover.RoverController;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.planet.PlanetWithObstacle;
import org.marsrover.rover.Rover;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.planet.Obstacle;
import utilities.RoverBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestParametricRoverController {

    private final int xFinal;

    private final int yFinal;

    private final Direction directionFinal;

    private final String sequenceCommands;

    private final Planet planet;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        // Rover part en x = 1 et y = 2
        return Arrays.asList(new Object[][]{
                {1, 1, Direction.North, "S", new PlanetWithoutObstacles(5, 5) },
                {3, 2, Direction.East, "DZZ", new PlanetWithoutObstacles(5, 5)},
                {0, 1, Direction.East, "SDS", new PlanetWithoutObstacles(5, 5)},
                {1, 2, Direction.East, "DZZ", new PlanetWithObstacle(new PlanetWithoutObstacles(5, 5), List.of(new Obstacle(new Coordinates(2, 2))))}
        });
    }

    public TestParametricRoverController(int xFinal, int yFinal, Direction directionFinal, String sequenceCommands, Planet planet) {
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.directionFinal = directionFinal;
        this.sequenceCommands = sequenceCommands;
        this.planet = planet;
    }

    @Test
    public void sequence() {
        IRover rover = new RoverBuilder().looking(Direction.North).onPlanet(planet).build();
        RoverController roverController = new RoverController(rover);

        rover = roverController.processSequence(sequenceCommands);

        assertEquals(xFinal, rover.getCurrentCoordinates().x());
        assertEquals(yFinal, rover.getCurrentCoordinates().y());
        assertEquals(directionFinal, rover.getCurrentDirection());
    }
}
