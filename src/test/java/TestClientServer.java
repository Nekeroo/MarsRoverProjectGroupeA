import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.marsrover.communication.Client;
import org.marsrover.communication.Interpreter;
import org.marsrover.communication.Repeater;
import org.marsrover.config.CancellationToken;
import org.marsrover.console.LoggerConsole;
import org.marsrover.planet.IPlanet;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.rover.Rover;
import org.marsrover.rover.NetworkRover;
import org.marsrover.rover.roverCommands.IRoverCommand;
import org.marsrover.topology.Coordinates;
import org.marsrover.topology.Direction;
import utilities.RoverBuilder;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

/**
 * Classe de test pour le client et le serveur
 * PS : Chaque test est à exécuter un par un afin d'éviter les conflits de port
 */
@Execution(SAME_THREAD)
public class TestClientServer {

    private Rover roverSansReseau;

    private Rover roverAvecReseau;

    @Test
    public void moveForward() throws InterruptedException, IOException {

        IPlanet planet = new PlanetWithoutObstacles(5, 5);
        roverSansReseau = new RoverBuilder().looking(Direction.North)
                .onPlanet(planet)
                .build();
        Interpreter interpreter = new Interpreter();
        IRoverCommand command = interpreter.mapStringToCommand("Z");
        roverSansReseau = (Rover) command.execute(roverSansReseau);

        CancellationToken token = new CancellationToken();
        roverAvecReseau = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithoutObstacles(5, 5), new LoggerConsole());

        new Thread(() -> {
            try {
                roverAvecReseau.startRover(token, roverAvecReseau);
            } catch (ExecutionException | InterruptedException ignored) {
            }
        }).start();

        Repeater repeater = new Repeater();

        new Thread(() -> {
            try {
                repeater.startRepeater();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(1000);
        token.cancel();

        Client client = new Client();
        NetworkRover networkRover = (NetworkRover) client.SendAndWaitForResponse("Z");

        repeater.shutDown();
        roverAvecReseau.shutdown();

        assertAll(
                () -> assertEquals(1, roverSansReseau.getCurrentCoordinates().x()),
                () -> assertEquals(3, roverSansReseau.getCurrentCoordinates().y()),
                () -> assertEquals(Direction.North, roverSansReseau.getCurrentDirection()),
                () -> assertEquals(Direction.North, networkRover.getCurrentDirection()),
                () -> assertEquals(1, networkRover.getCurrentCoordinates().x()),
                () -> assertEquals(3, networkRover.getCurrentCoordinates().y()),
                () -> assertEquals(roverAvecReseau, roverSansReseau));
    }

    @Test
    public void moveSequence() throws InterruptedException, IOException {
        Thread.sleep(1000);
        CancellationToken token = new CancellationToken();
        roverAvecReseau = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithoutObstacles(5, 5), new LoggerConsole());

        new Thread(() -> {
            try {
                roverAvecReseau.startRover(token, roverAvecReseau);
            } catch (ExecutionException | InterruptedException ignored) {
            }
        }).start();

        Repeater repeater = new Repeater();

        new Thread(() -> {
            try {
                repeater.startRepeater();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(1000);
        token.cancel();

        Client client = new Client();
        NetworkRover networkRover = (NetworkRover) client.SendAndWaitForResponse("ZZDZ");

        repeater.shutDown();
        roverAvecReseau.shutdown();

        assertAll(
                () -> assertEquals(Direction.East, networkRover.getCurrentDirection()),
                () -> assertEquals(2, networkRover.getCurrentCoordinates().x()),
                () -> assertEquals(4, networkRover.getCurrentCoordinates().y())
        );
    }
}
