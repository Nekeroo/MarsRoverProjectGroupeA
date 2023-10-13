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

    private void moveForward() {
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
        if (this.position.getX() > position.getPlanet().getHeight()) {
            position.setX(1);
        }
        else if (this.position.getY() > position.getPlanet().getWidth()) {
            position.setY(1);
        }
        else if(this.position.getY() == 0) {
            position.setY(position.getPlanet().getWidth());
        }
        else if(this.position.getX() == 0) {
            position.setX(position.getPlanet().getHeight());
        }
    }

    private void moveBack(){
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
//        if (this.position.getDirection() == Direction.EAST && (direction == Direction.NORTH || direction == Direction.SOUTH)) {
//            return false;
//        }
//        else if (this.position.getDirection() == Direction.
//    }

    public Position move (Direction direction) {
        if (this.position.getDirection() == Direction.EAST && direction == Direction.WEST) {
            moveBack();
        }
        else if (this.position.getDirection() == Direction.WEST && direction == Direction.EAST) {
            moveBack();
        }
        else if (this.position.getDirection() == Direction.SOUTH && direction == Direction.NORTH) {
            moveBack();
        }
        else if (this.position.getDirection() == Direction.NORTH && direction == Direction.SOUTH) {
            moveBack();
        }
        else {
            moveForward();
        }
        return position;
    }


}
