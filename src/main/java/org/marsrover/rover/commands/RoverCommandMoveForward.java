package org.marsrover.rover.commands;

import org.marsrover.rover.IRover;

public class RoverCommandMoveForward implements IRoverCommand {

    @Override
    public IRover execute(IRover rover) {
        rover = rover.moveForward();
        return rover;
    }
}
