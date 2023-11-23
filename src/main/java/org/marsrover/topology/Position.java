package org.marsrover.topology;


/**
 * Classe Position prenant en paramètre un objet Coordinates et un objet Direction. Utilisé pour la création du Rover
 * @param coordinates
 * @param direction
 */
public record Position(Coordinates coordinates, Direction direction) {

    public Coordinates callAddCoordinates(Coordinates coordinates, Direction direction) {
        return coordinates.addCoordinates(coordinates, direction);
    }

    public Coordinates callSubCoordinates(Coordinates coordinates, Direction direction) {
        return coordinates.subCoordinates(coordinates, direction);
    }

    public Coordinates callModuloCoordinates(Coordinates coordinates, int height, int width) {
        return coordinates.moduloCoordinates(coordinates, height, width);
    }

}