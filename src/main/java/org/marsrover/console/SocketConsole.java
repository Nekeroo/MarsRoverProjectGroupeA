package org.marsrover.console;

import org.marsrover.console.IConsole;
import org.marsrover.console.Logger;

import java.io.*;
import java.net.Socket;

public class SocketConsole implements IConsole {
    private final Socket socket;
    private final Logger logger;
    public SocketConsole(Socket socket, Logger logger) {
        this.socket = socket;
        this.logger = logger;
    }

    @Override
    public String readLine() {
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
        logger.log(message);
    }
}
