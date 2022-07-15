package me.hasenzahn1.breakout.game.orbs;


import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.balls.Ball;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

import java.awt.image.BufferedImage;

public class WidenOrb extends Orb{

    private static final BufferedImage IMAGE = ImageLoader.loadImage("game/orbs/orb_widen.png");

    public WidenOrb(int x, int y) {
        super(x, y, IMAGE);
    }

    @Override
    public void onCollide(ICollidable object) {
        super.onCollide(object);
        IngameState ingameState = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);

        if(IngameState.SPEED_MODIFY_TASK != null) IngameState.SPEED_MODIFY_TASK.cancel();
        if(IngameState.CURRENT_SPEED < Ball.SPEED) return;

        ingameState.getPaddle().setWidth(ingameState.getPaddle().width * 2)
        BreakoutRunnable timer = new BreakoutRunnable() {
            @Override
            public void run() {
                ingameState.getPaddle().setWidth(Paddle.WIDTH)
                IngameState.SPEED_MODIFY_TASK = null;
            }
        }.runTaskLater(Breakout.getInstance(),10);
    }




}