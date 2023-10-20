package org.marsrover.abstract_class;

public abstract class PlanetDecorator extends Planet {

    protected PlanetDecorator(Planet planet) {
        super(planet.getHeight(), planet.getWidth());
    }

}
