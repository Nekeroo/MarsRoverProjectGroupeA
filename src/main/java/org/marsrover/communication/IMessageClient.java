package org.marsrover.communication;

import org.marsrover.rover.IRover;

public interface IMessageClient {

    IRover SendAndWaitForResponse(String message);

}
