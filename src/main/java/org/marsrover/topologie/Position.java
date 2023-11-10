package org.marsrover.topologie;


/**
 * Classe Position prenant en paramètre un objet Coordinates et un objet Direction. Utilisé pour la création du LocalRover
 * @param coordinates
 * @param direction
 */
public record Position(Coordinates coordinates, Direction direction) {}