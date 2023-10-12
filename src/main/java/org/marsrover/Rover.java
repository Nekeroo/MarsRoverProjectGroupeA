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

    public Coordonnees tournerDroite() {
        if (coordonnees.getDirection() == Direction.EAST) {
            coordonnees.setDirection(Direction.SOUTH);
        }
        else if (coordonnees.getDirection() == Direction.SOUTH) {
            coordonnees.setDirection(Direction.WEST);
        }
        else if (coordonnees.getDirection() == Direction.WEST) {
            coordonnees.setDirection(Direction.NORTH);
        }
        else if (coordonnees.getDirection() == Direction.NORTH) {
            coordonnees.setDirection(Direction.EAST);
        }
        return coordonnees;
    }

    public Coordonnees tournerGauche() {
        if (coordonnees.getDirection() == Direction.EAST) {
            coordonnees.setDirection(Direction.NORTH);
        }
        else if (coordonnees.getDirection() == Direction.SOUTH) {
            coordonnees.setDirection(Direction.EAST);
        }
        else if (coordonnees.getDirection() == Direction.NORTH) {
            coordonnees.setDirection(Direction.WEST);
        }
        else if (coordonnees.getDirection() == Direction.WEST) {
            coordonnees.setDirection(Direction.SOUTH);
        }
        return coordonnees;
    }

    public Coordonnees avancer(Direction direction) {
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

    public Coordonnees reculer(Direction direction){
        switch (direction) {
            case EAST :
                coordonnees.setX(coordonnees.getX() - 1);
                this.coordonnees.setDirection(Direction.EAST);
            case WEST :
                coordonnees.setX(coordonnees.getX() + 1);
                this.coordonnees.setDirection(Direction.WEST);
            case NORTH :
                coordonnees.setY(coordonnees.getY() - 1);
                this.coordonnees.setDirection(Direction.NORTH);
            case SOUTH :
                coordonnees.setY(coordonnees.getY() + 1);
                this.coordonnees.setDirection(Direction.SOUTH);
        }
        return coordonnees;
    }

}
