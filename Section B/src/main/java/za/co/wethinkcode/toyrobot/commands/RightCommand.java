package za.co.wethinkcode.toyrobot.commands;

public class RightCommand extends Command{
    public RightCommand() {
        super("right");
    }

    @Override
    public boolean execute(Robot target) {
        target.getWorld().updateDirection(true);
        target.setStatus("Turned right.");
        return true;
    }
}
