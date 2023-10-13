import org.junit.Before;
import org.junit.Test;
import org.marsrover.Coordonnees;
import org.marsrover.Position;
import org.marsrover.Planet;
import org.marsrover.Rover;
import org.marsrover.enums.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestClassRover {

    private Rover rover;

    private Planet planet;

    @Before
    public void init() {
        planet = new Planet(5, 5, "Mars");
        Coordonnees coordonnees = new Coordonnees(1, 2);
        rover = new Rover(new Position(coordonnees, Direction.NORTH));
    }

    @Test
    public void testTurnEast() {
        rover.turnRight();
        assertEquals(Direction.EAST, rover.getPosition().getDirection());
    }

    @Test
    public void testTurnSouth() {
        rover.turnLeft();
        assertEquals(Direction.WEST, rover.getPosition().getDirection());
    }

    @Test
    public void testMoveForwardNorth() {
        rover.moveForward();
        assertEquals(3, rover.getPosition().getCoordonnees().getY());
    }

    @Test
    public void testMoveForwardWest() {
        rover.turnLeft();
        rover.moveForward();
        planet.rendu(rover);
        assertEquals(Direction.WEST, rover.getPosition().getDirection());
        assertEquals(5, rover.getPosition().getCoordonnees().getX());
        assertEquals(2, rover.getPosition().getCoordonnees().getY());
    }

    @Test
    public void testMoveForwardSouth(){
        rover.turnLeft();
        rover.turnLeft();
        rover.moveForward();
        assertEquals(Direction.SOUTH, rover.getPosition().getDirection());
        assertEquals(1, rover.getPosition().getCoordonnees().getY());
        assertEquals(1, rover.getPosition().getCoordonnees().getX());
    }

    @Test
    public void testMoveBack() {
        rover.moveBack();
        assertEquals(Direction.NORTH, rover.getPosition().getDirection());
        assertEquals(1, rover.getPosition().getCoordonnees().getY());
        assertEquals(1, rover.getPosition().getCoordonnees().getX());
    }

    @Test
    public void testBorderNorth() {
        rover.moveForward();
        rover.moveForward();
        rover.moveForward();
        rover.moveForward();

        assertEquals(1, rover.getPosition().getCoordonnees().getY());
        assertEquals(1, rover.getPosition().getCoordonnees().getX());
        assertEquals(Direction.NORTH, rover.getPosition().getDirection());
    }

    @Test
    public void testBorderEast() {
        rover.turnRight();
        rover.moveForward();
        rover.moveForward();
        rover.moveForward();
        rover.moveForward();
        rover.moveForward();

        assertEquals(1, rover.getPosition().getCoordonnees().getX());
        assertEquals(2, rover.getPosition().getCoordonnees().getY());
        assertEquals(Direction.EAST, rover.getPosition().getDirection());
    }

    @Test
    public void testBorderWest() {
        rover.turnLeft();
        rover.moveForward();
        assertEquals(5, rover.getPosition().getCoordonnees().getX());
        assertEquals(2, rover.getPosition().getCoordonnees().getY());
        assertEquals(Direction.WEST, rover.getPosition().getDirection());
    }

    @Test
    public void testBorderSouth(){
        rover.moveBack();
        rover.moveBack();
        assertEquals(5, rover.getPosition().getCoordonnees().getY());
        assertEquals(1, rover.getPosition().getCoordonnees().getX());
        assertEquals(Direction.NORTH, rover.getPosition().getDirection());
    }

    @Test
    public void testPath1() {
        System.out.println("------ Départ Mission sur la Planète " + planet.getName() + " ------");
        System.out.println("Position départ " + rover.getPosition().getCoordonnees().getX() + " " + rover.getPosition().getCoordonnees().getY());
        rover.moveForward();
        System.out.println("Position actuelle " + rover.getPosition().getCoordonnees().getX() + " " + rover.getPosition().getCoordonnees().getY());
        rover.turnRight();
        rover.moveForward();
        rover.moveForward();
        System.out.println("Position actuelle " + rover.getPosition().getCoordonnees().getX() + " " + rover.getPosition().getCoordonnees().getY());
        rover.turnRight();
        rover.moveForward();
        rover.moveForward();
        rover.moveForward();
        System.out.println("Position actuelle " + rover.getPosition().getCoordonnees().getX() + " " + rover.getPosition().getCoordonnees().getY());
        assertEquals(5, rover.getPosition().getCoordonnees().getY());
        assertEquals(3, rover.getPosition().getCoordonnees().getX());
        assertEquals(Direction.SOUTH, rover.getPosition().getDirection());
    }
}
