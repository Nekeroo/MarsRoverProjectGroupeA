package org.marsrover.topology;


/**
 * Classe Coordinates regroupent deux entiers (x et y) et possèdent des méthodes d'opérations mathématique (+, -, %)
 * @param x
 * @param y
 */
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

    public Coordinates moduloCoordinates(Coordinates coordinates, int height, int width) {
        int x = (((coordinates.x() % width) + width) % width);
        int y = (((coordinates.y() % height) + height ) % height);
        return new Coordinates(x,y);
    }
}
