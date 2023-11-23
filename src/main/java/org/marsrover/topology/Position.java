package org.marsrover.topology;


/**
 * Classe Position prenant en paramètre un objet Coordinates et un objet Direction. Utilisé pour la création du Rover
 * @param coordinates
 * @param direction
 */
public record Position(Coordinates coordinates, Direction direction) {}