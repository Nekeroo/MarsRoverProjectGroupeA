package org.marsrover;

public class Planet {

    private int height;

    private int width;

    private String name;

    public Planet(int height, int width, String name) {
        this.height = height;
        this.width = width;
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }

    public Position rendu(Rover rover) {
        rover.getPosition().getCoordonnees().setX(rover.getPosition().getCoordonnees().getX() % this.width);
        rover.getPosition().getCoordonnees().setY(rover.getPosition().getCoordonnees().getY() % this.height);
        return rover.getPosition();
    }

}
