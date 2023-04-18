package za.co.wethinkcode.toyrobot.maze;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.Collections;
import java.util.List;

public class EmptyMaze extends AbstractMaze{
    @Override
    public List<Obstacle> getObstacles() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }

    @Override
    public void genMaze() {

    }
}
