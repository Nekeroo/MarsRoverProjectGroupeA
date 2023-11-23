package org.marsrover.planet;

import org.marsrover.topology.Coordinates;

public interface IPlanet {

    Coordinates canonise(Coordinates coordinates);

    boolean isObstaclesAt(Coordinates coordinates);


}
