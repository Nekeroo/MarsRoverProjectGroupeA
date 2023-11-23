package org.marsrover.rover.roverCommands;

import org.marsrover.rover.IRover;

/**
 * RoverCommandTurnRight implémente IRoverCommand.
 * Regroupe le nom de la commande et l'exécution à réaliser sur un objet IRover
 */
public class RoverCommandTurnRight implements IRoverCommand {

    public final static String COMMAND = "D";

    @Override
    public IRover execute(IRover rover) {
        rover = rover.turnRight();
        return rover;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
