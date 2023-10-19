package org.marsrover.controllers;
import org.marsrover.models.Rover;
import org.marsrover.enums.RoverCommands;

import java.util.List;

public class Controller {
    private Rover rover;
    
    public Controller(Rover rover)
    {
            this.rover = rover;
    }
    
    public Rover processSequence(List<String> sequence)
    {
        List<RoverCommands> commands = RoverCommands.fromString(sequence);
        for (RoverCommands command: commands)
        {
            if (rover.isObstacleInFront())
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
