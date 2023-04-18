package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.world.IWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackCommandTest {

    @Test
    void back() {
        String[] args = {"text", "RandomMaze"};
        Robot robot = new Robot("Hal", args);
        BackCommand command = new BackCommand("5");
        assertTrue(robot.handleCommand(command));
        Position newPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() - 5);
        assertEquals(newPosition, robot.getPosition());
        assertEquals("Moved back by 5 steps.", robot.getStatus());
    }
}
