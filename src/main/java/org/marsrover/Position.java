package org.marsrover;

import org.marsrover.enums.Direction;

public class Position {

    private int x;

    private int y;

    private Direction direction;

    private Planet planet;

    public Position(int x, int y, Direction direction, Planet planet) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.planet = planet;
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

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }
}
