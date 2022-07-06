package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.math.BoundingBox;
import me.hasenzahn1.breakout.math.Vec2d;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball implements IDrawable, ICollidable {

    private float x, y;
    Vec2d direction;

    private BufferedImage image;

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox((int) x, (int) y, image.getWidth(), image.getHeight());
    }

    @Override
    public void onCollide(Ball ball) {

    }
}
