package me.hasenzahn1.breakout.game.orbs;


import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.balls.Ball;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TimesThreeOrb extends Orb{

    private static final BufferedImage IMAGE = ImageLoader.loadImage("game/orbs/orb_times3.png");

    public TimesThreeOrb(int x, int y) {
        super(x, y, IMAGE);
    }

    @Override
    public void onCollide(ICollidable object) {
        super.onCollide(object);
        IngameState ingameState = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);

        for(Ball ball : new ArrayList<>(ingameState.getBalls())) {
            if(ingameState.getBalls().size() >= 1000) return;
            for(int i = 0; i < 2; i++) {
                Ball ball1 = new Ball(ball.getCollider().getMiddleX(), ball.getCollider().getY(), ingameState);
                ingameState.addBall(ball1);
            }
        }
    }



}