package me.hasenzahn1.breakout.map;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.game.balls.Ball;
import me.hasenzahn1.breakout.map.bricks.Brick;
import me.hasenzahn1.breakout.map.bricks.UnbreakableBrick;
import me.hasenzahn1.breakout.math.CombinedValues;
import me.hasenzahn1.breakout.math.Vec2d;

import java.awt.*;

public class Map implements IDrawable {
    public static final int MAP_X = 32;
    public static final int MAP_Y = 32;

    private final Brick[][] bricks;
    private final String name;
    private int count;

    public Map(String name){
        bricks = new Brick[18][32];
        this.name = name;
        count = 0;
    }

    public void setBrick(int x, int y, Brick brick){
        if(brick == null) return;
        brick.setX(MAP_X + x * Brick.BRICK_IMAGES[0].getWidth() + x);
        brick.setTx(x);
        brick.setTy(y);
        brick.setY(MAP_Y + y * Brick.BRICK_IMAGES[0].getHeight() + y);
        brick.setMap(this);
        bricks[x][y] = brick;
        if(!(brick instanceof UnbreakableBrick)) count++;
    }

    public void removeBrick(Brick brick){
        bricks[brick.getTx()][brick.getTy()] = null;
        count --;
    }

    public Brick checkCollision(Ball ball){
        for(int x = 0; x < bricks.length; x++){
            for(int y = 0; y < bricks[0].length; y++){
                if(bricks[x][y] == null) continue;
                Brick collide = bricks[x][y];
                if(collide.getCollider().intersects(ball.getCollider())){
                    //At Least intercept
                    CombinedValues<Integer, Vec2d> result = collide.getCollider().getCollideIndex(ball.getCollider().getMiddleX(), ball.getCollider().getMiddleY(), ball.getDirection());
                    if(result == null) continue;
                    switch (result.getT()) {
                        case 0 -> {
                            if (y - 1 < 0) return collide;
                            if (bricks[x][y - 1] == null) return collide;
                        }
                        case 1 -> {
                            if (x - 1 < 0) return collide;
                            if (bricks[x - 1][y] == null) return collide;
                        }
                        case 2 -> {
                            if (x + 1 >= bricks.length) return collide;
                            if (bricks[x + 1][y] == null) return collide;
                        }
                        case 3 -> {
                            if (y + 1 >= bricks[0].length) return collide;
                            if (bricks[x][y + 1] == null) return collide;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void tick(double deltaTime) {
        for(int x = 0; x < bricks.length; x++){
            for(int y = 0; y < bricks[x].length; y++){
                if(bricks[x][y] == null) continue;
                bricks[x][y].tick(deltaTime);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        for(int x = 0; x < bricks.length; x++){
            for(int y = 0; y < bricks[x].length; y++){
                if(bricks[x][y] == null) continue;
                bricks[x][y].render(g);
            }
        }
    }

    public Brick[][] getBricks() {
        return bricks;
    }

    public String getName() {
        return name;
    }

    public boolean finished(){
        return count == 0;
    }
}
