import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.marsrover.controllers.Controller;
import org.marsrover.models.Direction;
import org.marsrover.models.Planet;
import org.marsrover.models.Rover;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Obstacle;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestParametricController {

    private int xFinal;

    private int yFinal;

    private Direction directionFinal;

    private List<String> sequenceCommands;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<String> list1 = Arrays.asList("S");
        List<String> list2 = Arrays.asList("D", "Z", "Z");
        List<String> list3 = Arrays.asList("S", "D", "S");
        // Rover part en 1, 2
        return Arrays.asList(new Object[][]{
                {1, 1, Direction.North, list1},
                {3, 2, Direction.East, list2},
                {0, 1, Direction.East, list3}
        });
    }

    public TestParametricController(int xFinal, int yFinal, Direction directionFinal, List<String> sequenceCommands) {
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.directionFinal = directionFinal;
        this.sequenceCommands = sequenceCommands;
    }

    @Test
    public void sequence() {
        Planet planet = new Planet(5, 5, new Obstacle(new Coordinates(2, 3)));
        Rover rover = new Rover(new Coordinates(1, 2), Direction.North, planet);
        Controller controller = new Controller(rover);

        rover = controller.processSequence(sequenceCommands);

        assertEquals(xFinal, rover.getCurrentCoordinates().x());
        assertEquals(yFinal, rover.getCurrentCoordinates().y());
        assertEquals(directionFinal, rover.getCurrentDirection());
    }
}
