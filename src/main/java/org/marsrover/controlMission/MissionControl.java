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
        NetworkRover networkRover = new NetworkRover(new Position(new Coordinates(1, 2), Direction.North));
        Communicator communicator = new Communicator();

        boolean canContinue = true;
        while (canContinue) {
            // Déclaration des variables utiles
            Scanner console = new Scanner(System.in);
            String nextCommand = console.nextLine();
            Interpreter interpreter = new Interpreter();

            // On envoie la commande au rover
            communicator.sendCommand(nextCommand);


            System.out.println("Sent command: " + nextCommand);
        }
    }

}
