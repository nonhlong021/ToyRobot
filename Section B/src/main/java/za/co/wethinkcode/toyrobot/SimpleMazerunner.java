package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.MazeRunner;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

import java.util.*;

public class SimpleMazeRunner extends Command implements MazeRunner {
    private int mazeRunCost = 0;
    private String direction;

    List<Position> positions = new ArrayList<>();
    List<String> visited = new ArrayList<>();
    Position current;

    public SimpleMazeRunner(String direction) {

        super("mazerun");
        this.direction = direction;
    }

    public SimpleMazeRunner() {
        super("mazerun");
        this.direction = "top";
    }

    @Override
    public boolean mazeRun(Robot target, IWorld.Direction edgeDirection) {
        int newX; int newY; int steps = 10;

        Map<String, List<Position>> solution = new HashMap<>();
        List<Position> visited = new ArrayList<>();
        List<Position> frontier = new ArrayList<>();
        Position current = new Position(0,0);

        frontier.add(current);

        while(frontier.size() != 0) {
            List<Position> listOfPositions = new ArrayList<>();
            current = frontier.remove(frontier.size()-1);
            visited.add(current);

            for (int i = 0 ; i < 4 ; i ++ ) {
                newX = current.getX();
                newY = current.getY();

                if (i == 0 ){newY += steps;}
                else if (i == 1) {newX += steps;}
                else if (i == 2) {newY -= steps;}
                else if (i == 3) {newX -= steps;}

                Position newPosition = new Position(newX, newY);
                if (newPosition.isIn(new Position(-100, 200), new Position(100, -200))) {
                    listOfPositions.add(newPosition);
                    if (!visited.contains(newPosition)) {
                        frontier.add(newPosition);
                    }
                }
            }
            if (!solution.containsKey(current.toString())) {
                solution.put(current.toString(), listOfPositions);
            }
        }
        List<Position> path = searchForThePath(target, solution);
        mazeRunner(path, target);
        return true;
    }

    @Override
    public int getMazeCost() {
        return this.mazeRunCost;
    }

    public List<Position> searchForThePath(Robot target, Map<String, List<Position>> solution) {

        positions.add(target.getPosition());
        Map<String, Position> navigator = new HashMap<>();

        while (positions.size() != 0) {
            current = positions.remove(0);
            visited.add(current.toString());

            for (Position neighbor : solution.get(current.toString())) {

                if (target.getWorld().isNewPositionAllowed(neighbor) && !visited.contains(neighbor.toString())) {
                    positions.add(neighbor);
                    navigator.put(neighbor.toString(), current);
                    visited.add(neighbor.toString());

                    if (neighbor.getY() == 200 && (direction.equals("top") || direction.equals(""))) {
                        return returnThePath(navigator, current);
                    }
                    if (neighbor.getY() == -200 && direction.equals("bottom")) {
                        return returnThePath(navigator, current);
                    }
                    if (neighbor.getX() == -100 && direction.equals("left")) {
                        return returnThePath(navigator, current);
                    }
                    if (neighbor.getX() == 100 && direction.equals("right")) {
                        return returnThePath(navigator, current);
                    }
                }
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List<Position> returnThePath(Map<String, Position> navigator, Position current) {
        List<Position> path = new ArrayList<>();
        path.add(current);
        while (navigator.containsKey(current.toString())) {
            current = navigator.get(current.toString());
            path.add(0, current);
        }
        return path;
    }

    public void mazeRunner(List<Position> path, Robot target) {
        int steps = 10;

        moveRobot(target,path ,steps);
        target.handleCommand(new ForwardCommand(""+steps));
        mazeRunCost++;

        target.setStatus("I am at the " +direction+ " edge. (Cost: "+mazeRunCost+" steps)");
    }

    public void checkAndTurnRobot(Robot target, List<Position> path, int i){

        if (path.get(i).getX() == path.get(i-1).getX() && (path.get(i).getY() > path.get(i-1).getY())) {
            while(!target.getCurrentDirection().equals(IWorld.Direction.UP)) {
                target.handleCommand(new RightCommand());
                if(!target.getWorld().getClass().equals(TurtleWorld.class)) {
                    System.out.println(target);
                }
                this.mazeRunCost++;
            }
        } else if (path.get(i).getX() == path.get(i-1).getX() && (path.get(i).getY() < path.get(i-1).getY())) {
            while(!target.getCurrentDirection().equals(IWorld.Direction.DOWN)) {
                target.handleCommand(new RightCommand());
                if(!target.getWorld().getClass().equals(TurtleWorld.class)) {
                    System.out.println(target);
                }
                this.mazeRunCost++;
            }
        } else  if (path.get(i).getY() == path.get(i-1).getY() && (path.get(i).getX() > path.get(i-1).getX())) {
            while(!target.getCurrentDirection().equals(IWorld.Direction.RIGHT)) {
                target.handleCommand(new RightCommand());
                if(!target.getWorld().getClass().equals(TurtleWorld.class)) {
                    System.out.println(target);
                }
                this.mazeRunCost++;
            }
        } else  if (path.get(i).getY() == path.get(i-1).getY() && (path.get(i).getX() < path.get(i-1).getX())) {
            while(!target.getCurrentDirection().equals(IWorld.Direction.LEFT)) {
                target.handleCommand(new RightCommand());
                if(!target.getWorld().getClass().equals(TurtleWorld.class)) {
                    System.out.println(target);
                }
                this.mazeRunCost++;
            }
        }
    }

    public void moveRobot(Robot target , List<Position> path , int steps){

        for (int i=1; i < path.size(); i++) {
            checkAndTurnRobot(target,path,i);
            target.handleCommand(new ForwardCommand(""+steps));
            if(!target.getWorld().getClass().equals(TurtleWorld.class)) {
                System.out.println(target);
            }
            mazeRunCost++;
        }
    }


    @Override
    public boolean execute(Robot target) {
        mazeRun(target, target.getCurrentDirection());
        return true;
    }

}