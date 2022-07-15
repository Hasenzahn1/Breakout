package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.balls.Ball;
import me.hasenzahn1.breakout.game.Paddle;
import me.hasenzahn1.breakout.game.orbs.Orb;
import me.hasenzahn1.breakout.input.keyboard.IKeyClickable;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.map.bricks.Brick;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class IngameState extends GameState implements IKeyClickable {

    public static float CURRENT_SPEED = Ball.SPEED;
    public static BreakoutRunnable SPEED_MODIFY_TASK;

    private Map map;
    private Paddle paddle;
    private ArrayList<Ball> balls = new ArrayList<>();

    private ArrayList<Orb> currentOrbs;

    @Override
    public void start() {
        CURRENT_SPEED = Ball.SPEED;
        paddle = new Paddle(Breakout.getInstance().getWidth() / 2, 630);
        balls = new ArrayList<>();
        balls.add(new Ball(321, 600));

        currentOrbs = new ArrayList<>();

        Breakout.getInstance().getKeyRegisterbable().register(this);
    }

    @Override
    public void end() {
        map = null;
        paddle.end();
        Breakout.getInstance().getKeyRegisterbable().unregister(this);

        paddle.end();
    }

    @Override
    public void tick(double deltaTime) {
        if(map != null) map.tick(deltaTime);

        for(int i = balls.size() - 1; i >= 0; i--) {
            Ball ball = balls.get(i);
            for (int c = 0; c < ball.getSpeed() + 1; c++) {
                //Collision
                if (paddle.getCollider().intersects(ball.getCollider())) {
                    //Ball collides with paddle
                    ball.onCollide(paddle);
                    paddle.onCollide(ball);
                }

                Brick collide = map.checkCollision(ball);
                if (collide != null) {
                    collide.onCollide(ball);
                    ball.onCollide(collide);
                }

                //movement
                ball.tick(deltaTime / (ball.getSpeed() + 1));
            }
        }

        for(int i = currentOrbs.size() - 1; i >= 0; i--){
            if(currentOrbs.get(i).getCollider().intersects(paddle.getCollider())){
                currentOrbs.get(i).onCollide(paddle);
            }

        }
        for(Orb orb : currentOrbs) {
            orb.tick(deltaTime);
        }
        paddle.tick(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        if(map != null) map.render(g);
        paddle.render(g);
        for(Ball ball : balls) {
            ball.render(g);
        }
        for(Orb orb : currentOrbs){
            orb.render(g);
        }
    }

    public void setMap(Map map) {
        this.map = map;
    }

    //Benedikts Lieblingsmethode
    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void addBall(Ball ball){
        ball.setSpeed(CURRENT_SPEED);
        this.balls.add(ball);
    }

    public void addOrb(Orb orb){
        this.currentOrbs.add(orb);
    }

    public void removeOrb(Orb orb){
        currentOrbs.remove(orb);
    }

    public void removeBall(Ball ball){
        balls.remove(ball);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'r'){
            Breakout.getInstance().getGameStateManager().setGameState(GameState.LEVEL_SELECT_STATE);
        }
    }

    public void setCurrentSpeed(float speed){
        CURRENT_SPEED = speed;
        for(Ball ball : balls){
            ball.setSpeed(speed);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
