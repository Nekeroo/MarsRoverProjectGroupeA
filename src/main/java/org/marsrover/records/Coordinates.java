package org.marsrover.records;


// Objet Valeur
public record Coordinates(Integer x, Integer y)
{
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + "\n";
    }

    public Coordinates addCoordinates(Coordinates coordinates, Direction direction) {
        Integer x = coordinates.x() + direction.vector().vectorX();
        Integer y = coordinates.y() + direction.vector().vectorY();
        return new Coordinates(x,y);
    }

    public Coordinates subtractCoordinates(Coordinates coordinates, Direction direction) {
        Integer x = coordinates.x() - direction.vector().vectorX();
        Integer y = coordinates.y() - direction.vector().vectorY();
        return new Coordinates(x,y);
    }

    public Coordinates moduloCoordinates(Coordinates coordinates, Integer height, Integer width) {
        Integer x = (((coordinates.x() % width) + width) % width);
        Integer y = (((coordinates.y() % height) + height) % height);
        return new Coordinates(x,y);
    }
}
