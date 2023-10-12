import org.junit.Before;
import org.junit.Test;
import org.marsrover.Position;
import org.marsrover.Planete;
import org.marsrover.Rover;
import org.marsrover.enums.Direction;

import static org.junit.Assert.assertEquals;

public class TestClassRover {

    private Rover rover;

    @Before
    public void init() {
        Planete planete = new Planete(5, 5);
        rover = new Rover(new Position(1, 2, Direction.NORTH, planete));
    }

    @Test
    public void testTournerEast() {
        rover.tournerDroite();
        assertEquals(Direction.EAST, rover.getCoordonneesDirection());
    }

    @Test
    public void testTournerSouth() {
        rover.tournerGauche();
        assertEquals(Direction.WEST, rover.getCoordonneesDirection());
    }

    @Test
    public void testAvancerNorth() {
        rover.move(Direction.NORTH);
        assertEquals(3, rover.getCoordonneesY());
    }

    @Test
    public void testAvancerWest() {
        rover.tournerGauche();
        rover.move(Direction.WEST);
        assertEquals(Direction.WEST, rover.getCoordonneesDirection());
        assertEquals(5, rover.getCoordonneesX());
        assertEquals(2, rover.getCoordonneesY());
    }

    @Test
    public void testAvancerSouth(){
        rover.tournerGauche();
        rover.tournerGauche();
        rover.move(Direction.SOUTH);
        assertEquals(Direction.SOUTH, rover.getCoordonneesDirection());
        assertEquals(1, rover.getCoordonneesY());
        assertEquals(1, rover.getCoordonneesX());
    }

    @Test
    public void testReculer() {
        rover.move(Direction.SOUTH);
        assertEquals(Direction.NORTH, rover.getCoordonneesDirection());
        assertEquals(1, rover.getCoordonneesY());
        assertEquals(1, rover.getCoordonneesX());
    }

    @Test
    public void testBordure1() {
        rover.move(Direction.NORTH);
        rover.move(Direction.NORTH);
        rover.move(Direction.NORTH);
        rover.move(Direction.NORTH);
        assertEquals(1, rover.getCoordonneesY());
        assertEquals(1, rover.getCoordonneesX());
        assertEquals(Direction.NORTH, rover.getCoordonneesDirection());
    }

    @Test
    public void testBordure2() {
        rover.tournerDroite();
        rover.move(Direction.EAST);
        rover.move(Direction.EAST);
        rover.move(Direction.EAST);
        rover.move(Direction.EAST);
        rover.move(Direction.EAST);
        assertEquals(1, rover.getCoordonneesX());
        assertEquals(2, rover.getCoordonneesY());
        assertEquals(Direction.EAST, rover.getCoordonneesDirection());
    }

    @Test
    public void testBordure3() {
        rover.tournerGauche();
        rover.move(Direction.WEST);
        assertEquals(5, rover.getCoordonneesX());
        assertEquals(2, rover.getCoordonneesY());
        assertEquals(Direction.WEST, rover.getCoordonneesDirection());
    }

    @Test
    public void testBordure4(){
        rover.move(Direction.SOUTH);
        rover.move(Direction.SOUTH);
        assertEquals(5, rover.getCoordonneesY());
        assertEquals(1, rover.getCoordonneesX());
        assertEquals(Direction.NORTH, rover.getCoordonneesDirection());
    }
}
