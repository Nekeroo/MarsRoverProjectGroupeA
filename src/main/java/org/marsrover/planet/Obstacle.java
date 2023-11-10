package org.marsrover.planet;


import org.marsrover.topologie.Coordinates;

/**
 * Obstacle est un objet prenant en paramètre des coordonnées.
 * Celui-ci est utilisé dans les PlanetWithObstacle.
 * @param coordinates
 */
public record Obstacle(Coordinates coordinates) {}