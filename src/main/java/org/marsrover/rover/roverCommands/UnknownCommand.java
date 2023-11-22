package org.marsrover.rover.roverCommands;

import org.marsrover.rover.IRover;

public class UnknownCommand implements IRoverCommand
{
    @Override
    public IRover execute(IRover rover) {
        return null;
    }

    @Override
    public String getCommand() {
        return "X";
    }
}
