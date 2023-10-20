package org.marsrover.controllers;
import org.marsrover.models.PlanetWithObstacle;
import org.marsrover.models.Rover;
import org.marsrover.enums.RoverCommands;

import java.util.List;

// Service
public class RoverController {
    private Rover rover;
    
    public RoverController(Rover rover)
    {
            this.rover = rover;
    }
    
    public Rover processSequence(List<String> sequenceOfStrings)
    {
        List<RoverCommands> commands = RoverCommands.getCommandsFromStrings(sequenceOfStrings);
        for (RoverCommands command: commands)
        {
            if (rover.getPlanet() instanceof PlanetWithObstacle planetWithObstacle && planetWithObstacle.isObstaclesInFrontOfRover(rover.getCurrentCoordinates().x(), rover.getCurrentCoordinates().y(), rover.getCurrentDirection()))
                break;
            switch (command)
            {
                case Z ->
                    rover = rover.moveForward();
                case S ->
                    rover = rover.moveBack();
                case D ->
                    rover = rover.turnRight();
                case Q ->
                    rover = rover.turnLeft();
            }
        }
        return rover;
    }
}
