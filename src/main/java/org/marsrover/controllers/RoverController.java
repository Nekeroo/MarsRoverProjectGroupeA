package org.marsrover.controllers;

import org.marsrover.models.Rover;
import org.marsrover.enums.RoverCommands;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Direction;

import java.util.List;

// Service
public class RoverController
{
    private Rover rover;

    public RoverController(Rover rover)
    {
            this.rover = rover;
    }

    public Rover processSequence(List<String> sequenceOfStrings) // TODO : indentations
    {
        List<RoverCommands> commands = RoverCommands.getCommandsFromStrings(sequenceOfStrings);
        Coordinates coordinates;
        Direction direction;
        for (RoverCommands command: commands)
        {
            direction = rover.getCurrentDirection();
            coordinates = rover.getCurrentCoordinates();
            switch (command) {
                case Z -> rover = rover.moveForward();
                case S -> rover = rover.moveBack();
                case D -> rover = rover.turnRight();
                case Q -> rover = rover.turnLeft();
            }

            if (rover.getCurrentCoordinates().equals(coordinates) && rover.getCurrentDirection().equals(direction))
            {
                break;
            }
        }
        return rover;
    }
}
