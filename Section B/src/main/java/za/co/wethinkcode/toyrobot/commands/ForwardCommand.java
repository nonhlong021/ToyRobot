package za.co.wethinkcode.toyrobot.commands;

import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

import java.awt.*;

public class ForwardCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());

        IWorld.UpdateResponse response = target.getWorld().updatePosition(nrSteps);

        if (response.equals(IWorld.UpdateResponse.SUCCESS)) {
            if(target.getWorldString().equals("turtle")){
                TurtleWorld.turtle.setColor(Color.DARK_GRAY);
                TurtleWorld.turtle.forward(nrSteps);
            }else {
                target.setStatus("Moved forward by " + nrSteps + " steps.");
            }
        }else if (response.equals(IWorld.UpdateResponse.FAILED_OUTSIDE_WORLD)){
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }else{
            target.setStatus("Sorry, there is an obstacle in the way.");
        }
        return true;
    }

    public ForwardCommand(String argument) {
        super("forward", argument);
    }
}

