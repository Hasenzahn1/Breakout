package me.hasenzahn1.breakout.game.balls;

import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.game.Paddle;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.map.bricks.UnbreakableBrick;

import java.awt.*;
import java.awt.image.BufferedImage;


public class NoCollisionBall extends Ball {

    public static BufferedImage IMAGE = ImageLoader.loadImage("game/balls/ball_nCollision.png");


    public NoCollisionBall(float newX, float newY, IngameState state) {
        super(newX, newY, state);
        speed *= 2;
        image = IMAGE;
    }

    @Override
    public void onCollide(ICollidable object) {
        if(object instanceof Paddle || object instanceof UnbreakableBrick) super.onCollide(object);
    }

    @Override
    public void setSpeed(double speed) {}
}