package org.marsrover.rover.commands;

import org.marsrover.rover.IRover;

public class RoverCommandTurnRight implements IRoverCommand {
    @Override
    public IRover execute(IRover rover) {
        rover = rover.turnRight();
        return rover;
    }
}
