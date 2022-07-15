package me.hasenzahn1.breakout.map.bricks;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick implements IDrawable, ICollidable {

    public static final BufferedImage[] BRICK_IMAGES = loadAllBrickImages();
    public static final BufferedImage[] BREAKING_STAGES = loadBreakingStages();

    private static BufferedImage[] loadAllBrickImages() {
        BufferedImage[] images = new BufferedImage[16];
        for(int i = 1; i < 17; i++){
            images[i - 1] = ImageLoader.loadImage("game/bricks/brick_" + i + ".png");
        }
        return images;
    }

    public static BufferedImage[] loadBreakingStages(){
        BufferedImage[] images = new BufferedImage[3];
        images[0] = ImageLoader.loadImage("game/damage_small.png");
        images[1] = ImageLoader.loadImage("game/damage_medium.png");
        images[2] = ImageLoader.loadImage("game/damage_heavy.png");
        return images;
    }

    protected int x, y, tx, ty;

    protected int health, damage;
    protected BufferedImage image;
    protected final int debug;
    protected Map map;

    //debug
    public boolean collided;
    public boolean result;


    public Brick(int nHealth,int imageData){
        health = nHealth;
        health = 0;
        damage = 0;

        image = BRICK_IMAGES[imageData];
        debug = imageData;
    }

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, null);

        if(damage != 0){
            g.drawImage(BREAKING_STAGES[damage - 1], x, y, null);
        }
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
        damage += healthAmount;
    }

    public boolean isBroken(){
        return health < 0;        
    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox(x, y, image.getWidth() + 1, image.getHeight() + 1);
    }

    @Override
    public void onCollide(ICollidable ball) {
        hit(1);
        if(isBroken()){
            map.removeBrick(this);
        }
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getTx() {
        return tx;
    }

    public void setTx(int tx) {
        this.tx = tx;
    }

    public int getTy() {
        return ty;
    }

    public void setTy(int ty) {
        this.ty = ty;
    }
}
