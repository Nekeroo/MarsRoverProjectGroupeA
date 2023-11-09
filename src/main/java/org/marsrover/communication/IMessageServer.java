package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.LocalRover;

public interface IMessageServer {

    IRover listenAndSendResponse(LocalRover rover);

}
