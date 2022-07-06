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
    private int width;

    private BufferedImage image;

    public Ball(float newX, float newY){
        x = newX;
        y = newY;
        direction = new Vec2d(Math.random()-0.5,Math.random());
        direction.normalize();
        speed = 5;
        image = ImageLoader.loadImage("game/ball.png");
        width = 13;
        //System.out.println(windowHeight + "<- Height , Width -> " + windowWidth);
        //System.out.println(direction);
    }

    @Override
    public void tick(double deltaTime) {
        x += direction.getN1() * deltaTime * speed;
        y += direction.getN2() * deltaTime * speed;

        if(x < 0 || x > Breakout.getInstance().getWidth() - width){
            direction.multiply( -1, 1);
            if(x < 0) x=0;
            else x = Breakout.getInstance().getWidth() - width;
        }
        if(y < 0 || y > Breakout.getInstance().getHeight()-width){
            direction.multiply(1,-1);
            if(y < 0) y=0;
            else y = Breakout.getInstance().getHeight() - width;
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, width, null);
        //System.out.println(image + "   y:" + y + "   x:" + x);
    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox((int) x, (int) y, width, width);
    }

    @Override
    public void onCollide(ICollidable object) {
        BoundingBox objectBoundingBox = object.getCollider();
        /*Vec2d vec = new Vec2d(objectBoundingBox.getX() + objectBoundingBox.getWidth() / 2, objectBoundingBox.getY() + objectBoundingBox.getHeight() / 2);
        vec.subtract(new Vec2d(x + width / 2, y + width / 2));

         */



        direction.multiply(1, -1);
        if(y < objectBoundingBox.getY()){
            y = objectBoundingBox.getY() - image.getHeight() - 1;
        }else{
            y = objectBoundingBox.getMaxY() + 1;
        }
    }
}
