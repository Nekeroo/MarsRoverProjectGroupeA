package org.marsrover.rover;

import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.rover.commands.IRoverCommand;
import org.marsrover.rover.commands.RoverCommandMoveForward;
import org.marsrover.rover.commands.RoverCommands;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Service
public class RoverController
{
    private IRover rover;

    public RoverController(IRover rover)
    {
            this.rover = rover;
    }

    public IRover processSequence(String commandSequence)
    {
        List<RoverCommands> commands = new ArrayList<>();
        if (commandSequence.length() > 1) {
            commands = RoverCommands.getCommandsFromStrings(List.of(commandSequence.split("")));
        }
        else {
            commands.add(RoverCommands.getCommandFromString(commandSequence));
        }
        Coordinates coordinates;
        Direction direction;
        for (RoverCommands command: commands)
        {
            direction = rover.getCurrentDirection();
            coordinates = rover.getCurrentCoordinates();
            switch (command) {
                case Z -> rover = new RoverCommandMoveForward().execute(rover);
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

    /*public static void main(String[] args) {
        Rover rover = new Rover(new Coordinates(1,2), Direction.North, new PlanetWithoutObstacles(5,5));
        RoverController rc = new RoverController(rover);
        new Thread(() -> {
            try {
                rc.rover.getSocketCommunicator().startListening();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
*/
}
