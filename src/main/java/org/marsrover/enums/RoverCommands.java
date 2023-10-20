package org.marsrover.enums;

import org.marsrover.models.Rover;

import java.util.*;
import java.util.function.Function;

// Objet Valeur
public enum RoverCommands
{
    Z("Z"),
    Q("Q"),
    S("S"),
    D("D");

    private Map<String, Function<Rover, Rover>> commandes = new HashMap<String, Function<Rover, Rover>>();

    private final String command;

    RoverCommands(String command)
    {
        this.command = command;
    }

    public String getCommand()
    {
        return command;
    }

    public static List<RoverCommands> getCommandsFromStrings(List<String> seqString) // TODO : indentations
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
