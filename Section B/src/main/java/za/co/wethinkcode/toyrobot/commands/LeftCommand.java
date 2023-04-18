package za.co.wethinkcode.toyrobot.commands;

public class LeftCommand extends Command{

    public LeftCommand() {
        super("left");
    }

    @Override
    public boolean execute(Robot target) {
        target.getWorld().updateDirection(false);
        target.setStatus("Turned left.");
        return true;
    }
}
