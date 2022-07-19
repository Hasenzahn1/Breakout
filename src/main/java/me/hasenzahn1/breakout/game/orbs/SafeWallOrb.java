package me.hasenzahn1.breakout.game.orbs;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SafeWallOrb extends Orb{

    public static final BufferedImage IMAGE = ImageLoader.loadImage("game/orbs/orb_save.png");

    public SafeWallOrb(int x, int y) {
        super(x, y, IMAGE);
    }

    @Override
    public void onCollide(ICollidable ball) {
        super.onCollide(ball);
        IngameState state = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);
        if(IngameState.SAFE_WALL_RUNNABLE != null){
            IngameState.SAFE_WALL_RUNNABLE.cancel();
            IngameState.SAFE_WALL_RUNNABLE.runTaskLater(Breakout.getInstance(), 10);
            return;
        }
        state.getSafeWall().setShow(true);
        IngameState.SAFE_WALL_RUNNABLE = new BreakoutRunnable() {
            @Override
            public void run() {
                state.getSafeWall().setShow(false);
                IngameState.SAFE_WALL_RUNNABLE = null;
            }
        }.runTaskLater(Breakout.getInstance(), 10);
    }
}
