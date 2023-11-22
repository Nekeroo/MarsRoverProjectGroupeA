package org.marsrover.rover.roverCommands;

import org.marsrover.rover.IRover;

/**
 * RoverCommandTurnLeft implémente IRoverCommand.
 * Regroupe le nom de la commande et l'exécution à réaliser sur un objet IRover
 */
public class RoverCommandTurnLeft implements IRoverCommand {

    public final static String COMMAND = "Q";

    @Override
        public IRover execute(IRover rover) {
            rover = rover.turnLeft();
            return rover;
        }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
