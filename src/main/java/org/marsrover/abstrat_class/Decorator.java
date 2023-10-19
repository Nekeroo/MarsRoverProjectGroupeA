package org.marsrover.abstrat_class;

public abstract class Decorator extends Planet {

    protected Decorator(Planet planet) {
        super(planet.getHeight(), planet.getWidth());
    }
}
