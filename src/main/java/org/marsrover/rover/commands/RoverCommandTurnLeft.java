package org.marsrover.rover.commands;

import org.marsrover.rover.IRover;

public class RoverCommandTurnLeft implements IRoverCommand {
        @Override
        public IRover execute(IRover rover) {
            rover = rover.turnLeft();
            return rover;
        }
}
