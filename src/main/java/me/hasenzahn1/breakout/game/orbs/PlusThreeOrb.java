package me.hasenzahn1.breakout.game.orbs;


import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.Ball;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.math.BoundingBox;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

import java.awt.image.BufferedImage;

public class PlusThreeOrb extends Orb{

    public PlusThreeOrb(int x, int y) {
        super(x, y, ImageLoader.loadImage("game/orb_doubleshot.png"));
    }

    @Override
    public void onCollide(ICollidable object) {
        IngameState ingameState = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);

        Ball ball1 = new Ball(object.getCollider().getMiddleX(), object.getCollider().getY()-10);
        ball1.setDirection(new Vec2d(-1, -1).normalize());
        ingameState.addBall(ball1);

        Ball ball2 = new Ball(object.getCollider().getMiddleX(), object.getCollider().getY()-10);
        ball2.setDirection(new Vec2d(-1, -10).normalize());
        ingameState.addBall(ball2);

        Ball ball3 = new Ball(object.getCollider().getMiddleX(), object.getCollider().getY()-10);
        ball3.setDirection(new Vec2d(6, -10).normalize());
        ingameState.addBall(ball3);
    }



}