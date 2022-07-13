package me.hasenzahn1.breakout.map.bricks;

import me.hasenzahn1.breakout.game.ICollidable;

import java.awt.*;

public class PowerupBrick extends Brick{

    public PowerupBrick(int nHealth, int imageData) {
        super(nHealth, imageData);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.RED);
        g.fillRect(x, y, getCollider().getWidth(), getCollider().getHeight());
    }

    @Override
    public void tick(double deltaTime) {
        super.tick(deltaTime);
    }

    @Override
    public void onCollide(ICollidable ball) {
        super.onCollide(ball);
    }
}
