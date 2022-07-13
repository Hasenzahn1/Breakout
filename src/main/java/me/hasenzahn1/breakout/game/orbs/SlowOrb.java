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

public class SlowOrb extends Orb{

    public SlowOrb(int x, int y) {
        super(x, y, ImageLoader.loadImage("game/orb_doubleshot.png"));
    }

    @Override
    public void onCollide(ICollidable object) {
        IngameState ingameState = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);
        for(Ball ball : ingameState.getBalls()) {
            ball.setSpeed(ball.getSpeed() / 2);
        }
        BreakoutRunnable timer = new BreakoutRunnable() {
            @Override
            public void run() {
                for(Ball ball : ingameState.getBalls()) {
                    ball.setSpeed(ball.getSpeed() * 2);
                }
            }
        }.runTaskLater(Breakout.getInstance(),10);
    }




}