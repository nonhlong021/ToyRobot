package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;

public class DesignedMaze extends AbstractMaze{
    static List<Obstacle> obstacleList = new ArrayList<>();

    static String[] mazyMaze = {
            "XEXXXXXXXXXXXXXXXXXXX",
            "X   X               X",
            "X X X XXXXXXXXXXXXX X",
            "X X X X           X X",
            "X X XXX XXXXX XX  X X",
            "X X         X  X  X E",
            "X XXXXXXXXX X  X    X",
            "X X       X X  XXXX X",
            "X X X XXX X X       X",
            "X X X X   X XXXXXXX X",
            "X X X X           X X",
            "X X X XXXXX XXXXX X X",
            "X X X     X X   X X X",
            "X X XXXXX X X   X X X",
            "X X     X X X     X X",
            "XXX XXX X X XXXX  X X",
            "X X   X X X    X  X X",
            "X     X X XXXXXXX X X",
            "XXX X X         X X X",
            "X X X X     XXXXX X X",
            "X X X X           X X",
            "X                   X",
            "X X X X XXXXXXXXX X X",
            "X X X X X       X X X",
            "X X X X X XXXXXXX X X",
            "X X X X X         X X",
            "X X X X X XXXXXXXXX X",
            "X   X X X X         X",
            "XXXXXXX X XXXXXXXXX X",
            "X       X           X",
            "X X XXXXX XXXXXXXXX X",
            "X X X     X       X X",
            "X X X X X X XXXXX X X",
            "X X X X X X     X X X",
            "X X X X X XXXXX X X X",
            "X X X X X       X X X",
            "X X XXXXXXXXXXXXX X X",
            "E X               X X",
            "X XXXXXXXXXXXXXXXXX X",
            "X                   X",
            "XXXXXXXXXXXXXXXXXEXXX",
    };

    @Override
    public List<Obstacle> getObstacles() {
        return obstacleList;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }

    public void genMaze(){
        for (int x = 0; x < mazyMaze.length; x++) {
            for (int y = 0; y < mazyMaze[x].length(); y++) {
            char c = mazyMaze[x].charAt(y);
            int x1 = -100 + (y * 10);
            int y1 = 200 - (x * 10);
            if (c == 'X'){
                obstacleList.add(new SquareObstacle(x1,y1));
            }
        }
    }

}
}
