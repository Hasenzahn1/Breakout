package me.hasenzahn1.breakout.game.orbs;


import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.game.Paddle;
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

        if(IngameState.PADDLE_MODIFY_TASK != null) IngameState.PADDLE_MODIFY_TASK.cancel();
        if(IngameState.CURRENT_WIDTH <= Paddle.WIDTH) ingameState.setCurrentWidth((int) (IngameState.CURRENT_WIDTH * 1.4f));
        BreakoutRunnable timer = new BreakoutRunnable() {
            @Override
            public void run() {
                ingameState.setCurrentWidth(Paddle.WIDTH);
                IngameState.PADDLE_MODIFY_TASK = null;
            }
        }.runTaskLater(Breakout.getInstance(),10);
        IngameState.PADDLE_MODIFY_TASK = timer;
    }




}