package org.marsrover.console;

import java.io.*;
import java.net.Socket;

public class SocketConsole implements IConsole {
    private final Socket socket;
    private final LoggerConsole loggerConsole;
    public SocketConsole(Socket socket, LoggerConsole loggerConsole) {
        this.socket = socket;
        this.loggerConsole = loggerConsole;
    }

    @Override
    public String readline() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeLine(String message) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(String message) {
        loggerConsole.log(message);
    }
}
