package org.marsrover.rover.commands;

import org.marsrover.rover.IRover;

public class RoverCommandMoveBack implements IRoverCommand {

    @Override
    public IRover execute(IRover rover) {
        rover = rover.moveBack();
        return rover;
    }
}
