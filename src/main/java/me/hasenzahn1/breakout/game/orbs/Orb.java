package me.hasenzahn1.breakout.game.orbs;

import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.*;

public abstract class Orb implements IDrawable,ICollidable{

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