package org.marsrover.enums;

import java.util.ArrayList;
import java.util.List;

public enum RoverCommands {
    Z("Z"),
    Q("Q"),
    S("S"),
    D("D");

    private final String command;

    RoverCommands(String command)
    {
        this.command = command;
    }

    public String getCommand()
    {
        return command;
    }

    public static List<RoverCommands> fromString(List<String> seqString)
    {
        List<RoverCommands> sequence = new ArrayList<>();
        for (String command:seqString)
        {
            if (command.equals(Z.getCommand()))
                sequence.add(Z);
            else if (command.equals(S.getCommand()))
                sequence.add(S);
            else if (command.equals(D.getCommand()))
                sequence.add(D);
            else if (command.equals(Q.getCommand()))
                sequence.add(Q);
        }
        return sequence;
    }
}
