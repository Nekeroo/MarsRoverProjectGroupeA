package org.marsrover.models;

import org.marsrover.abstract_class.Planet;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Position;

// Objet Valeur
@SuppressWarnings("DuplicatedCode")
public final class Rover {

    private final Position position;
    private final Planet planet;

    public Rover(Coordinates coordinates, Direction direction, Planet planet)
    {
        Coordinates canonisedCoordinates = planet.canonise(coordinates.x(), coordinates.y());
        this.position = new Position(canonisedCoordinates, direction);
        this.planet = planet;
        System.out.printf("Coordonnées : " + this.getCurrentCoordinates());
    }

    public Direction getCurrentDirection()
    {
        return position.direction();
    }

    public Coordinates getCurrentCoordinates()
    {
        return position.coordinates();
    }

    public Planet getPlanet()
    {
        return planet;
    }
    public Rover turnRight()
    {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionFromClockwise(), this.planet) ;
    }

    public Rover turnLeft()
    {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionCounterClockwise(), this.planet) ;
    }

    public Rover moveForward()
    {
        int x = this.getCurrentCoordinates().x() + this.getCurrentDirection().getVectorX();
        int y = this.getCurrentCoordinates().y() + this.getCurrentDirection().getVectorY();
        Coordinates newCoordinates = new Coordinates(x, y);
        return new Rover(newCoordinates, this.getCurrentDirection(), this.planet);
    }

    public Rover moveBack()
    {
        int x = this.getCurrentCoordinates().x() - this.getCurrentDirection().getVectorX();
        int y = this.getCurrentCoordinates().y() - this.getCurrentDirection().getVectorY();
        Coordinates newCoordinates = new Coordinates(x, y);
        return new Rover(newCoordinates, this.getCurrentDirection(), this.planet);
    }
}
