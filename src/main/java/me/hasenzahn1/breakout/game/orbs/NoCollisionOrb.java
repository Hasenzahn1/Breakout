package me.hasenzahn1.breakout.game.orbs;


import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.balls.Ball;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.game.balls.NoCollisionBall;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;

import java.awt.image.BufferedImage;

public class NoCollisionOrb extends Orb{

    private static final BufferedImage IMAGE = ImageLoader.loadImage("game/orbs/orb_noCollision.png");

    public NoCollisionOrb(int x, int y) {
        super(x, y, IMAGE);
    }

    @Override
    public void onCollide(ICollidable object) {
        super.onCollide(object);
        IngameState ingameState = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);

        Ball ball = new NoCollisionBall(object.getCollider().getMiddleX(), object.getCollider().getY() - 20);
        ball.setSpeed(ball.getSpeed() * 2);
        ingameState.addBall(ball);
    }
}