package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class SquareObstacle implements Obstacle{
    
    private final Position position;
    private int x;
    private int y;
    int size = 5;
    public SquareObstacle(int a, int b) {
        this.position = new Position(x,y);
        this.x = a;
        this.y = b;
    }

    @Override
    public int getBottomLeftX() {
        return this.x;
    }

    @Override
    public int getBottomLeftY() {
        return this.y;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean blocksPosition(Position position) {

        return ((this.x <= position.getX() &&
                position.getX() <= this.x+getSize()-1) &&
                (this.y <= position.getY() && position.getY() <= this.y+getSize()-1));
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        for(int i= a.getX(); i<=b.getX(); i++){
            for(int j= a.getY();j<=b.getY(); j++ ){
                if(blocksPosition(new Position(i, j)))
                    return true;
            }
        }
        return false;
    }

    public Position getPosition(){
        return position;
    }
}
