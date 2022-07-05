package me.hasenzahn1.breakout.map;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.map.bricks.Brick;

import java.awt.*;

public class Map implements IDrawable {

    public static final int ROW = 20;
    public static final int COLLUMS = 18;

    private final Brick[][] bricks;

    public Map(){
        bricks = new Brick[COLLUMS][ROW];
    }

    public void setBrick(int x, int y, Brick brick){
        bricks[x][y] = brick;
    }


    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {

    }
}
