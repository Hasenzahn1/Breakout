package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.input.click.IMouseClickable;
import me.hasenzahn1.breakout.math.BoundingBox;
import me.hasenzahn1.breakout.math.CombinedValues;
import me.hasenzahn1.breakout.math.Vec2d;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class NoCollisionBall extends Ball {

    public NoCollisionBall() {
        super();
        speed *= 2;

    }

    @Override 
    public void onCollide(ICollidable object) {
    }


}