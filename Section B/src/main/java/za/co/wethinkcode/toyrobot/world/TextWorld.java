package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.*;

import java.util.List;

public class TextWorld extends AbstractWorld{

    private AbstractMaze maze;

    public TextWorld(AbstractMaze maze) {
        this.maze = maze;
        this.position = CENTRE;
        this.maze.genMaze();
        this.currentDirection = Direction.UP;
    }

    @Override
    public UpdateResponse updatePosition(int nrSteps) {

        int newX = position.getX();
        int newY = position.getY();

        if (IWorld.Direction.UP.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        else if (IWorld.Direction.LEFT.equals(this.currentDirection)){
            newX = newX - nrSteps;
        }
        else if (IWorld.Direction.RIGHT.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }
        else if (IWorld.Direction.DOWN.equals(this.currentDirection)){
            newY = newY - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if(position.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            if (isNewPositionAllowed(newPosition)) {
                position = newPosition;
                return UpdateResponse.SUCCESS;
            } else {
                return UpdateResponse.FAILED_OUTSIDE_WORLD;
            }
        }
        return UpdateResponse.FAILED_OBSTRUCTED;
    }

    @Override
    public void updateDirection(boolean turnRight) {
        if (turnRight) {
            if(getCurrentDirection().equals(Direction.UP)){
                setCurrentDirection(Direction.RIGHT);
            }else if(getCurrentDirection().equals(Direction.RIGHT)){
                setCurrentDirection(Direction.DOWN);
            }else if(getCurrentDirection().equals(Direction.DOWN)){
                setCurrentDirection(Direction.LEFT);
            }else if(getCurrentDirection().equals(Direction.LEFT)){
                setCurrentDirection(Direction.UP);
            }

        } else {
            if(getCurrentDirection().equals(Direction.UP)){
                setCurrentDirection(Direction.LEFT);
            }else if(getCurrentDirection().equals(Direction.LEFT)){
                setCurrentDirection(Direction.DOWN);
            }else if(getCurrentDirection().equals(Direction.DOWN)){
                setCurrentDirection(Direction.RIGHT);
            }else if(getCurrentDirection().equals(Direction.RIGHT)){
                setCurrentDirection(Direction.UP);
            }

        }

    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public void setCurrentDirection(Direction direction){
        currentDirection = direction;
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
        if ((this.position.getX() == -100) || (this.position.getX() == 100)){return true;}
        if ((this.position.getY() == -200) || (this.position.getY() == 200)){return true;}
        return false;
    }

    @Override
    public void reset() {
        position = CENTRE;
        currentDirection = IWorld.Direction.UP;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return maze.getObstacles();
    }

    @Override
    public void showObstacles() {
        List<Obstacle> obs = maze.getObstacles();
        System.out.println("There are some obstacles: ");
        for (int i = 0; i< obs.size(); i++ ){
            System.out.println("- At position " + obs.get(i).getBottomLeftX()+ "," + obs.get(i).getBottomLeftY()+
                    "(to " + (obs.get(i).getBottomLeftX()  + 5 ) + "," + (obs.get(i).getBottomLeftY() + 5)+")");
        }
    }
}
