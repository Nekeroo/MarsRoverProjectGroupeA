package org.marsrover.communication;

public interface IConsole extends ILogger{
    String readline();
    void writeLine(String message);
}
