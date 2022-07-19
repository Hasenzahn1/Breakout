package me.hasenzahn1.breakout.game.orbs;


import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.balls.Ball;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

import java.awt.image.BufferedImage;

public class SlowOrb extends Orb{

    private static final BufferedImage IMAGE = ImageLoader.loadImage("game/orbs/orb_slow.png");

    public SlowOrb(int x, int y) {
        super(x, y, IMAGE);
    }

    @Override
    public void onCollide(ICollidable object) {
        super.onCollide(object);
        IngameState ingameState = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);

        if(IngameState.SPEED_MODIFY_TASK != null) IngameState.SPEED_MODIFY_TASK.cancel();
        if(IngameState.CURRENT_SPEED >= Ball.SPEED) ingameState.setCurrentSpeed(IngameState.CURRENT_SPEED / 1.4f);
        BreakoutRunnable timer = new BreakoutRunnable() {
            @Override
            public void run() {
                ingameState.setCurrentSpeed(Ball.SPEED);
                IngameState.SPEED_MODIFY_TASK = null;
            }
        }.runTaskLater(Breakout.getInstance(),10);
        IngameState.SPEED_MODIFY_TASK = timer;
    }




}