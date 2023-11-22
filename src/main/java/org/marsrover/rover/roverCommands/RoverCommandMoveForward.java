package org.marsrover.rover.roverCommands;

import org.marsrover.rover.IRover;

/**
 * RoverCommandMoveForward implémente IRoverCommand.
 * Regroupe le nom de la commande et l'exécution à réaliser sur un objet IRover
 */
public class RoverCommandMoveForward implements IRoverCommand {

    public final static String COMMAND = "Z";

    @Override
    public IRover execute(IRover rover) {
        rover = rover.moveForward();
        return rover;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
