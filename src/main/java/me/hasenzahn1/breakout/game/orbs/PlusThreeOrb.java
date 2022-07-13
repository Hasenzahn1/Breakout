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

        //Spawn three balls

    }




}