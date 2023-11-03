package org.marsrover.planet;

import org.marsrover.planet.Planet;

public abstract class PlanetDecorator extends Planet
{
    protected PlanetDecorator(Planet planet)
    {
        super(planet.getHeight(), planet.getWidth());
    }
}
