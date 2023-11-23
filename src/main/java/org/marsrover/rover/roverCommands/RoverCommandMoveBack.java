package org.marsrover.rover.roverCommands;

import org.marsrover.rover.IRover;

/**
 * RoverCommandMoveBack implémente IRoverCommand.
 * Regroupe le nom de la commande et l'exécution à réaliser sur un objet IRover
 */
public class RoverCommandMoveBack implements IRoverCommand {

    public final static String COMMAND = "S";

    @Override
    public IRover execute(IRover rover) {
        rover = rover.moveBack();
        return rover;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
