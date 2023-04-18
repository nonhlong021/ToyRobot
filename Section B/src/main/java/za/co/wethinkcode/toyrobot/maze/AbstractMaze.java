package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.List;

public abstract class AbstractMaze implements Maze{
    @Override
    public abstract List<Obstacle> getObstacles();

    @Override
    public boolean blocksPath(Position a, Position b) {

        List<Obstacle> obs = getObstacles();
        for(int i = 0; i<getObstacles().size(); i++){
                if(obs.get(i).blocksPath(a, b))
                    return true;
              
        }
        return false;
    }

    @Override
    public abstract void genMaze();

}
