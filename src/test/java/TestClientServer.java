import org.junit.Test;
import org.marsrover.communication.CancellationToken;
import org.marsrover.communication.Client;
import org.marsrover.rover.LocalRover;
import org.marsrover.rover.NetworkRover;
import org.marsrover.topologie.Direction;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class TestClientServer {

    @Test
    public void moveForward() throws InterruptedException {
        CancellationToken token = new CancellationToken();

        new Thread(() -> {
            try {
                LocalRover.startRover(token);
            } catch (ExecutionException | InterruptedException ignored) {
            }
        }).start();

        Thread.sleep(1000);
        token.cancel();

        Client client = new Client();
        NetworkRover networkRover = (NetworkRover) client.SendAndWaitForResponse("Z");

        assertEquals(Direction.North, networkRover.getCurrentDirection());
        assertEquals(1, networkRover.getCurrentCoordinates().x());
        assertEquals(3, networkRover.getCurrentCoordinates().y());
    }
}
