package me.hasenzahn1.breakout.game;

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

    private BufferedImage image;

    public Ball(float newX, float newY){
        x = newX;
        y = newY;
        direction = new Vec2d(Math.random()-0.5,Math.random()-0.5);
        direction.normalize();
        speed = 1;
        image = ImageLoader.loadImage("game/ball.png");
    }

    @Override
    public void tick(double deltaTime) {
        x += direction.getN1() * deltaTime * speed;
        y += direction.getN2() * deltaTime * speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox((int) x, (int) y, image.getWidth(), image.getHeight());
    }

    @Override
    public void onCollide(Ball ball) {

    }
}
