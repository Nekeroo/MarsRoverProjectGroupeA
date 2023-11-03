package org.marsrover.rover.commands;

import org.marsrover.rover.IRover;

public interface IRoverCommand {

    IRover execute(IRover rover);

    String getCommand();
}
