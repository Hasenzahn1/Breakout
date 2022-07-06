package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Paddle implements ICollidable, IDrawable {


    private float x, y;
    private int width;
    BufferedImage image;

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox((int) x, (int) y, width, image.getHeight());
    }

    @Override
    public void onCollide(Ball ball) {

    }

    public void moveTo(float newX){
        x = newX;
    }

    public void move(float sub){
        x += sub;
    }

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, image.getHeight(),null);
    }
}



