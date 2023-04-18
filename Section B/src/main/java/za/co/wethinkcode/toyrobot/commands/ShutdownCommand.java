package za.co.wethinkcode.toyrobot.commands;

public class ShutdownCommand extends Command {
    public ShutdownCommand() {
        super("off");
    }

    @Override
    public boolean execute(Robot target) {
        getHistory().clear();
        target.setStatus("Shutting down...");
        return false;
    }
}
