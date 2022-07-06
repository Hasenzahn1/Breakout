package me.hasenzahn1.breakout.map.bricks;

import me.hasenzahn1.breakout.image.ImageLoader;

import java.awt.*;

public class UnbreakableBrick extends Brick{



    public UnbreakableBrick(int nHealth, int imageData) {
        super(nHealth, imageData);
        image = ImageLoader.loadImage("game/brick_unbreakable.png");
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    public void tick(double deltaTime) {
        super.tick(deltaTime);
    }

    @Override
    public void hit(int healthAmount){}
}
