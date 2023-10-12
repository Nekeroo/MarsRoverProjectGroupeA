package org.marsrover;

import org.marsrover.enums.Direction;

public class Rover {

    private Coordonnees coordonnees;

    public Rover(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Integer getCoordonneesX() {
        return coordonnees.getX();
    }

    public Integer getCoordonneesY() {
        return coordonnees.getY();
    }

    public Direction getCoordonneesDirection() {
        return coordonnees.getDirection();
    }

    public Coordonnees move(Direction direction, Planete planete) {
        switch (direction) {
            case EAST :
                coordonnees.setX(coordonnees.getX() + 1);
                this.coordonnees.setDirection(Direction.EAST);
            case WEST :
                coordonnees.setX(coordonnees.getX() - 1);
                this.coordonnees.setDirection(Direction.WEST);
            case NORTH :
                coordonnees.setY(coordonnees.getY() + 1);
                this.coordonnees.setDirection(Direction.NORTH);
            case SOUTH :
                coordonnees.setY(coordonnees.getY() - 1);
                this.coordonnees.setDirection(Direction.SOUTH);
        }
        return coordonnees;
    }

}
