import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.marsrover.abstract_class.Planet;
import org.marsrover.models.PlanetWithObstacle;
import org.marsrover.models.PlanetWithoutObstacle;
import org.marsrover.models.Rover;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Direction;
import org.marsrover.records.Obstacle;
import utilities.RoverBuilder;
import utilities.TestConstants;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.marsrover.controllers.RoverController.*;

@RunWith(Parameterized.class)
public class TestParametricRoverController {
    private final int xFinal;
    private final int yFinal;
    private final Direction directionFinal;
    private final List<Character> sequenceCommands;
    private final Planet planet;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Character> list1 = List.of('R');
        List<Character> list2 = Arrays.asList(TestConstants.TurnRightCommand, TestConstants.MoveForwardCommand, TestConstants.MoveForwardCommand);
        List<Character> list3 = Arrays.asList(TestConstants.MoveBackCommand, TestConstants.TurnRightCommand, TestConstants.MoveBackCommand);
        List<Character> list4 = Arrays.asList(TestConstants.TurnRightCommand, TestConstants.MoveForwardCommand, TestConstants.MoveForwardCommand);
        // Rover part en x = 1 et y = 2
        return Arrays.asList(new Object[][]{
                {1, 1, TestConstants.North, list1, new PlanetWithoutObstacle(5, 5) },
                {3, 2, TestConstants.East, list2, new PlanetWithoutObstacle(5, 5)},
                {0, 1, TestConstants.East, list3, new PlanetWithoutObstacle(5, 5)},
                {1, 2, TestConstants.East, list4, new PlanetWithObstacle(new PlanetWithoutObstacle(5, 5), List.of(new Obstacle(new Coordinates(2, 2))))}
        });
    }

    public TestParametricRoverController(int xFinal, int yFinal, Direction directionFinal, List<Character> sequenceCommands, Planet planet) {
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.directionFinal = directionFinal;
        this.sequenceCommands = sequenceCommands;
        this.planet = planet;
    }

    @Test
    public void sequence() {
        Rover rover = new RoverBuilder().lookingDirection(TestConstants.North).onPlanet(planet).build();

        rover = execute(rover, sequenceCommands);

        assertEquals(xFinal, rover.getCurrentCoordinates().x());
        assertEquals(yFinal, rover.getCurrentCoordinates().y());
        assertEquals(directionFinal, rover.getCurrentDirection());
    }
}
