package me.hasenzahn1.breakout.game.orbs;

import me.hasenzahn1.breakout.display.IDrawable;

import java.awt.*;

public abstract class Orb implements IDrawable{

    public abstract void onHit(/*ballIrgendEinNamenAberEgal (war Omid don't judge me)*/);
    
    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
    }
}