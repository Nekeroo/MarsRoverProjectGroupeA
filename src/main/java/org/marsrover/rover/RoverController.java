package org.marsrover.rover;

import org.marsrover.rover.commands.*;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * RoverController est un controller permettant d'ordonner au Rover d'exécuter une commande selon une entrée en String.
 * Cette classe n'est plus utilisée dans notre code actuel.
 */
public class RoverController
{
    private IRover rover;

    public RoverController(IRover rover)
    {
            this.rover = rover;
    }

    public IRover processSequence(String commandSequence) {
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
                case S -> rover = new RoverCommandMoveBack().execute(rover);
                case D -> rover = new RoverCommandTurnRight().execute(rover);
                case Q -> rover = new RoverCommandTurnLeft().execute(rover);
            }

            if (rover.getCurrentCoordinates().equals(coordinates) && rover.getCurrentDirection().equals(direction))
            {
                break;
            }

        }
        return rover;
    }

}
