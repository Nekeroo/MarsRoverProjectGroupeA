package org.marsrover.rover;

import org.marsrover.communication.Communicator;
import org.marsrover.communication.Interpreter;
import org.marsrover.planet.Planet;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

import java.io.IOException;

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

    public static void main(String[] args) {
        // Déclaration des variables utiles
        PlanetWithoutObstacles planet = new PlanetWithoutObstacles(10, 10);
        LocalRover rover = new LocalRover(new Coordinates(1, 2), Direction.North, planet);
        Communicator communicator = new Communicator(8080, 8081);
        Interpreter interpreter = new Interpreter();
        LocalRover roverResult = null;
        String command = communicator.startListening(rover);
        while(true) {
            if (interpreter.mapStringToCommand(command) != null) {
                roverResult = (LocalRover) interpreter.mapStringToCommand(command).execute(rover);
            }

            if (roverResult != null) {
                communicator.sendAnswer(roverResult);
            }
        }
    }

}
