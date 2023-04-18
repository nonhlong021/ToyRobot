package za.co.wethinkcode.toyrobot.commands;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g 'BACK 10'\n" +
                "RIGHT - turn right\n" +
                "LEFT - turn left\n" +
                "SPRINT - sprints forward");
        return true;
    }
}
