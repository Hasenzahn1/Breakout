package me.hasenzahn1.breakout.game.orbs;


import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.Ball;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.game.NoCollisionBall;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.math.BoundingBox;
import me.hasenzahn1.breakout.math.Vec2d;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

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