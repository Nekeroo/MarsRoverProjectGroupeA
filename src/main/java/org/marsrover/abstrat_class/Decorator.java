package org.marsrover.abstrat_class;

public abstract class Decorator extends PlanetBase {

    protected Decorator(PlanetBase planetBase) {
        super(planetBase.getHeight(), planetBase.getWidth());
    }
}
