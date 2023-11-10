package utilities;

import org.marsrover.models.Command;
import org.marsrover.records.Direction;
import org.marsrover.records.Vector;

public class TestConstants {
    public static final Command MoveForwardCommand = new Command('A');
    public static final Command MoveBackCommand = new Command('R');
    public static final Command TurnRightCommand = new Command('D');
    public static final Command TurnLeftCommand = new Command('G');

    public static final Direction North = new Direction("N", new Vector(0, 1));
    public static final Direction South = new Direction("S", new Vector(0, -1));
    public static final Direction East = new Direction("E", new Vector(1, 0));
    public static final Direction West = new Direction("W", new Vector(-1, 0));
}
