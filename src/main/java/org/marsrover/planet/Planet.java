package org.marsrover.planet;

import org.marsrover.topology.Coordinates;

/**
 * Classe Abstraite Planet regroupe toutes les types de planètes (With / Without Obstacle)
 */
public abstract class Planet
{
    private final int height;
    private final int width;

    protected Planet(int height, int width)
    {
        this.height = height;
        this.width = width;
    }

    protected int getHeight()
    {
        return height;
    }

    protected int getWidth()
    {
        return width;
    }

    /**
     * Retourne des coordonnées par rapport à la taille de la Planète
     * Exemple :
     *            SI Coordinates x = 4 et y = 2
     *            ET Planete height = 5 et y = 5
     *            ALORS Coordinates x = 0 et y = 2
     * NB : On compte de 0,1,2,3,4
     */
    public Coordinates canonise(Coordinates coordinates)
    {
        return coordinates.moduloCoordinates(coordinates, height, width);
    }

    public boolean isObstaclesAt(Coordinates coordinates)
    {
        return false;
    }
}
