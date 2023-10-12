import org.junit.Before;
import org.junit.Test;
import org.marsrover.Coordonnees;
import org.marsrover.Planete;
import org.marsrover.Rover;
import org.marsrover.enums.Direction;

import static org.junit.Assert.assertEquals;

public class TestClassRover {

    private Rover rover;

    @Before
    public void init() {
        rover = new Rover(new Coordonnees(1, 2, Direction.NORTH));
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
}
