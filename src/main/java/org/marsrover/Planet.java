package org.marsrover;

public class Planet {

    private int height;

    private int width;

    private String name;

    public Planet(int height, int width, String name)
    {
        this.height = height;
        this.width = width;
        this.name = name;
    }

    public Coordinates Canonise(int x, int y)
    {
        if (x < 0)
            return new Coordinates(width-1, y%height);
        else if (y < 0)
            return new Coordinates(x%width, height-1);
        return new Coordinates(x%width,y%height);
    }
}
