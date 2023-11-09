package org.marsrover.communication;

public interface IMessageClient {

    String SendAndWaitForResponse(String message);

}
