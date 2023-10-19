package org.marsrover.models;

public class Planet
{
    private final int height;
    private final int width;
    private final Obstacle obstacle;

    public Planet(int height, int width, Obstacle obstacle)
    {
        this.height = height;
        this.width = width;
        this.obstacle = obstacle;
    }

    public Coordinates Canonise(int x, int y)
    {
        if (x < 0)
            return new Coordinates(width-1, y%height);
        else if (y < 0)
            return new Coordinates(x%width, height-1);
        return new Coordinates(x%width,y%height);
    }

    public boolean checkObstacle(int x, int y)
    {
        return this.obstacle.coordinates().x() == x && this.obstacle.coordinates().y() == y;
    }

    public Obstacle getObstacle()
    {
        return obstacle;
    }
}
