package org.marsrover.records;


import org.marsrover.models.Direction;

// Objet Valeur
public record Coordinates(int x, int y)
{
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + "\n";
    }

    public Coordinates addCoordinates(Coordinates coordinates, Direction direction) {
        int x = coordinates.x() + direction.getVectorX();
        int y = coordinates.y() + direction.getVectorY();
        return new Coordinates(x,y);
    }

    public Coordinates subCoordinates(Coordinates coordinates, Direction direction) {
        int x = coordinates.x() - direction.getVectorX();
        int y = coordinates.y() - direction.getVectorY();
        return new Coordinates(x,y);
    }
}
