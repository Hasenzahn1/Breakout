package me.hasenzahn1.breakout.map;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.map.bricks.Brick;

import java.awt.*;
import java.util.ArrayList;

public class Map implements IDrawable {
    public static final int MAP_X = 32;
    public static final int MAP_Y = 32;

    private final ArrayList<Brick> bricks;

    public Map(){
        bricks = new ArrayList<>();
    }

    public void setBrick(int x, int y, Brick brick){
        if(brick == null) return;
        brick.setX(MAP_X + x * Brick.BRICK_IMAGES[0].getWidth() + x);
        brick.setY(MAP_Y + y * Brick.BRICK_IMAGES[0].getHeight() + y);
        bricks.add(brick);
    }


    @Override
    public void tick(double deltaTime) {
        for(Brick brick : bricks){
            brick.tick(deltaTime);
        }
    }

    @Override
    public void render(Graphics g) {
        for(Brick brick : bricks){
            brick.render(g);
        }
    }
}
