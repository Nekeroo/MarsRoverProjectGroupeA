package org.marsrover;

import org.marsrover.enums.Direction;

public class Position {

    private int x;

    private int y;

    private Direction direction;

    private Planete planete;

    public Position(int x, int y, Direction direction, Planete planete) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.planete = planete;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Planete getPlanete() {
        return planete;
    }

    public void setPlanete(Planete planete) {
        this.planete = planete;
    }
}
