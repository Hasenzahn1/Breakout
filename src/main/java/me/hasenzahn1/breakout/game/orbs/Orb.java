package me.hasenzahn1.breakout.game.orbs;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Orb implements IDrawable,ICollidable{

    public static int ORB_SPEED = 2;
    public float x, y;
    public BufferedImage image;

    public Orb(int x, int y, BufferedImage image){
        this.x = x;
        this.y = y;
        this.image = image;
    }


    @Override
    public void tick(double deltaTime) {
        y += ORB_SPEED * deltaTime;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, (int) (image.getWidth() * 1.2), (int) (image.getHeight() * 1.1), null);
    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox((int) x, (int) y, (int) (image.getWidth() * 1.2), (int) (image.getHeight() * 1.2));
    }

    @Override
    public void onCollide(ICollidable ball) {
        ((IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE)).removeOrb(this);
    }
}