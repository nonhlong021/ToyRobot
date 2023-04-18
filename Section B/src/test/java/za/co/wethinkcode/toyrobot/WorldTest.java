package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.maze.DesignedMaze;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldTest {
    @Test
    void PositionUpdate(){
        IWorld world = new TextWorld(new DesignedMaze());
        assertEquals(IWorld.CENTRE, world.getPosition());
        world.updatePosition(100);
        Position newPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 100);
        assertEquals(newPosition, world.getPosition());
    }

    @Test
    void reset() {
        IWorld world = new TextWorld(new EmptyMaze());
        world.updatePosition(100);
        world.updateDirection(true);
        assertEquals(IWorld.Direction.RIGHT, world.getCurrentDirection());
        world.reset();
        assertEquals(IWorld.Direction.UP, world.getCurrentDirection());
        assertEquals(IWorld.CENTRE, world.getPosition());
    }
}

