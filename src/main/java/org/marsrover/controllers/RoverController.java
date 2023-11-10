package org.marsrover.controllers;

import org.marsrover.models.Command;
import org.marsrover.models.Rover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// Service
public final class RoverController
{
    private static final Command MoveForwardCommand = new Command('A');
    private static final Command MoveBackCommand = new Command('R');
    private static final Command TurnRightCommand = new Command('D');
    private static final Command TurnLeftCommand = new Command('G');

    private static final Map<Character, Function<Rover, Rover>> commands = new HashMap<>();

    static {
        commands.put(MoveForwardCommand.getValue(), Rover::moveForward);
        commands.put(MoveBackCommand.getValue(), Rover::moveBack);
        commands.put(TurnRightCommand.getValue(), Rover::turnRight);
        commands.put(TurnLeftCommand.getValue(), Rover::turnLeft);
    }

    public static Rover execute(Rover rover, List<Command> commandList) {
        for (Command command : commandList) {
            rover = execute(rover, command);
        }
        return rover;
    }

    public static Rover execute(Rover rover, Command command) {
        return commands.get(command.getValue()).apply(rover);
    }
}
