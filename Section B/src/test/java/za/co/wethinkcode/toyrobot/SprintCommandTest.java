package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.world.IWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SprintCommandTest {
    @Test
    void sprint() {
        String[] args = {"text", "EmptyMaze"};
        Robot robot = new Robot("Hal", args);
        SprintCommand command = new SprintCommand("20");
        assertTrue(robot.handleCommand(command));
        Position newPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 200);
        assertEquals(newPosition, robot.getPosition());
        assertEquals("Sorry, I cannot go outside my safe zone.", robot.getStatus());
    }
}
