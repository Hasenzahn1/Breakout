package me.hasenzahn1.breakout.map;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.map.bricks.Brick;

import java.awt.*;

public class Map implements IDrawable {

    public static final int ROW = 20;
    public static final int COLLUMS = 18;
    public static final int MAP_X = 10;
    public static final int MAP_Y = 10;

    private final Brick[][] bricks;

    public Map(){
        bricks = new Brick[COLLUMS][ROW];
    }

    public void setBrick(int x, int y, Brick brick){
        if(brick == null) return;
        brick.setX(MAP_X + x * Brick.DEFUALT.getWidth() + x);
        brick.setY(MAP_Y + y * Brick.DEFUALT.getHeight() + y);
        bricks[x][y] = brick;
    }


    @Override
    public void tick(double deltaTime) {
        for(int x = 0; x < bricks.length; x++){
            for(int y = 0; y < bricks[x].length; y++){
                if(bricks[x][y] != null){
                    bricks[x][y].tick(deltaTime);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        for(int x = 0; x < bricks.length; x++){
            for(int y = 0; y < bricks[x].length; y++){
                if(bricks[x][y] != null){
                    bricks[x][y].render(g);
                }
            }
        }
    }
}
