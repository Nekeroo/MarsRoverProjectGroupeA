package org.marsrover.communication;

import org.marsrover.rover.NetworkRover;
import org.marsrover.rover.roverCommands.*;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    public static final List<String> validChar = List.of("Z", "D", "S", "Q");

    /**
     * Permet de mapper une chaine de caractère sous forme de RoverCommand
     */
    public IRoverCommand mapStringToCommand(String data) {
        return switch (data) {
            case RoverCommandMoveForward.COMMAND -> new RoverCommandMoveForward();
            case RoverCommandMoveBack.COMMAND -> new RoverCommandMoveBack();
            case RoverCommandTurnLeft.COMMAND -> new RoverCommandTurnLeft();
            case RoverCommandTurnRight.COMMAND -> new RoverCommandTurnRight();
            default -> new UnknownCommand();
        };
    }

    /**
     * Permet de mapper une chaine de caractère sous forme de List<IRoverCommand>
     */
    public List<IRoverCommand> mapStringToCommandList(String data) {
        List<IRoverCommand> commandsToExecute = new ArrayList<>();
        List<String> commandsString = List.of(data.split(""));
        for (String commandString : commandsString){
            switch (commandString) {
                case RoverCommandMoveForward.COMMAND -> commandsToExecute.add(new RoverCommandMoveForward());
                case RoverCommandMoveBack.COMMAND -> commandsToExecute.add(new RoverCommandMoveBack());
                case RoverCommandTurnLeft.COMMAND -> commandsToExecute.add(new RoverCommandTurnLeft());
                case RoverCommandTurnRight.COMMAND -> commandsToExecute.add(new RoverCommandTurnRight());
                default -> commandsToExecute.add(new UnknownCommand());
            }
        }
        return commandsToExecute;
    }

    /**
     * Permet de mapper une commande au format String
     * @return
     * Exemple : La commande RoverCommandMoveForward ==> "Z"
     */
    public String mapCommandToString(IRoverCommand command) {
        return command.getCommand();
    }

    /**
     * Permet de mapper une chaîne de caractère en Rover
     * @return
     * Exemple : "1,2,N" => Rover(1,2,N)
     * Où x = 1, y = 2 et Direction = Direction.NORTH
     */

    // TODO : Voir pour la planète
    public NetworkRover mapRoverFromString(String data) {
        String[] roverInfos = data.split(",");
        int positionX = Integer.parseInt(roverInfos[0]);
        int positionY = Integer.parseInt(roverInfos[1]);
        Direction direction = Direction.mapDirectionFromString(roverInfos[2]);
        return new NetworkRover(new Position(new Coordinates(positionX, positionY), direction));
    }

    public boolean isSequenceValid(List<String> sequence)
    {
        for (String command : sequence){
            if (!validChar.contains(command))
                return false;
        }
        return true;
    }
}
