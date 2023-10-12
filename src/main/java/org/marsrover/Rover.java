package org.marsrover;

import org.marsrover.enums.Direction;

import java.beans.PropertyVetoException;

public class Rover {

    private Position position;

    public Rover(Position position) {
        this.position = position;
    }

    public int getCoordonneesX() {
        return position.getX();
    }

    public int getCoordonneesY() {
        return position.getY();
    }

    public Direction getCoordonneesDirection() {
        return position.getDirection();
    }

    public Position tournerDroite() {
        if (position.getDirection() == Direction.EAST) {
            position.setDirection(Direction.SOUTH);
        }
        else if (position.getDirection() == Direction.SOUTH) {
            position.setDirection(Direction.WEST);
        }
        else if (position.getDirection() == Direction.WEST) {
            position.setDirection(Direction.NORTH);
        }
        else if (position.getDirection() == Direction.NORTH) {
            position.setDirection(Direction.EAST);
        }
        return position;
    }

    public Position tournerGauche() {
        if (position.getDirection() == Direction.EAST) {
            position.setDirection(Direction.NORTH);
        }
        else if (position.getDirection() == Direction.SOUTH) {
            position.setDirection(Direction.EAST);
        }
        else if (position.getDirection() == Direction.NORTH) {
            position.setDirection(Direction.WEST);
        }
        else if (position.getDirection() == Direction.WEST) {
            position.setDirection(Direction.SOUTH);
        }
        return position;
    }

    private void avancer() {
        switch (position.getDirection()) {
            case EAST -> {
                position.setX(position.getX() + 1);
                this.position.setDirection(Direction.EAST);
            }
            case WEST -> {
                position.setX(position.getX() - 1);
                this.position.setDirection(Direction.WEST);
            }
            case NORTH -> {
                position.setY(position.getY() + 1);
                this.position.setDirection(Direction.NORTH);
            }
            case SOUTH -> {
                position.setY(position.getY() - 1);
                this.position.setDirection(Direction.SOUTH);
            }
        }
        checkBorder();
    }

    private void checkBorder() {
        if (this.getCoordonneesX() > position.getPlanete().getLongueur()) {
            position.setX(1);
        }
        else if (this.getCoordonneesY() > position.getPlanete().getLargeur()) {
            position.setY(1);
        }
        else if(this.getCoordonneesY() == 0) {
            position.setY(position.getPlanete().getLargeur());
        }
        else if(this.getCoordonneesX() == 0) {
            position.setX(position.getPlanete().getLongueur());
        }
    }

    private void reculer(){
        switch (position.getDirection()) {
            case EAST -> {
                position.setX(position.getX() - 1);
                this.position.setDirection(Direction.EAST);
            }
            case WEST -> {
                position.setX(position.getX() + 1);
                this.position.setDirection(Direction.WEST);
            }
            case NORTH -> {
                position.setY(position.getY() - 1);
                this.position.setDirection(Direction.NORTH);
            }
            case SOUTH -> {
                position.setY(position.getY() + 1);
                this.position.setDirection(Direction.SOUTH);
            }
        }
        checkBorder();
    }

//    public boolean canMove(Direction direction) {
//        if (this.getCoordonneesDirection() == Direction.EAST && (direction == Direction.NORTH || direction == Direction.SOUTH)) {
//            return false;
//        }
//        else if (this.getCoordonneesDirection() == Direction.
//    }

    public Position move (Direction direction) {
        if (this.getCoordonneesDirection() == Direction.EAST && direction == Direction.WEST) {
            reculer();
        }
        else if (this.getCoordonneesDirection() == Direction.WEST && direction == Direction.EAST) {
            reculer();
        }
        else if (this.getCoordonneesDirection() == Direction.SOUTH && direction == Direction.NORTH) {
            reculer();
        }
        else if (this.getCoordonneesDirection() == Direction.NORTH && direction == Direction.SOUTH) {
            reculer();
        }
        else {
            avancer();
        }
        return position;
    }

}
