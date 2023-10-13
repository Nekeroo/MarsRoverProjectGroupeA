package org.marsrover;
import org.marsrover.interfaces.IRover;

public class Rover implements IRover {

    private Position position;
    private Planet planet;

    public Rover(Coordinates coordinates, Direction direction, Planet planet)
    {
        Coordinates coordinatesCanon = planet.Canonise(coordinates.getX(), coordinates.getY());
        this.position = new Position(coordinatesCanon, direction);
        this.planet = planet;
        System.out.printf("Coordonnées : " + this.getCurrentCoordinates());
    }

    @Override
    public Position getCurrentPosition()
    {
        return position;
    }

    @Override
    public Direction getCurrentDirection()
    {
        return position.getDirection();
    }

    @Override
    public Coordinates getCurrentCoordinates()
    {
        return position.getCoordinates();
    }

    @Override
    public Rover turnRight()
    {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextClockwise(), this.planet) ;
    }

    @Override
    public Rover turnLeft()
    {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextCounterClockwise(), this.planet) ;
    }

    @Override
    public Rover moveForward()
    {
        int x = this.getCurrentCoordinates().getX() + this.getCurrentDirection().getVectorX();
        int y = this.getCurrentCoordinates().getY() + this.getCurrentDirection().getVectorY();
        Coordinates newCoordinates = new Coordinates(x, y);
        return new Rover(newCoordinates, this.getCurrentDirection(), this.planet);
    }

    @Override
    public Rover moveBack()
    {
        int x = this.getCurrentCoordinates().getX() - this.getCurrentDirection().getVectorX();
        int y = this.getCurrentCoordinates().getY() - this.getCurrentDirection().getVectorY();
        Coordinates newCoordinates = new Coordinates(x, y);
        return new Rover(newCoordinates, this.getCurrentDirection(), this.planet);
    }
}
