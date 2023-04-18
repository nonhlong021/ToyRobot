package za.co.wethinkcode.toyrobot.world;

import org.turtle.StdDraw;
import org.turtle.Turtle;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.AbstractMaze;

import java.awt.*;
import java.util.List;


public class TurtleWorld extends AbstractWorld{
    private AbstractMaze maze;

    public static Turtle turtle;

    public TurtleWorld(AbstractMaze maze) {
        this.maze = maze;
        maze.genMaze();
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        StdDraw.setXscale(-200,200);
        StdDraw.setYscale(-250,250);
        turtle = new Turtle(5, 5, 90);
        turtle.setSize(0.01);
    }

    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (IWorld.Direction.UP.equals(currentDirection)) {
            newY = newY + nrSteps;
        }
        else if (IWorld.Direction.LEFT.equals(currentDirection)){
            newX = newX - nrSteps;
        }
        else if (IWorld.Direction.RIGHT.equals(currentDirection)) {
            newX = newX + nrSteps;
        }
        else if (IWorld.Direction.DOWN.equals(currentDirection)){
            newY = newY - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if(position.isIn(TOP_LEFT, BOTTOM_RIGHT)){
            if (isNewPositionAllowed(newPosition)){
                position = newPosition;
                return UpdateResponse.SUCCESS;
            }else{
                return UpdateResponse.FAILED_OUTSIDE_WORLD;
            }
        }return UpdateResponse.FAILED_OBSTRUCTED;

    }

    @Override
    public void updateDirection(boolean turnRight) {
        if (turnRight) {
            if(currentDirection.equals(Direction.UP)){
                setCurrentDirection(Direction.RIGHT);
            }else if(currentDirection.equals(Direction.RIGHT)){
                setCurrentDirection(Direction.DOWN);
            }else if(currentDirection.equals(Direction.DOWN)){
                setCurrentDirection(Direction.LEFT);
            }else if(currentDirection.equals(Direction.LEFT)){
                setCurrentDirection(Direction.UP);
            }turtle.right(90);

        } else {
            if(currentDirection.equals(Direction.UP)){
                setCurrentDirection(Direction.LEFT);
            }else if(currentDirection.equals(Direction.LEFT)){
                setCurrentDirection(Direction.DOWN);
            }else if(currentDirection.equals(Direction.DOWN)){
                setCurrentDirection(Direction.RIGHT);
            }else if(currentDirection.equals(Direction.RIGHT)){
                setCurrentDirection(Direction.UP);
            }turtle.left(90);
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    @Override
    public void setCurrentDirection(Direction direction){
        this.currentDirection = direction;
    }

    @Override
    public boolean isNewPositionAllowed(Position position) {
        for(Obstacle obstacle : getObstacles()){
            if(position.equals(obstacle.getPosition()) || obstacle.blocksPosition(position)){
                return false;
            }
        }
        if (position.isIn(new Position(-100,200), new Position(100, -200))){
            return true;
        }
        return false;
    }

    @Override
    public boolean isAtEdge() {
        if ((this.position.getX() >= -100) || (this.position.getX() <= 100)){return true;}
        if ((this.position.getY() >= -200) || (this.position.getY() <= 200)){return true;}
        return false;
    }
    @Override
    public void reset() {
        setCurrentDirection(IWorld.Direction.UP);
        position = CENTRE;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return maze.getObstacles();
    }

    @Override
    public void showObstacles() {

        StdDraw.setPenColor(Color.PINK);
        StdDraw.rectangle(0, 0, 100, 200);
        StdDraw.filledSquare(0.5,0.5,5);
        StdDraw.setPenColor(Color.GRAY);

        for(int i = 0; i < getObstacles().size(); i ++) {
            int obsSize = getObstacles().get(i).getSize();
            StdDraw.filledSquare(getObstacles().get(i).getBottomLeftX()+obsSize*0.01,
                    getObstacles().get(i).getBottomLeftY()+obsSize*0.01,obsSize);
        }

    }

}
