package org.marsrover.rover.commands;

import org.marsrover.rover.IRover;

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
