package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.world.IWorld;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void getShutdownName() {
        Command test = new ShutdownCommand();
        assertEquals("off", test.getName());
    }

    @Test
    void executeShutdown() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        Command shutdown = Command.create("shutdown");
        assertFalse(shutdown.execute(robot));
        assertEquals("Shutting down...", robot.getStatus());
    }

    @Test
    void getForwardName() {
        ForwardCommand test = new ForwardCommand("100");
        assertEquals("forward", test.getName());
        assertEquals("100", test.getArgument());
    }

    @Test
    void executeForward() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        Command forward100 = Command.create("forward 10");
        assertTrue(forward100.execute(robot));
        Position expectedPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getWorld().getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    @Test
    void getHelpName() {
        Command test = new HelpCommand();                                                               //<1>
        assertEquals("help", test.getName());
    }

    @Test
    void executeHelp() {
        String[] args = {"text", "DesignedMaze"};
        Robot robot = new Robot("CrashTestDummy", args);
        Command help = Command.create("help");
        assertTrue(help.execute(robot));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g 'BACK 10'\n" +
                "RIGHT - turn right\n" +
                "LEFT - turn left\n" +
                "SPRINT - sprints forward", robot.getStatus());
    }

    @Test
    void createCommand() {
        Command forward = Command.create("forward 10");                                                 //<1>
        assertEquals("forward", forward.getName());
        assertEquals("10", forward.getArgument());

        Command shutdown = Command.create("shutdown");                                                  //<2>
        assertEquals("off", shutdown.getName());

        Command help = Command.create("help");                                                          //<3>
        assertEquals("help", help.getName());
    }

    @Test
    void createInvalidCommand() {
        try {
            Command forward = Command.create("say hello");                                              //<4>
            fail("Should have thrown an exception");                                                    //<5>
        } catch (IllegalArgumentException e) {
            assertEquals("Unsupported command: say hello", e.getMessage());                             //<6>
        }
    }
}
