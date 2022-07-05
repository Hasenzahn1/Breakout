package me.hasenzahn1.breakout.map.bricks;

import java.awt.*;

public class UnbreakableBrick extends Brick{

    public UnbreakableBrick(int nHealth, int imageData) {
        super(nHealth, imageData);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    public void tick(double deltaTime) {
        super.tick(deltaTime);
    }
}
