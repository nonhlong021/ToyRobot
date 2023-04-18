package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.world.IWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForwardCommandTest {

    @Test
    void forward() {
        String[] args = {"text", "RandomMaze"};
        Robot robot = new Robot("Hal", args);
        ForwardCommand command = new ForwardCommand("20");
        assertTrue(robot.handleCommand(command));
        Position newPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 20);
        assertEquals(newPosition, robot.getPosition());
        assertEquals("Moved forward by 20 steps.", robot.getStatus());
    }

}

