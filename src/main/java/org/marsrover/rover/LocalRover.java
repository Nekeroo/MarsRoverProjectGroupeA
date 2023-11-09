package org.marsrover.rover;

import org.marsrover.communication.CancellationToken;
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

    private final Position position;
    private final Planet planet;

    public LocalRover(Coordinates coordinates, Direction direction, Planet planet)
    {
        Coordinates canonisedCoordinates = planet.canonise(coordinates);
        this.position = new Position(canonisedCoordinates, direction);
        this.planet = planet;
        System.out.printf("Coordonnées : " + this.getCurrentCoordinates() + " / Direction : " + this.getCurrentDirection() + "\n");
    }

    public Direction getCurrentDirection()
    {
        return position.direction();
    }

    public Coordinates getCurrentCoordinates()
    {
        return position.coordinates();
    }

    @Override
    public LocalRover turnRight()
    {
        return new LocalRover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionFromClockwise(), this.planet) ;
    }

    @Override
    public LocalRover turnLeft()
    {
        return new LocalRover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionCounterClockwise(), this.planet) ;
    }

    @Override
    public LocalRover moveForward()
    {

        Coordinates coordinates = this.getCurrentCoordinates().addCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates))
        {
            System.out.println("Obstacle found");
            return this;
        }
        Coordinates newCoordinates = new Coordinates(coordinates.x(), coordinates.y());
        return new LocalRover(newCoordinates, this.getCurrentDirection(), this.planet);
    }

    @Override
    public LocalRover moveBack()
    {
        Coordinates coordinates = this.getCurrentCoordinates().subCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates))
        {
            System.out.println("Obstacle found");
            return this;
        }
        Coordinates newCoordinates = new Coordinates(coordinates.x(), coordinates.y());
        return new LocalRover(newCoordinates, this.getCurrentDirection(), this.planet);
    }

    public static void startRover(CancellationToken token) throws ExecutionException, InterruptedException {
        LocalRover rover = new LocalRover(new Coordinates(1, 2), Direction.North, new PlanetWithoutObstacles(5, 5));
        Server server = new Server();
        while (!token.isCancellationRequested()) {
            if (token.isCancellationRequested()) {
                break;
            }

            rover = (LocalRover) server.listenAndSendResponse(rover).get();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CancellationToken token = new CancellationToken();
        startRover(token);
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
