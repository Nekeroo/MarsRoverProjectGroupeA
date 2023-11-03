package org.marsrover.rover.commands;

import org.marsrover.rover.IRover;
import org.marsrover.rover.Rover;
import org.marsrover.topologie.Coordinates;

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
