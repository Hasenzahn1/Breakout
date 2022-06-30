package me.hasenzahn1.breakout.gui;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.input.IMouseClickable;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.function.Consumer;

public class Button implements IDrawable, IMouseClickable {

    protected int x, y, width, height;
    protected BufferedImage image, secondImage, currentImage;
    protected Consumer<Button> onClick;
    protected boolean resetOnRelease;
    protected boolean clicked;

    public Button(int x, int y, BufferedImage image, BufferedImage secondImage, Consumer<Button> onClick) {
        this(x, y, image.getWidth(), image.getHeight(), image, secondImage, onClick);
    }

    public Button(int x, int y, int width, int height, BufferedImage image, BufferedImage secondImage, Consumer<Button> onClick){
        this.x = x;
        this.y = y;
        this.image = image;
        this.secondImage = secondImage;
        this.currentImage = image;
        this.onClick = onClick;
        this.width = width;
        this.height = height;

        clicked = false;
        resetOnRelease = false;

        //Observer :D
        Breakout.getInstance().getMouseRegisterable().register(this);
    }

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(clicked ? secondImage : image, x, y, width, height, null);
    }

    @Override
    public void onClick(MouseEvent e) {
        Point p = e.getPoint();
        if(p.x < x || p.x > x + width) return;
        if(p.y < y || p.y > y + height) return;

        onClick.accept(this);
    }

    @Override
    public void onRelease(MouseEvent e) {
        Point p = e.getPoint();
        if(p.x < x || p.x > x + width) return;
        if(p.y < y || p.y > y + height) return;

        if(resetOnRelease) clicked = false;
    }

    @Override
    public void onPressed(MouseEvent e) {
        Point p = e.getPoint();
        if(p.x < x || p.x > x + width) return;
        if(p.y < y || p.y > y + height) return;

        clicked = !clicked;
    }

    public void remove(){
        Breakout.getInstance().getMouseRegisterable().unregister(this);
    }

    public boolean isResetOnRelease() {
        return resetOnRelease;
    }

    public void setResetOnRelease(boolean resetOnRelease) {
        this.resetOnRelease = resetOnRelease;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
