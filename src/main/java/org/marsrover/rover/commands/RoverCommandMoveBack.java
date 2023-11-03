package org.marsrover.rover.commands;

import org.marsrover.rover.IRover;

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
