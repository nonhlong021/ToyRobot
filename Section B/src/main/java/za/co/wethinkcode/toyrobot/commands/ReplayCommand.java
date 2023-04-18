package za.co.wethinkcode.toyrobot.commands;


import java.util.Collections;

public class ReplayCommand extends Command {
    public ReplayCommand(String arg) {
        super("replay", arg);
    }

    public ReplayCommand() {
        super("replay");

    }

    public ReplayCommand(String arg, String arg1) {
        super("replay",arg,arg1);
    }

    public boolean isNumeric(String number){
        if (number == null) {
            return false;
        }
        try {
            double digit = Double.parseDouble(number);
        }catch (NumberFormatException n) {
            return false;
        }
        return true;
    }

    @Override
    public boolean execute(Robot target) {
        String argument = getArgument();
        String arg = getArgument1();

        String[] args = argument.trim().split("-");

        if(argument.equals("reversed") || arg.equals("reversed")){
            if (arg.equals("reversed") && argument.contains("-")) {
                for (int i = getHistory().size()-Integer.parseInt(args[1]);
                     i > getHistory().size() - Integer.parseInt(args[0]); i--) {
                    Command command = Command.create(getHistory().get(i-1));
                    target.handleCommand(command);
                    System.out.println(target);
                }
                target.setStatus("replayed " + (Integer.parseInt(args[0])-Integer.parseInt(args[1])) + " commands.");
            }else if (arg.equals("reversed") && isNumeric(argument)) {
                for (int i = getHistory().size() - 1; i >= getHistory().size() - Integer.parseInt(argument) ; i--) {
                    Command command = Command.create(getHistory().get(i));
                    target.handleCommand(command);
                    System.out.println(target);
                }
                target.setStatus("replayed " + argument + " commands.");
            }
            else if (argument.equals("reversed")) {
                for (int i = getHistory().size()-1; i >= 0; i--) {
                    Command command = Command.create(getHistory().get(i));
                    target.handleCommand(command);
                    System.out.println(target);
                }
                target.setStatus("replayed " + getHistory().size() + " commands.");
            }
        } else {
            if (!argument.equals("") && argument.contains("-")) {
                for (int i = getHistory().size() - Integer.parseInt(args[0]);
                     i < getHistory().size() - Integer.parseInt(args[1]); i++) {
                    Command command = Command.create(getHistory().get(i));
                    target.handleCommand(command);
                    if (i < getHistory().size() - 1) {
                        System.out.println(target);
                    }
                }
                target.setStatus("replayed " + (Integer.parseInt(args[0]) - Integer.parseInt(args[1])) + " commands.");
            }else if (!argument.equals("") && isNumeric(argument)) {
                for (int i = getHistory().size() - Integer.parseInt(argument); i < getHistory().size(); i++) {
                    Command command = Command.create(getHistory().get(i));
                    target.handleCommand(command);
                    System.out.println(target);
                }
                target.setStatus("replayed " + argument + " commands.");
            }
            else if (argument.equals("")) {
                for (int i = 0; i < getHistory().size(); i++) {
                    Command command = Command.create(getHistory().get(i));
                    target.handleCommand(command);
                    System.out.println(target);
                }
                target.setStatus("replayed " + getHistory().size() + " commands.");
            }
        }
        return true;
    }
}
