package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;

public class SimpleMaze extends AbstractMaze{

    List<Obstacle> obstacleList = new ArrayList<>();
    @Override
    public List<Obstacle> getObstacles() {
        genMaze();
        return obstacleList;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }

    @Override
    public void genMaze() {
        obstacleList.add(new SquareObstacle(1,1));
    }
}
