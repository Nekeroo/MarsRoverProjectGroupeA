import org.junit.Test;
import org.marsrover.communication.Client;
import org.marsrover.rover.LocalRover;
import org.marsrover.rover.NetworkRover;
import org.marsrover.topologie.Direction;

import static org.junit.Assert.assertEquals;

public class TestClientServer {

    @Test
    public void moveForward(){
        boolean mustRun = true;
        LocalRover.startRover(value -> mustRun);
        Client client = new Client();

        NetworkRover rover = (NetworkRover) client.SendAndWaitForResponse("Z");
        assertEquals(Direction.North, rover.getCurrentDirection());
        assertEquals(1, rover.getCurrentCoordinates().x());
        assertEquals(3, rover.getCurrentCoordinates().y());
    }

}
