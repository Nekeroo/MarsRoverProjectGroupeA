package org.marsrover.abstrat_class;

import org.marsrover.abstrat_class.PlanetBase;

public abstract class Decorator extends PlanetBase {

    private final PlanetBase planetBase;

    protected Decorator(PlanetBase planetBase) {
        super(planetBase.getHeight(), planetBase.getWidth());
        this.planetBase = planetBase;
    }

    public PlanetBase getPlanetBase() {
        return planetBase;
    }



}
