package org.marsrover.controllers;

import org.marsrover.models.Rover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// Service
public final class RoverController
{
    private static final Character MoveForwardCommand = 'A';
    private static final Character MoveBackCommand = 'R';
    private static final Character TurnRightCommand = 'D';
    private static final Character TurnLeftCommand = 'G';

    private static final Map<Character, Function<Rover, Rover>> commands = new HashMap<>();

    static {
        commands.put(MoveForwardCommand, Rover::moveForward);
        commands.put(MoveBackCommand, Rover::moveBack);
        commands.put(TurnRightCommand, Rover::turnRight);
        commands.put(TurnLeftCommand, Rover::turnLeft);
    }

    public static Rover execute(Rover rover, List<Character> commandList) {
        for (Character command : commandList) {
            rover = execute(rover, command);
        }
        return rover;
    }

    public static Rover execute(Rover rover, Character command) {
        return commands.get(command).apply(rover);
    }
}
