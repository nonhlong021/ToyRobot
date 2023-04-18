package za.co.wethinkcode.toyrobot.commands;

import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

public class BackCommand extends Command {
    @Override
    public boolean execute (Robot target){
        int nrSteps = Integer.parseInt(getArgument());
        IWorld.UpdateResponse response = target.getWorld().updatePosition(-nrSteps);

        if (response.equals(IWorld.UpdateResponse.SUCCESS)) {
            if(target.getWorldString().equals("turtle")){
                TurtleWorld.turtle.backward(nrSteps);
            }else{
                target.setStatus("Moved back by " + nrSteps + " steps.");
            }
        } else if (response.equals(IWorld.UpdateResponse.FAILED_OUTSIDE_WORLD)){
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }else{
            target.setStatus("Sorry, there is an obstacle in the way.");
        }
        return true;
        }
    public BackCommand(String argument) {
        super("back", argument);
        }
    }

