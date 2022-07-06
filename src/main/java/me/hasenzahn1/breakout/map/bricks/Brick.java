package me.hasenzahn1.breakout.map.bricks;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.game.Ball;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick implements IDrawable, ICollidable {

    public static final BufferedImage[] BRICK_IMAGES = loadAllBrickImages();

    private static BufferedImage[] loadAllBrickImages() {
        BufferedImage[] images = new BufferedImage[16];
        for(int i = 1; i < 17; i++){
            images[i - 1] = ImageLoader.loadImage("game/bricks/brick_" + i + ".png");
        }
        return images;
    }

    protected int x, y;

    protected int health;
    protected BufferedImage image;
    protected final int debug;


    public Brick(int nHealth,int imageData){
        health = nHealth;

        image = BRICK_IMAGES[imageData];
        debug = imageData;
    }

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Brick{" +
                "x=" + x +
                ", y=" + y +
                ", health=" + health +
                ", image=" + debug +
                '}';
    }

    public void hit(int healthAmount){
        health -= healthAmount;
    }

    public boolean isBroken(){
        return health < 0;        
    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public void onCollide(ICollidable ball) {

    }
}
