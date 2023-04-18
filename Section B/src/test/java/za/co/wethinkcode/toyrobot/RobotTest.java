package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.world.IWorld;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void initialPosition() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        assertEquals(IWorld.CENTRE, robot.getPosition());
        assertEquals(IWorld.Direction.UP, robot.getCurrentDirection());
    }

    @Test
    void dump() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        assertEquals("[0,0] CrashTestDummy> Ready", robot.toString());
    }

    @Test
    void shutdown() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        ShutdownCommand command = new ShutdownCommand();
        assertFalse(robot.handleCommand(command));
    }

    @Test
    void forward() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        ForwardCommand command = new ForwardCommand("10");
        assertTrue(robot.handleCommand(command));
        Position expectedPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    @Test
    void forwardforward() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        assertTrue(robot.handleCommand(new ForwardCommand("10")));
        assertTrue(robot.handleCommand(new ForwardCommand("5")));
        assertEquals("Moved forward by 5 steps.", robot.getStatus());
    }

    @Test
    void tooFarForward() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        assertTrue(robot.handleCommand(new ForwardCommand("1000")));
        assertEquals(IWorld.CENTRE, robot.getPosition());
        assertEquals("Sorry, I cannot go outside my safe zone.", robot.getStatus());
    }

    @Test
    void help() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        Command command = new HelpCommand();
        assertTrue(robot.handleCommand(command));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g 'BACK 10'\n" +
                "RIGHT - turn right\n" +
                "LEFT - turn left\n" +
                "SPRINT - sprints forward", robot.getStatus());
    }
}