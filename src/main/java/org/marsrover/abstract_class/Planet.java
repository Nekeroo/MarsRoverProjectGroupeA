package org.marsrover.abstract_class;

import org.marsrover.records.Coordinates;

public abstract class Planet {

    private final int height;

    private final int width;

    protected Planet(int height, int width){
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Coordinates canonise(int x, int y) {
        if (x < 0)
            return new Coordinates(getWidth() - 1, y % getHeight());
        else if (y < 0)
            return new Coordinates(x % getWidth(), getHeight() - 1);
        return new Coordinates(x % getWidth(), y % getHeight());
    }
}
