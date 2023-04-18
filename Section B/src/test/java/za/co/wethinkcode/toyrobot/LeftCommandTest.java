package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.world.IWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeftCommandTest {

    @Test
    void Left(){
        String[] args = {"text", "RandomMaze"};
        Robot robot = new Robot("Hal", args);
        Command command = Command.create("left");
        assertTrue(robot.handleCommand(command));
        assertEquals("Turned left.", robot.getStatus());
    }
    }

