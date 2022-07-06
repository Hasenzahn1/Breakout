package me.hasenzahn1.breakout.map.orbs;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.image.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Orb implements IDrawable{

    public abstract void onHit(/*ballIrgendEinNamenAberEgal (war Omid don't judge me)*/);
    
    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
        
    }
}