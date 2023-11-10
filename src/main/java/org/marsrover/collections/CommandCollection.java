package org.marsrover.collections;


import org.marsrover.models.Command;
import org.marsrover.models.Rover;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandCollection {
    private final Map<Character, Function<Rover, Rover>> commands = new HashMap<>();

    public CommandCollection() {
    }

    public void addCommand(Command command, Function<Rover, Rover> roverFunction) {
        commands.put(command.getValue(), roverFunction);
    }

    public Function<Rover, Rover> getCommandFunction(Command command) {
        return commands.get(command.getValue());
    }
}
