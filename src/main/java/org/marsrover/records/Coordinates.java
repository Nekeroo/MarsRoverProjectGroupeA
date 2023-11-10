package org.marsrover.records;

import org.marsrover.models.Height;
import org.marsrover.models.Width;

// Objet Valeur
public record Coordinates(Integer x, Integer y)
{
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + "\n";
    }

    public Coordinates addCoordinates(Coordinates coordinates, Direction direction) {
        Integer x = coordinates.x() + direction.vectorX();
        Integer y = coordinates.y() + direction.vectorY();
        return new Coordinates(x,y);
    }

    public Coordinates subtractCoordinates(Coordinates coordinates, Direction direction) {
        Integer x = coordinates.x() - direction.vectorX();
        Integer y = coordinates.y() - direction.vectorY();
        return new Coordinates(x,y);
    }

    public Coordinates moduloCoordinates(Coordinates coordinates, Height height, Width width) {
        Integer x = (((coordinates.x() % width.getValue()) + width.getValue()) % width.getValue());
        Integer y = (((coordinates.y() % height.getValue()) + height.getValue()) % height.getValue());
        return new Coordinates(x,y);
    }
}
