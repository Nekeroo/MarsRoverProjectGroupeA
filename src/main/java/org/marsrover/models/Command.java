package org.marsrover.models;

public class Command {
    private final Character value;

    public Command(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
