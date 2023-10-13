package org.marsrover;

import org.marsrover.enums.Direction;

public class Position {

    private Coordonnees coordonnees;

    private Direction direction;

    public Position(Coordonnees coordonnees, Direction direction) {
        this.coordonnees = coordonnees;
        this.direction = direction;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
