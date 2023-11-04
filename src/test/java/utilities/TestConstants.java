package utilities;

import org.marsrover.records.Direction;
import org.marsrover.records.Vector;

public class TestConstants {
    public static final Character MoveForwardCommand = 'A';
    public static final Character TurnRightCommand = 'D';
    public static final Character MoveBackCommand = 'R';
    public static final Character TurnLeftCommand = 'G';

    public static final Direction North = new Direction("N", new Vector(0, 1));
    public static final Direction South = new Direction("S", new Vector(0, -1));
    public static final Direction East = new Direction("E", new Vector(1, 0));
    public static final Direction West = new Direction("W", new Vector(-1, 0));
}
