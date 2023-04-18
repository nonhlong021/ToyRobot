package za.co.wethinkcode.toyrobot.commands;

import java.util.ArrayList;

public abstract class Command {
    private final String name;
    private String argument1;
    private String argument;

    private static ArrayList<String> history = new ArrayList<>();
    private String[] ValidCommands = {"forward", "Back", "left", "right","sprint", "replay"};

    public Command(String name, String arg, String arg1) {
        this(name);
        this.argument = arg1;
        this.argument1 = arg;
    }

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
        this.argument1 = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
        this.argument1 = "";
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void CheckList(String instruction){
        for (String i: ValidCommands){
            if(instruction.contains(i.toLowerCase())){
                this.history.add(instruction);
            }
        }
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return this.argument;
    }

    public String getArgument1() {
        return this.argument1;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            case "back":
                return new BackCommand(args[1]);
            case "left":
                return new LeftCommand();
            case "right":
                return new RightCommand();
            case "mazerun":
                if (args.length > 1) {
                    return new SimpleMazeRunner(args[1]);
                }else{
                    return new SimpleMazeRunner();
                }
            case "sprint":
                return new SprintCommand(args[1]);
            case "replay":
                if (args.length == 2){
                    return new ReplayCommand(args[1]);
                }else if (args.length == 3){
                    return new ReplayCommand(args[1],args[2]);
                }else{
                    return new ReplayCommand();
                }
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}

