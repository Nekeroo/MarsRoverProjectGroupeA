import org.junit.Before;
import org.junit.Test;
import org.marsrover.Position;
import org.marsrover.Planet;
import org.marsrover.Rover;
import org.marsrover.enums.Direction;

import static org.junit.Assert.assertEquals;

public class TestClassRover {

    private Rover rover;

    @Before
    public void init() {
        Planet planet = new Planet(5, 5, "Mars");
        rover = new Rover(new Position(1, 2, Direction.NORTH, planet));
    }

    @Test
    public void testTurnEast() {
        rover.turnRight();
        assertEquals(Direction.EAST, rover.getCoordinatesDirection());
    }

    @Test
    public void testTurnSouth() {
        rover.turnLeft();
        assertEquals(Direction.WEST, rover.getCoordinatesDirection());
    }

    @Test
    public void testMoveForwardNorth() {
        rover.move(Direction.NORTH);
        assertEquals(3, rover.getCoordinatesY());
    }

    @Test
    public void testMoveForwardWest() {
        rover.turnLeft();
        rover.move(Direction.WEST);
        assertEquals(Direction.WEST, rover.getCoordinatesDirection());
        assertEquals(5, rover.getCoordinatesX());
        assertEquals(2, rover.getCoordinatesY());
    }

    @Test
    public void testMoveForwardSouth(){
        rover.turnLeft();
        rover.turnLeft();
        rover.move(Direction.SOUTH);
        assertEquals(Direction.SOUTH, rover.getCoordinatesDirection());
        assertEquals(1, rover.getCoordinatesY());
        assertEquals(1, rover.getCoordinatesX());
    }

    @Test
    public void testMoveBack() {
        rover.move(Direction.SOUTH);
        assertEquals(Direction.NORTH, rover.getCoordinatesDirection());
        assertEquals(1, rover.getCoordinatesY());
        assertEquals(1, rover.getCoordinatesX());
    }

    @Test
    public void testBorderNorth() {
        rover.move(Direction.NORTH);
        rover.move(Direction.NORTH);
        rover.move(Direction.NORTH);
        rover.move(Direction.NORTH);
        assertEquals(1, rover.getCoordinatesY());
        assertEquals(1, rover.getCoordinatesX());
        assertEquals(Direction.NORTH, rover.getCoordinatesDirection());
    }

    @Test
    public void testBorderEast() {
        rover.turnRight();
        rover.move(Direction.EAST);
        rover.move(Direction.EAST);
        rover.move(Direction.EAST);
        rover.move(Direction.EAST);
        rover.move(Direction.EAST);
        assertEquals(1, rover.getCoordinatesX());
        assertEquals(2, rover.getCoordinatesY());
        assertEquals(Direction.EAST, rover.getCoordinatesDirection());
    }

    @Test
    public void testBorderWest() {
        rover.turnLeft();
        rover.move(Direction.WEST);
        assertEquals(5, rover.getCoordinatesX());
        assertEquals(2, rover.getCoordinatesY());
        assertEquals(Direction.WEST, rover.getCoordinatesDirection());
    }

    @Test
    public void testBorderSouth(){
        rover.move(Direction.SOUTH);
        rover.move(Direction.SOUTH);
        assertEquals(5, rover.getCoordinatesY());
        assertEquals(1, rover.getCoordinatesX());
        assertEquals(Direction.NORTH, rover.getCoordinatesDirection());
    }

    @Test
    public void testPath1() {
        System.out.println("------ Départ Mission sur la Planète " + rover.getPosition().getPlanet().getName() + " ------");
        System.out.println("Position départ " + rover.getCoordinatesX() + " " + rover.getCoordinatesY());
        rover.move(Direction.NORTH);
        System.out.println("Position actuelle " + rover.getCoordinatesX() + " " + rover.getCoordinatesY());
        rover.turnRight();
        rover.move(Direction.EAST);
        rover.move(Direction.EAST);
        System.out.println("Position actuelle " + rover.getCoordinatesX() + " " + rover.getCoordinatesY());
        rover.turnRight();
        rover.move(Direction.SOUTH);
        rover.move(Direction.SOUTH);
        rover.move(Direction.SOUTH);
        System.out.println("Position actuelle " + rover.getCoordinatesX() + " " + rover.getCoordinatesY());
        assertEquals(5, rover.getCoordinatesY());
        assertEquals(3, rover.getCoordinatesX());
        assertEquals(Direction.SOUTH, rover.getCoordinatesDirection());
    }
}
