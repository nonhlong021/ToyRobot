package za.co.wethinkcode.toyrobot.commands;

public class SprintCommand extends Command{
    public SprintCommand(String argument) {
        super("forward", argument);
    }

    @Override
    public boolean execute(Robot target) {

        ForwardCommand forward = new ForwardCommand(getArgument());

        int nrSteps = Integer.parseInt(getArgument());
        if (nrSteps == 1) {
            forward.execute(target);
        } else {
            forward.execute(target);
            System.out.println(target);
            nrSteps --;
            setArgument(String.valueOf(nrSteps));
            return this.execute(target);

        }
            return true;
        }
    }
