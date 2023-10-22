package org.marsrover.records;


// Objet Valeur
public record Coordinates(int x, int y)
{
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + "\n";
    }

    public Coordinates addCoordinates(Coordinates coordinates, Direction direction) {
        int x = coordinates.x() + direction.vector().vectorX();
        int y = coordinates.y() + direction.vector().vectorY();
        return new Coordinates(x,y);
    }

    public Coordinates subtractCoordinates(Coordinates coordinates, Direction direction) {
        int x = coordinates.x() - direction.vector().vectorX();
        int y = coordinates.y() - direction.vector().vectorY();
        return new Coordinates(x,y);
    }

    public Coordinates moduloCoordinates(Coordinates coordinates, int height, int width) {
        int x = (((coordinates.x() % width) + width) % width);
        int y = (((coordinates.y() % height) + height) % height);
        return new Coordinates(x,y);
    }
}
