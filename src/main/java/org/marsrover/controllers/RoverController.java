package org.marsrover.controllers;

import org.marsrover.collections.CommandCollection;
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

    private static final CommandCollection commandCollection = new CommandCollection();

    static {
        commandCollection.addCommand(MoveForwardCommand, Rover::moveForward);
        commandCollection.addCommand(MoveBackCommand, Rover::moveBack);
        commandCollection.addCommand(TurnRightCommand, Rover::turnRight);
        commandCollection.addCommand(TurnLeftCommand, Rover::turnLeft);
    }

    public static Rover execute(Rover rover, List<Command> commandList) {
        for (Command command : commandList) {
            rover = execute(rover, command);
        }
        return rover;
    }

    public static Rover execute(Rover rover, Command command) {
        return commandCollection.getCommandFunction(command).apply(rover);
    }
}
