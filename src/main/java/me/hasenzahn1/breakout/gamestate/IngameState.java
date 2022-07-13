package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.Ball;
import me.hasenzahn1.breakout.game.Paddle;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.map.bricks.Brick;

import java.awt.*;

public class IngameState extends GameState{

    private Map map;
    private Paddle paddle;
    private Ball ball;

    @Override
    public void start() {
        paddle = new Paddle(Breakout.getInstance().getWidth() / 2, 630);
        ball = new Ball(321, 600);
    }

    @Override
    public void end() {
        map = null;
        paddle.end();
    }

    @Override
    public void tick(double deltaTime) {
        if(map != null) map.tick(deltaTime);

        for(int i = 0; i < ball.getSpeed() + 1; i++) {
            //Collision
            if (paddle.getCollider().intersects(ball.getCollider())) {
                //Ball collides with paddle
                ball.onCollide(paddle);
                paddle.onCollide(ball);
            }

            Brick collide = map.checkCollision(ball);
            if(collide != null){
                collide.onCollide(ball);
                ball.onCollide(collide);
            }

            //movement
            ball.tick(deltaTime / (ball.getSpeed() + 1));
        }
        paddle.tick(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        if(map != null) map.render(g);
        paddle.render(g);
        ball.render(g);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Ball getBall() {
        return ball;
    }
}
