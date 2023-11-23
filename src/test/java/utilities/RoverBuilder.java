package utilities;

import org.marsrover.console.LoggerConsole;
import org.marsrover.planet.IPlanet;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.rover.Rover;
import org.marsrover.topology.Coordinates;
import org.marsrover.topology.Direction;

public class RoverBuilder {

    private Direction direction = Direction.North;
    private IPlanet planet = new PlanetWithoutObstacles(5, 5);
    private Coordinates coordinates = new Coordinates(1,2);
    private final LoggerConsole loggerConsole = new LoggerConsole();

    public RoverBuilder looking(Direction direction) {
        this.direction = direction;
        return this;
    }

    public RoverBuilder onThisPosition(int x, int y) {
        this.coordinates = new Coordinates(x, y);
        return this;
    }

    public RoverBuilder onPlanet(IPlanet planet) {
        this.planet = planet;
        return this;
    }


    public Rover build() {
        return new Rover(coordinates, direction, planet, loggerConsole);
    }
}
