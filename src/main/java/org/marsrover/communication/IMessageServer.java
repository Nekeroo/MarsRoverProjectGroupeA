package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.LocalRover;

import java.util.concurrent.CompletableFuture;

public interface IMessageServer {
    CompletableFuture<IRover> listenAndSendResponse(LocalRover rover);
}