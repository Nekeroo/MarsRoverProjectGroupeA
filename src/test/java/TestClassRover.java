import org.junit.Before;
import org.junit.Test;
import org.marsrover.Coordonnees;
import org.marsrover.Planete;
import org.marsrover.Rover;
import org.marsrover.enums.Direction;

import static org.junit.Assert.assertEquals;

public class TestClassRover {

    private Rover rover;

    private Planete planete;

    @Before
    public void init() {
        rover = new Rover(new Coordonnees(1, 2, Direction.NORTH));
        planete = new Planete(5, 5);
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
        assertEquals(0, rover.getCoordonneesX());
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
}
