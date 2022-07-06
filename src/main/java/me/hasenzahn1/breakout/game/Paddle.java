package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.image.BufferedImage;

public class Paddle implements ICollidable{


    private float x, y;
    private int width;
    BufferedImage image;

    @Override
    public BoundingBox getCollider() {
        return null;
    }

    @Override
    public void onCollide(Ball ball) {

    }
}



