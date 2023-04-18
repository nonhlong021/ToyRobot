package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMaze extends AbstractMaze{

    List<Obstacle> obstacleList = new ArrayList<>();
    @Override
    public List<Obstacle> getObstacles() {
        return obstacleList;
    }


    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }

    @Override
    public void genMaze() {
        Random rand = new Random();
        int n = rand.nextInt(9);

        for (int i = 0; i < n;i++) {
            int x = ThreadLocalRandom.current().nextInt(-100, 100);
            int y = ThreadLocalRandom.current().nextInt(-200, 200);
            obstacleList.add(new SquareObstacle(x,y));
        }
    }
}
