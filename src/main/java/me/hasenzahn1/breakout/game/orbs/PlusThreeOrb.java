package me.hasenzahn1.breakout.game.orbs;


import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.balls.Ball;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.math.Vec2d;

public class PlusThreeOrb extends Orb{

    public PlusThreeOrb(int x, int y) {
        super(x, y, ImageLoader.loadImage("game/orbs/orb_plus3.png"));
    }

    @Override
    public void onCollide(ICollidable object) {
        super.onCollide(object);
        IngameState ingameState = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);

        Ball ball1 = new Ball(object.getCollider().getMiddleX(), object.getCollider().getY()-20, ingameState);
        ball1.setDirection(new Vec2d(1,  - 1).normalize());
        ingameState.addBall(ball1);

        Ball ball2 = new Ball(object.getCollider().getMiddleX(), object.getCollider().getY()-20, ingameState);
        ball2.setDirection(new Vec2d(-0.1, 10).normalize());
        ingameState.addBall(ball2);

        Ball ball3 = new Ball(object.getCollider().getMiddleX(), object.getCollider().getY()-20, ingameState);
        ball3.setDirection(new Vec2d(-1, - 1).normalize());
        ingameState.addBall(ball3);
    }



}