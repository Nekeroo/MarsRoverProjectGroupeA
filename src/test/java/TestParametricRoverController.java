import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.marsrover.abstract_class.Planet;
import org.marsrover.controllers.RoverController;
import org.marsrover.models.PlanetWithoutObstacles;
import org.marsrover.models.PlanetWithObstacle;
import org.marsrover.models.Rover;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Direction;
import org.marsrover.records.Obstacle;
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

    private final List<String> sequenceCommands;

    private final Planet planet;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<String> list1 = List.of("S");
        List<String> list2 = Arrays.asList("D", "Z", "Z");
        List<String> list3 = Arrays.asList("S", "D", "S");
        List<String> list4 = Arrays.asList("D", "Z", "Z");
        // Rover part en x = 1 et y = 2
        return Arrays.asList(new Object[][]{
                {1, 1, Direction.North, list1, new PlanetWithoutObstacles(5, 5) },
                {3, 2, Direction.East, list2, new PlanetWithoutObstacles(5, 5)},
                {0, 1, Direction.East, list3, new PlanetWithoutObstacles(5, 5)},
                {1, 2, Direction.East, list4, new PlanetWithObstacle(new PlanetWithoutObstacles(5, 5), List.of(new Obstacle(new Coordinates(2, 2))))}
        });
    }

    public TestParametricRoverController(int xFinal, int yFinal, Direction directionFinal, List<String> sequenceCommands, Planet planet) {
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.directionFinal = directionFinal;
        this.sequenceCommands = sequenceCommands;
        this.planet = planet;
    }

    @Test
    public void sequence() {
        Rover rover = new RoverBuilder().looking(Direction.North).onPlanet(planet).build();
        RoverController roverController = new RoverController(rover);

        rover = roverController.processSequence(sequenceCommands);

        assertEquals(xFinal, rover.getCurrentCoordinates().x());
        assertEquals(yFinal, rover.getCurrentCoordinates().y());
        assertEquals(directionFinal, rover.getCurrentDirection());
    }
}
