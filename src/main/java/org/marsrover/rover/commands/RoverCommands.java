package org.marsrover.rover.commands;

import java.util.ArrayList;
import java.util.List;

// Objet Valeur
public enum RoverCommands
{
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

    public static List<RoverCommands> getCommandsFromStrings(List<String> seqString)
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

    public static RoverCommands getCommandFromString(String command)
    {
        if (command.equals(Z.getCommand()))
            return Z;
        else if (command.equals(S.getCommand()))
            return S;
        else if (command.equals(D.getCommand()))
            return D;
        else if (command.equals(Q.getCommand()))
            return Q;
        return null;
    }
}
