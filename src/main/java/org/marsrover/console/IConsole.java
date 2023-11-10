package org.marsrover.console;

public interface IConsole extends ILogger{
    String readline();
    void writeLine(String message);
}
