package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SafeWall implements IDrawable, ICollidable {

    private boolean show;
    private BufferedImage image;

    public SafeWall() {
        image = ImageLoader.loadImage("game/paddle.png");
        show = false;
    }

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
        if(show) g.drawImage(image, -40, Breakout.getInstance().getHeight() - 7, Breakout.getInstance().getWidth() + 80, 10, null);
    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox(-40, Breakout.getInstance().getHeight() - 7, Breakout.getInstance().getWidth() + 80, 80);
    }

    @Override
    public void onCollide(ICollidable ball) {}

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
