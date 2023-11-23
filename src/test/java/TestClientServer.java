import org.junit.After;
import org.junit.Test;
import org.marsrover.communication.Client;
import org.marsrover.communication.Interpreter;
import org.marsrover.communication.Repeater;
import org.marsrover.config.CancellationToken;
import org.marsrover.console.Logger;
import org.marsrover.planet.Planet;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.rover.Rover;
import org.marsrover.rover.NetworkRover;
import org.marsrover.rover.roverCommands.IRoverCommand;
import org.marsrover.topology.Coordinates;
import org.marsrover.topology.Direction;
import utilities.RoverBuilder;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


/**
 * Classe de test pour le client et le serveur
 * PS : Chaque test est à exécuter un par un afin d'éviter les conflits de port
 */
public class TestClientServer {

    private Rover roverSansReseau;

    @After
    public void tearDown() {

    }

    @Test
    public void moveForward() throws InterruptedException {
        CancellationToken token = new CancellationToken();
        Rover rover = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithoutObstacles(5, 5), new Logger());

        Planet planet = new PlanetWithoutObstacles(5, 5);
        roverSansReseau = new RoverBuilder().looking(Direction.North)
                .onPlanet(planet)
                .build();
        Interpreter interpreter = new Interpreter();
        IRoverCommand command = interpreter.mapStringToCommand("Z");
        roverSansReseau = (Rover) command.execute(roverSansReseau);

        new Thread(() -> {
            try {
                rover.startRover(token, rover);
            } catch (ExecutionException | InterruptedException ignored) {
            }
        }).start();

        Repeater repeater = new Repeater();

        new Thread(repeater::startRepeater).start();

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

    @Test
    public void moveSequence() throws InterruptedException {
        CancellationToken token = new CancellationToken();
        Rover rover = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithoutObstacles(5, 5), new Logger());

        new Thread(() -> {
            try {
                rover.startRover(token, rover);
            } catch (ExecutionException | InterruptedException ignored) {
            }
        }).start();

        Repeater repeater = new Repeater();

        new Thread(repeater::startRepeater).start();

        Thread.sleep(1000);
        token.cancel();

        Client client = new Client();
        NetworkRover networkRover = (NetworkRover) client.SendAndWaitForResponse("ZZDZ");

        assertAll(
                () -> assertEquals(Direction.East, networkRover.getCurrentDirection()),
                () -> assertEquals(2, networkRover.getCurrentCoordinates().x()),
                () -> assertEquals(4, networkRover.getCurrentCoordinates().y())
        );
    }
}
