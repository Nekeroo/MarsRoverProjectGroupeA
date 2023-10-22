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
        List<Character> list1 = List.of(MoveBackCommand);
        List<Character> list2 = Arrays.asList(TurnRightCommand, MoveForwardCommand, MoveForwardCommand);
        List<Character> list3 = Arrays.asList(MoveBackCommand, TurnRightCommand, MoveBackCommand);
        List<Character> list4 = Arrays.asList(TurnRightCommand, MoveForwardCommand, MoveForwardCommand);
        // Rover part en x = 1 et y = 2
        return Arrays.asList(new Object[][]{
                {1, 1, Direction.North, list1, new PlanetWithoutObstacle(5, 5) },
                {3, 2, Direction.East, list2, new PlanetWithoutObstacle(5, 5)},
                {0, 1, Direction.East, list3, new PlanetWithoutObstacle(5, 5)},
                {1, 2, Direction.East, list4, new PlanetWithObstacle(new PlanetWithoutObstacle(5, 5), List.of(new Obstacle(new Coordinates(2, 2))))}
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
        Rover rover = new RoverBuilder().looking(Direction.North).onPlanet(planet).build();

        rover = execute(rover, sequenceCommands);

        assertEquals(xFinal, rover.getCurrentCoordinates().x());
        assertEquals(yFinal, rover.getCurrentCoordinates().y());
        assertEquals(directionFinal, rover.getCurrentDirection());
    }
}
