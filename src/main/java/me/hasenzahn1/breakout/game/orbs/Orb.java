package me.hasenzahn1.breakout.game.orbs;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Orb implements IDrawable,ICollidable{

    public int x, y;
    public BufferedImage image;

    public Orb(int x, int y, BufferedImage image){
        this.x = x;
        this.y = y;
        this.image = image;
    }


    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public BoundingBox getCollider() {
        return null;
    }
}