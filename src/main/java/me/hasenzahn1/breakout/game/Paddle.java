package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.input.keyboard.IKeyClickable;
import me.hasenzahn1.breakout.input.motion.IMouseMovable;
import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Paddle implements ICollidable, IDrawable, IMouseMovable, IKeyClickable {


    private float x;
    private final int y;
    private int width;
    BufferedImage image;
    int speed;

    private boolean leftClicked, rightClicked;

    public Paddle(float x, int y) {
        this.x = x;
        this.y = y;
        this.image = ImageLoader.loadImage("game/paddle.png");
        this.width = image.getWidth();
        speed = 7;

        Breakout.getInstance().getMouseMotionRegisterable().register(this);
        Breakout.getInstance().getKeyRegisterbable().register(this);
    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox((int) x, (int) y, width, image.getHeight());
    }

    @Override
    public void onCollide(Ball ball) {

    }

    public void end(){
        Breakout.getInstance().getMouseMotionRegisterable().unregister(this);
        Breakout.getInstance().getKeyRegisterbable().unregister(this);
    }

    @Override
    public void tick(double deltaTime) {
        if(!Breakout.getInstance().getSettings().isMouseActive()) {
            if(leftClicked) x -= speed * deltaTime;
            if(rightClicked) x += speed * deltaTime;
        }

        if(x < 0) x = 0;
        if(x + width > Breakout.getInstance().getWidth()) x = Breakout.getInstance().getWidth() - width;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, image.getHeight(),  null);
    }

    @Override
    public void moveTo(int x, int y) {
        if(Breakout.getInstance().getSettings().isMouseActive()){
            this.x = x;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
            leftClicked = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightClicked = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
            leftClicked = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightClicked = false;
        }
    }
}



