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

}
