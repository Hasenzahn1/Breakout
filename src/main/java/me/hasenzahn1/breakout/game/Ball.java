package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.math.BoundingBox;
import me.hasenzahn1.breakout.math.Vec2d;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball implements IDrawable, ICollidable {

    private float x, y;
    Vec2d direction;
    private double speed;

    private int windowWidth, windowHeight;

    private BufferedImage image;

    public Ball(float newX, float newY){
        x = newX;
        y = newY;
        direction = new Vec2d(Math.random()-0.5,Math.random());
        direction.normalize();
        speed = 5;
        image = ImageLoader.loadImage("game/ball.png");
        windowHeight = Breakout.getInstance().getHeight();
        windowWidth = Breakout.getInstance().getWidth();
        //System.out.println(windowHeight + "<- Height , Width -> " + windowWidth);
        //System.out.println(direction);
    }

    @Override
    public void tick(double deltaTime) {
        x += direction.getN1() * deltaTime * speed;
        y += direction.getN2() * deltaTime * speed;

        if(x < 0 || x > Breakout.getInstance().getWidth()-image.getWidth()){
            direction.multiply( -1, 1);
            if(x < 0) x=0;
            else x = Breakout.getInstance().getWidth() - image.getWidth();
        }
        if(y < 0 || y > Breakout.getInstance().getHeight()-image.getWidth()){
            direction.multiply(1,-1);
            if(y < 0) y=0;
            else y = Breakout.getInstance().getHeight() - image.getHeight();
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
        //System.out.println(image + "   y:" + y + "   x:" + x);
    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox((int) x, (int) y, image.getWidth(), image.getHeight());
    }

    @Override
    public void onCollide(ICollidable object) {
        BoundingBox objectBoundingBox = object.getCollider();
        if(y < objectBoundingBox.getY()){
            direction.multiply(1, -1);
            y = objectBoundingBox.getY() - image.getHeight();
        }else{
            direction.multiply(1, -1);
            y = objectBoundingBox.getMaxY();
        }
    }
}
