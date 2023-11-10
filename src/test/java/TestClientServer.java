import org.junit.Test;
import org.marsrover.config.CancellationToken;
import org.marsrover.communication.Client;
import org.marsrover.console.Logger;
import org.marsrover.planet.Planet;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.rover.LocalRover;
import org.marsrover.rover.NetworkRover;
import org.marsrover.rover.RoverController;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import utilities.RoverBuilder;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestClientServer {

    private LocalRover roverSansReseau;

    @Test
    public void moveForward() throws InterruptedException {
        CancellationToken token = new CancellationToken();
        LocalRover rover = new LocalRover(new Coordinates(1, 2), Direction.North, new PlanetWithoutObstacles(5, 5), new Logger());

        Planet planet = new PlanetWithoutObstacles(5, 5);
        roverSansReseau =  new RoverBuilder().looking(Direction.North)
                .onPlanet(planet)
                .build();
        RoverController roverController = new RoverController(rover);

        roverSansReseau = (LocalRover) roverController.processSequence("Z");

        new Thread(() -> {
            try {
                rover.startRover(token, rover);
            } catch (ExecutionException | InterruptedException ignored) {
            }
        }).start();

        Thread.sleep(1000);
        token.cancel();

        Client client = new Client();
        NetworkRover networkRover = (NetworkRover) client.SendAndWaitForResponse("Z");

        assertAll(
                () -> assertEquals(1, roverSansReseau.getCurrentCoordinates().x()),
                () -> assertEquals(3, roverSansReseau.getCurrentCoordinates().y()),
                () -> assertEquals(Direction.North, roverSansReseau.getCurrentDirection()),
                () -> assertEquals(Direction.North, networkRover.getCurrentDirection()),
                () -> assertEquals(1, networkRover.getCurrentCoordinates().x()),
                () -> assertEquals(3, networkRover.getCurrentCoordinates().y()),
                () -> assertEquals(rover, roverSansReseau));
    }
}
