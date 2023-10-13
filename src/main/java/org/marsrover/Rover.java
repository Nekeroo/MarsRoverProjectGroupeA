package org.marsrover;
import org.marsrover.enums.Direction;

public class Rover {

    private Position position;

    public Rover(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Position turnRight() {
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

    public Position turnLeft() {
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

    public void moveForward() {
        switch (position.getDirection()) {
            case EAST -> {
                position.getCoordonnees().setX(position.getCoordonnees().getX() + 1);
                this.position.setDirection(Direction.EAST);
            }
            case WEST -> {
                position.getCoordonnees().setX(position.getCoordonnees().getX() - 1);
                this.position.setDirection(Direction.WEST);
            }
            case NORTH -> {
                position.getCoordonnees().setY(position.getCoordonnees().getY() + 1);
                this.position.setDirection(Direction.NORTH);
            }
            case SOUTH -> {
                position.getCoordonnees().setY(position.getCoordonnees().getY() - 1);
                this.position.setDirection(Direction.SOUTH);
            }
        }
    }


    public void moveBack(){
        switch (position.getDirection()) {
            case EAST -> {
                position.getCoordonnees().setX(position.getCoordonnees().getX() - 1);
                this.position.setDirection(Direction.EAST);
            }
            case WEST -> {
                position.getCoordonnees().setX(position.getCoordonnees().getX() + 1);
                this.position.setDirection(Direction.WEST);
            }
            case NORTH -> {
                position.getCoordonnees().setY(position.getCoordonnees().getY() - 1);
                this.position.setDirection(Direction.NORTH);
            }
            case SOUTH -> {
                position.getCoordonnees().setY(position.getCoordonnees().getY() + 1);
                this.position.setDirection(Direction.SOUTH);
            }
        }
    }



}
