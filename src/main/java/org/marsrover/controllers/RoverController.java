package org.marsrover.controllers;

import org.marsrover.models.Rover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// Service
public final class RoverController
{
    public static final char MoveForwardCommand = 'A';
    public static final char MoveBackCommand = 'R';
    public static final char TurnRightCommand = 'D';
    public static final char TurnLeftCommand = 'G';

    private static final Map<Character, Function<Rover, Rover>> commands = new HashMap<>();

    static {
        commands.put(MoveForwardCommand, Rover::moveForward);
        commands.put(MoveBackCommand, Rover::moveBack);
        commands.put(TurnRightCommand, Rover::turnRight);
        commands.put(TurnLeftCommand, Rover::turnLeft);
    }

    public static Rover execute(Rover rover, List<Character> commandList) {
        for (char command : commandList) {
            rover = execute(rover, command);
        }
        return rover;
    }

    public static Rover execute(Rover rover, char command) {
        return commands.get(command).apply(rover);
    }
}
