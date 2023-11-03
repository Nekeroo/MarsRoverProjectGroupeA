package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.commands.IRoverCommand;

public class SocketCommunicator extends Communicator{

    /**
     * Cette classe a pour but de simplement réaliser des communications par Socket basiques
     * Envoi / Récepetion d'informations sans réel traitement.
     */
    public SocketCommunicator() {
        super();
    }

}
