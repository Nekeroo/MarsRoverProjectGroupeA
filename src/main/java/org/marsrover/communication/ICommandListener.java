package org.marsrover.communication;

import org.marsrover.rover.IRover;

import java.io.IOException;

public interface ICommandListener {

    // TODO : Ajouter paramètre de la méthode.
    public String startListening(IRover rover) throws IOException;

}
