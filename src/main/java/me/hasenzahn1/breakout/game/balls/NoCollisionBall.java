package me.hasenzahn1.breakout.game.balls;

import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.game.Paddle;
import me.hasenzahn1.breakout.map.bricks.UnbreakableBrick;


public class NoCollisionBall extends Ball {


    public NoCollisionBall(float newX, float newY) {
        super(newX, newY);
        speed *= 2;
    }

    @Override
    public void onCollide(ICollidable object) {
        if(object instanceof Paddle || object instanceof UnbreakableBrick) super.onCollide(object);
    }

    @Override
    public void setSpeed(double speed) {}
}