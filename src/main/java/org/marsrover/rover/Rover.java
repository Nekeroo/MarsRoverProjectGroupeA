package org.marsrover.rover;

import org.marsrover.communication.SocketCommunicator;
import org.marsrover.planet.Planet;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

import java.io.IOException;

// Objet Valeur
public final class Rover implements IRover
{

    private SocketCommunicator socketCommunicator = new SocketCommunicator();
    private final Position position;
    private final Planet planet;

    public Rover(Coordinates coordinates, Direction direction, Planet planet)
    {
        Coordinates canonisedCoordinates = planet.canonise(coordinates);
        this.position = new Position(canonisedCoordinates, direction);
        this.planet = planet;
        System.out.printf("Coordonnées : " + this.getCurrentCoordinates());
    }

    @Override
    public Direction getCurrentDirection()
    {
        return position.direction();
    }

    @Override
    public Coordinates getCurrentCoordinates()
    {
        return position.coordinates();
    }

    public SocketCommunicator getSocketCommunicator() {
        return socketCommunicator;
    }

    @Override
    public Rover turnRight()
    {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionFromClockwise(), this.planet) ;
    }

    @Override
    public Rover turnLeft()
    {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionCounterClockwise(), this.planet) ;
    }

    @Override
    public Rover moveForward()
    {

        Coordinates coordinates = this.getCurrentCoordinates().addCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates))
        {
            System.out.println("Obstacle found");
            return this;
        }
        Coordinates newCoordinates = new Coordinates(coordinates.x(), coordinates.y());
        return new Rover(newCoordinates, this.getCurrentDirection(), this.planet);
    }

    @Override
    public Rover moveBack()
    {
        Coordinates coordinates = this.getCurrentCoordinates().subCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates))
        {
            System.out.println("Obstacle found");
            return this;
        }
        Coordinates newCoordinates = new Coordinates(coordinates.x(), coordinates.y());
        return new Rover(newCoordinates, this.getCurrentDirection(), this.planet);
    }

    public static void main(String[] args) {
        Rover rover = new Rover(new Coordinates(1,2), Direction.North, new PlanetWithoutObstacles(5,5));
        new Thread(() -> {
            try {
                rover.socketCommunicator.startListening();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
