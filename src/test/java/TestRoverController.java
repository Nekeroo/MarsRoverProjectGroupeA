import org.junit.Before;
import org.junit.Test;
import org.marsrover.communication.Interpreter;
import org.marsrover.console.LoggerConsole;
import org.marsrover.planet.*;
import org.marsrover.rover.IRover;
import org.marsrover.rover.Rover;
import org.marsrover.rover.roverCommands.IRoverCommand;
import org.marsrover.topology.Coordinates;
import org.marsrover.topology.Direction;
import utilities.RoverBuilder;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestRoverController {
    private Interpreter interpreter;
    private IRover rover;

    @Before
    public void init() {
        IPlanet planet = new PlanetWithoutObstacles(5, 5);
        rover =  new RoverBuilder().looking(Direction.North)
                        .onPlanet(planet)
                        .build();
        interpreter = new Interpreter();
    }


    @Test
    public void testSequenceNoObstacle() {
        List<IRoverCommand> commands = interpreter.mapStringToCommandList("ZDSQZ");
        for (IRoverCommand command : commands){
            rover = command.execute(rover);
        }
        assertEquals(0, rover.getCurrentCoordinates().x());
        assertEquals(4, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleStopSequence() {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(2, 2)));

        rover = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithObstacle(5,5,obstacles), new LoggerConsole());
        Interpreter interpreter = new Interpreter();

        List<IRoverCommand> commands = interpreter.mapStringToCommandList("DZZ");
        for (IRoverCommand command : commands){
            rover = command.execute(rover);
        }

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(2, rover.getCurrentCoordinates().y());
        assertEquals(Direction.East, rover.getCurrentDirection());
    }

    @Test
    public void testMoveForward() {
        IRoverCommand command = interpreter.mapStringToCommand("Z");
        rover = command.execute(rover);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(3, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testMoveBack() {

        IRoverCommand command = interpreter.mapStringToCommand("S");
        rover = command.execute(rover);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(1, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }

    @Test
    public void testTurnRight() {
        IRoverCommand command = interpreter.mapStringToCommand("D");
        rover = command.execute(rover);
        assertEquals(Direction.East, rover.getCurrentDirection());
    }

    @Test
    public void testTurnLeft() {

        IRoverCommand command = interpreter.mapStringToCommand("Q");
        rover = command.execute(rover);
        assertEquals(Direction.West, rover.getCurrentDirection());
    }

    @Test
    public void testObstacleBackward() {
        List<Obstacle> obstacles = List.of(new Obstacle(new Coordinates(1, 1)));
        rover = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithObstacle(5,5,obstacles), new LoggerConsole());
        Interpreter interpreter = new Interpreter();
        IRoverCommand command = interpreter.mapStringToCommand("S");

        rover = command.execute(rover);

        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(2, rover.getCurrentCoordinates().y());
        assertEquals(Direction.North, rover.getCurrentDirection());
    }
}
