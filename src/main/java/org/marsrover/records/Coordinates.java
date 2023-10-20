package org.marsrover.records;


// Objet Valeur
public record Coordinates(int x, int y)
{
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + "\n";
    }

    public Coordinates addCoordinates(Coordinates coordinates, Direction direction) {
        int x = coordinates.x() + direction.xVector();
        int y = coordinates.y() + direction.yVector();
        return new Coordinates(x,y);
    }

    public Coordinates subCoordinates(Coordinates coordinates, Direction direction) {
        int x = coordinates.x() - direction.xVector();
        int y = coordinates.y() - direction.yVector();
        return new Coordinates(x,y);
    }
}
