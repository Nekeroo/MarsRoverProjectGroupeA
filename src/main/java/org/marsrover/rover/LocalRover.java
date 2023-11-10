package org.marsrover.rover;

import org.marsrover.config.CancellationToken;
import org.marsrover.console.Logger;
import org.marsrover.communication.Server;
import org.marsrover.planet.Planet;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

import java.util.concurrent.ExecutionException;

// Objet Valeur
public final class LocalRover implements IRover
{
    private final Logger logger;
    private final Position position;
    private final Planet planet;

    public LocalRover(Coordinates coordinates, Direction direction, Planet planet, Logger logger) {
        Coordinates canonisedCoordinates = planet.canonise(coordinates);
        this.position = new Position(canonisedCoordinates, direction);
        this.planet = planet;
        this.logger = logger;
        logger.log("Coordonnées : " + this.getCurrentCoordinates() + " / Direction : " + this.getCurrentDirection() + "\n");
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
     * @return une nouvelle installe de LocalRover
     */
    @Override
    public LocalRover turnRight() {
        return new LocalRover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionFromClockwise(), this.planet, logger) ;
    }

    /**
     * Méthode de déplacement (Tourner Gauche) pour le rover
     * @return une nouvelle installe de LocalRover
     */
    @Override
    public LocalRover turnLeft() {
        return new LocalRover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionCounterClockwise(), this.planet, logger) ;
    }

    /**
     * Méthode de déplacement (Avancer) pour le rover
     * @return une nouvelle installe de LocalRover
     */
    @Override
    public LocalRover moveForward() {

        Coordinates coordinates = this.getCurrentCoordinates().addCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates))
        {
            logger.log("Obstacle found");
            return this;
        }
        Coordinates newCoordinates = new Coordinates(coordinates.x(), coordinates.y());
        return new LocalRover(newCoordinates, this.getCurrentDirection(), this.planet, logger);
    }

    /**
     * Méthode de déplacement (Reculer) pour le rover
     * @return une nouvelle installe de LocalRover
     */
    @Override
    public LocalRover moveBack() {
        Coordinates coordinates = this.getCurrentCoordinates().subCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates))
        {
            logger.log("Obstacle found");
            return this;
        }
        Coordinates newCoordinates = new Coordinates(coordinates.x(), coordinates.y());
        return new LocalRover(newCoordinates, this.getCurrentDirection(), this.planet, logger);
    }

    /**
     * Méthode pour lancer la connexion serveur
     */
    public void startRover(CancellationToken token, LocalRover rover) throws ExecutionException, InterruptedException {
        Server server = new Server(this.logger);
        while (!token.isCancellationRequested()) {
            if (token.isCancellationRequested()) {
                break;
            }
            rover = (LocalRover) server.listenAndSendResponse(rover).get();
        }
    }

    /**
     * Méthode principale pour exécuter le programme (Côté Serveur)
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CancellationToken token = new CancellationToken();
        LocalRover rover = new LocalRover(new Coordinates(1, 2), Direction.North, new PlanetWithoutObstacles(5, 5), new Logger());
        rover.startRover(token, rover);
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof LocalRover roverToCompare))
            return false;

        return roverToCompare.getCurrentCoordinates().x() != getCurrentCoordinates().x()
                || roverToCompare.getCurrentCoordinates().y() != getCurrentCoordinates().y()
                || roverToCompare.getCurrentDirection() != getCurrentDirection();
    }
}
