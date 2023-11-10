package org.marsrover.console;

public interface IConsole extends ILogger{
    String readLine();
    void writeLine(String message);
}
