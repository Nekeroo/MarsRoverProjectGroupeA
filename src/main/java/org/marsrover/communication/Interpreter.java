package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.NetworkRover;
import org.marsrover.rover.commands.*;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

public class Interpreter {

    /**
     * Permet de mappuer une chaine de caractère sous forme de RoverCommand
     * @param data
     * @return
     */
    public IRoverCommand mapStringToCommand(String data) {
        switch (data) {
            case RoverCommandMoveForward.COMMAND:
                return new RoverCommandMoveForward();
            case RoverCommandMoveBack.COMMAND:
                return new RoverCommandMoveBack();
            case RoverCommandTurnLeft.COMMAND:
                return new RoverCommandTurnLeft();
            case RoverCommandTurnRight.COMMAND:
                return new RoverCommandTurnRight();
        }
        return null;
    }

    /**
     * Permet de mapper une commande au format String
     * @param command
     * @return
     * Exemple : La commande RoverCommandMoveForward ==> "Z"
     */
    public String mapCommandToString(IRoverCommand command) {
        return command.getCommand();
    }

    /**
     * Permet de mapper une chaîne de caractère en Rover
     * @param data
     * @return
     * Exemple : "1,2,N" => Rover(1,2,N)
     * Où x = 1, y = 2 et Direction = Direction.NORTH
     */

    // TODO : Voir pour la planète
    public NetworkRover mapRoverFromString(String data, Communicator communicator) {
        String[] roverInfos = data.split(",");
        int positionX = Integer.parseInt(roverInfos[0]);
        int positionY = Integer.parseInt(roverInfos[1]);
        Direction direction = Direction.getDirectionFromString(roverInfos[2]);
        return new NetworkRover(new Position(new Coordinates(positionX, positionY), direction), communicator);
    }

}
