package me.hasenzahn1.breakout.game.orbs;


import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;

import java.awt.image.BufferedImage;

public class BombOrb extends Orb{

    private static final BufferedImage IMAGE = ImageLoader.loadImage("game/orbs/orb_bomb.png");

    public BombOrb(int x, int y) {
        super(x, y, IMAGE);
    }

    @Override
    public void onCollide(ICollidable object) {
        super.onCollide(object);
        IngameState ingameState = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);

        //spawn new ball (subclass), enable new Ball to explode on impact

    }
}