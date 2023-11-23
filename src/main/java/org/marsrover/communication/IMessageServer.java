package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.Rover;

import java.util.concurrent.CompletableFuture;

public interface IMessageServer {
    CompletableFuture<IRover> listenAndSendResponse(Rover rover);
}