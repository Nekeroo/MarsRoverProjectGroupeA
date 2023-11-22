package org.marsrover.rover.roverCommands;

import org.marsrover.rover.IRover;

public interface IRoverCommand {

    IRover execute(IRover rover);

    String getCommand();
}
