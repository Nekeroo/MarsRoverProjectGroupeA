package org.marsrover.rover;

import org.marsrover.config.CancellationToken;
import org.marsrover.console.LoggerConsole;
import org.marsrover.communication.Server;
import org.marsrover.planet.IPlanet;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.topology.Coordinates;
import org.marsrover.topology.Direction;
import org.marsrover.topology.Position;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

// Objet Valeur
public final class Rover implements IRover
{
    private final LoggerConsole loggerConsole;
    private final Position position;
    private final IPlanet planet;

    private Server server;

    public Rover(Coordinates coordinates, Direction direction, IPlanet planet, LoggerConsole loggerConsole) {
        Coordinates canonisedCoordinates = planet.canonise(coordinates);
        this.position = new Position(canonisedCoordinates, direction);
        this.planet = planet;
        this.loggerConsole = loggerConsole;
        loggerConsole.log("Coordonnées : " + this.getCurrentCoordinates() + " / Direction : " + this.getCurrentDirection() + "\n");
    }

    public Direction getCurrentDirection()
    {
        return position.direction();
    }

    public Coordinates getCurrentCoordinates()
    {
        return position.coordinates();
    }

    /**
     * Méthode de déplacement (Tourner Droite) pour le rover
     * @return une nouvelle installe de Rover
     */
    @Override
    public Rover turnRight() {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionFromClockwise(), this.planet, loggerConsole) ;
    }

    /**
     * Méthode de déplacement (Tourner Gauche) pour le rover
     * @return une nouvelle installe de Rover
     */
    @Override
    public Rover turnLeft() {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionCounterClockwise(), this.planet, loggerConsole) ;
    }

    /**
     * Méthode de déplacement (Avancer) pour le rover
     * @return une nouvelle installe de Rover
     */
    @Override
    public Rover moveForward() {

        Coordinates coordinates = this.position.callAddCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates))
        {
            loggerConsole.log("Obstacle found");
            return this;
        }
        Coordinates newCoordinates = new Coordinates(coordinates.x(), coordinates.y());
        return new Rover(newCoordinates, this.getCurrentDirection(), this.planet, loggerConsole);
    }

    /**
     * Méthode de déplacement (Reculer) pour le rover
     * @return une nouvelle installe de Rover
     */
    @Override
    public Rover moveBack() {
        Coordinates coordinates = this.position.callSubCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates))
        {
            loggerConsole.log("Obstacle found");
            return this;
        }
        Coordinates newCoordinates = new Coordinates(coordinates.x(), coordinates.y());
        return new Rover(newCoordinates, this.getCurrentDirection(), this.planet, loggerConsole);
    }

    /**
     * Méthode pour lancer la connexion serveur
     */
    public void startRover(CancellationToken token, Rover rover) throws ExecutionException, InterruptedException {
        server = new Server(this.loggerConsole);
        while (!token.isCancellationRequested()) {
            if (token.isCancellationRequested()) {
                break;
            }
            rover = (Rover) server.listenAndSendResponse(rover).get();
        }
    }

    /**
     * Méthode principale pour exécuter le programme (Côté Serveur)
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CancellationToken token = new CancellationToken();
        Rover rover = new Rover(new Coordinates(1, 2), Direction.North, new PlanetWithoutObstacles(5, 5), new LoggerConsole());
        rover.startRover(token, rover);
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Rover roverToCompare))
            return false;

        return roverToCompare.getCurrentCoordinates().x() != getCurrentCoordinates().x()
                || roverToCompare.getCurrentCoordinates().y() != getCurrentCoordinates().y()
                || roverToCompare.getCurrentDirection() != getCurrentDirection();
    }

    public void shutdown() throws IOException {

        server.tearDown();
        System.out.println("Close server");

    }
}
