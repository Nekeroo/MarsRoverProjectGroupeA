package org.marsrover.rover.commands;

import org.marsrover.rover.IRover;

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
