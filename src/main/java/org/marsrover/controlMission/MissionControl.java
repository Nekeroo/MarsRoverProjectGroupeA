package org.marsrover.controlMission;

import org.marsrover.communication.Communicator;
import org.marsrover.communication.Interpreter;
import org.marsrover.rover.NetworkRover;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

import java.util.Scanner;

public class MissionControl {

    public static void main(String[] args) {
        Communicator communicator = new Communicator(8081, 8080);
        Interpreter interpreter = new Interpreter();
        NetworkRover networkRover = new NetworkRover(new Position(new Coordinates(1, 2), Direction.North),communicator);
        String roverResult = communicator.startListening(networkRover);
        Scanner console = new Scanner(System.in);
        boolean canContinue = true;
        while (canContinue) {
            // Déclaration des variables utiles
            String nextCommand = console.nextLine();

            // On envoie la commande au rover
            communicator.sendCommand(nextCommand);
            System.out.println("Sent command: " + nextCommand);

            // On récupère le résultat
            if (roverResult != "") {
                networkRover = interpreter.mapRoverFromString(roverResult, communicator);
            }

            System.out.println("Rover Moved ! x = " + networkRover.getCurrentCoordinates().x() + " y = " + networkRover.getCurrentCoordinates().y() + " direction = " + networkRover.getCurrentDirection().toString());
        }
    }

}
