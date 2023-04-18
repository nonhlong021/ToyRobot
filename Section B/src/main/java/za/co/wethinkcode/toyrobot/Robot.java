package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.DesignedMaze;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.maze.RandomMaze;
import za.co.wethinkcode.toyrobot.maze.SimpleMaze;
import za.co.wethinkcode.toyrobot.world.*;

public class Robot {
    private Position position;
    private String status;
    private String name;

    private String worldString = "text";

    public IWorld worlds;

    public Robot(String name, String[] args) {
        this.name = name;
        this.status = "Ready";
        setWorld(args);
        this.position = AbstractWorld.CENTRE;
    }

    public String getStatus() {
        return this.status;
    }

    public IWorld getWorld(){
        return this.worlds;
    }

    public IWorld.Direction getCurrentDirection() {
        return worlds.getCurrentDirection();
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }

    @Override
    public String toString() {
       return "[" + getPosition().getX() + "," + getPosition().getY() + "] "
               + this.name + "> " + this.status;
    }

    public Position getPosition() {
        return this.worlds.getPosition();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setWorld(String[] args){

        if(args.length > 0){
            System.out.println("Loaded "+args[1]);
            if(args[0].equalsIgnoreCase("turtle")){
                this.worldString = args[0];
            }
            if (args[0].equals("text") && args[1].equalsIgnoreCase("emptymaze")){
                this.worlds  = new TextWorld(new EmptyMaze());
            }else if (args[0].equals("text") && args[1].equalsIgnoreCase("designedmaze")){
                this.worlds  = new TextWorld(new DesignedMaze());
            }else if (args[0].equals("text") && args[1].equalsIgnoreCase("simplemaze")){
                this.worlds  = new TextWorld(new SimpleMaze());
            }else if (args[0].equals("turtle") && args[1].equalsIgnoreCase("designedmaze")){
                this.worlds  = new TurtleWorld(new DesignedMaze());
            }else if (args[0].equals("turtle") && args[1].equalsIgnoreCase("emptymaze")){
                this.worlds  = new TurtleWorld(new EmptyMaze());
            }else if (args[0].equals("turtle") && args[1].equalsIgnoreCase("simplemaze")){
                this.worlds  = new TextWorld(new SimpleMaze());
            }else {
                if (args[0].equals("turtle")){
                    this.worlds  = new TurtleWorld(new RandomMaze());
                }else{
                    this.worlds  = new TextWorld(new RandomMaze());
                }
            }
        }else {
            System.out.println("Loaded RandomMaze");
            this.worlds  = new TextWorld(new RandomMaze());
        }
    }
    public String getWorldString() {
        return worldString;
    }
}
