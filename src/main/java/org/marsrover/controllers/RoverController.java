package org.marsrover.controllers;

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
            switch (command) {
                case Z -> {
                    if (rover.getPlanet().isObstaclesAt(rover.getCurrentCoordinates().x(), rover.getCurrentCoordinates().y(), rover.getCurrentDirection())) {
                        return rover;
                    } else {
                        rover = rover.moveForward();
                    }
                }
                case S -> {
                    if (rover.getPlanet().isObstaclesAt(rover.getCurrentCoordinates().x(), rover.getCurrentCoordinates().y(), rover.getCurrentDirection().getCounterDirection())) {
                        return rover;
                    } else {
                        rover = rover.moveBack();
                    }
                }
                case D -> rover = rover.turnRight();
                case Q -> rover = rover.turnLeft();
            }
        }
        return rover;
    }
}
