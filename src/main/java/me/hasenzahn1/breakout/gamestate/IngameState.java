package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.GameEndOverlay;
import me.hasenzahn1.breakout.game.Paddle;
import me.hasenzahn1.breakout.game.SafeWall;
import me.hasenzahn1.breakout.game.balls.Ball;
import me.hasenzahn1.breakout.game.orbs.Orb;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.input.click.IMouseClickable;
import me.hasenzahn1.breakout.input.keyboard.IKeyClickable;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.map.bricks.Brick;
import me.hasenzahn1.breakout.map.bricks.UnbreakableBrick;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class IngameState extends GameState implements IKeyClickable, IMouseClickable {

    public static float CURRENT_SPEED = Ball.SPEED;
    public static int CURRENT_WIDTH = Paddle.WIDTH;
    public static final int MAX_HEALTH = 3;
    public static BreakoutRunnable SPEED_MODIFY_TASK;
    public static BreakoutRunnable PADDLE_MODIFY_TASK;
    public static BreakoutRunnable SAFE_WALL_RUNNABLE;

    private BufferedImage heart, heart_empty;

    private Map map;
    private Paddle paddle;
    private SafeWall safeWall;
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Orb> currentOrbs;
    private int health;
    private boolean running;
    private int score;
    private Font scoreFont;

    private GameEndOverlay gameEndOverlay;
    private boolean finished;


    @Override
    public void start() {
        CURRENT_SPEED = Ball.SPEED;
        CURRENT_WIDTH = Paddle.WIDTH;
        paddle = new Paddle(Breakout.getInstance().getWidth() / 2, 630);
        balls = new ArrayList<>();
        balls.add(new Ball(321, 600, this));
        safeWall = new SafeWall();
        health = MAX_HEALTH;
        running = false;
        heart = ImageLoader.loadImage("game/health_unused.png");
        heart_empty = ImageLoader.loadImage("game/heart_used.png");

        currentOrbs = new ArrayList<>();
        finished = false;
        score = 0;

        scoreFont = new Font("TimesRoman", Font.PLAIN, 12);

        gameEndOverlay = new GameEndOverlay("You lost", 0);
        gameEndOverlay.hide();

        Breakout.getInstance().getKeyRegisterbable().register(this);
        Breakout.getInstance().getMouseRegisterable().register(this);
    }

    @Override
    public void end() {
        paddle.end();
        Breakout.getInstance().getKeyRegisterbable().unregister(this);
        Breakout.getInstance().getMouseRegisterable().unregister(this);

        paddle.end();
        gameEndOverlay.hide();
    }

    @Override
    public void tick(double deltaTime) {
        if(finished || !running){
            gameEndOverlay.tick(deltaTime);
            if(!running){
                return;
            }
        }
        if(!finished && balls.size() == 0){
            health--;
            running = false;
            balls.add(new Ball(321, 600, this));
            paddle.setX(Breakout.getInstance().getWidth() / 2 - paddle.getCollider().getWidth() / 2);

            if(health == 0){
                currentOrbs.clear();
                gameEndOverlay.setScore(score);
                gameEndOverlay.setTitle("You Lost!");
                gameEndOverlay.show();
            }
            return;
        }

        if(map.finished()){
            currentOrbs.clear();
            gameEndOverlay.setScore(score);
            gameEndOverlay.setTitle("You won!");
            gameEndOverlay.show();
            finished = true;
        }

        if(map == null) return;
        map.tick(deltaTime);

        for(int i = balls.size() - 1; i >= 0; i--) {
            Ball ball = balls.get(i);
            for (int c = 0; c < ball.getSpeed() + 1; c++) {
                //Collision
                if (paddle.getCollider().intersects(ball.getCollider())) {
                    //Ball collides with paddle
                    ball.onCollide(paddle);
                    ball.getScoreManager().resetMult();
                    paddle.onCollide(ball);
                }

                Brick collide = map.checkCollision(ball);
                if (collide != null) {
                    collide.onCollide(ball);
                    if(!(collide instanceof UnbreakableBrick)) ball.getScoreManager().hit();
                    ball.onCollide(collide);
                }

                if(safeWall.isShow() && safeWall.getCollider().intersects(ball.getCollider())){
                    ball.onCollide(safeWall);
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

        if(!finished) paddle.tick(deltaTime);
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

        safeWall.render(g);

        for(int i = 0; i < MAX_HEALTH; i++){
            g.drawImage(i > health - 1 ? heart_empty : heart, Breakout.getInstance().getWidth() - 10 - (i + 1) * heart_empty.getWidth() - i*2, Breakout.getInstance().getHeight() - 10 - heart_empty.getHeight(), null);
        }

        gameEndOverlay.render(g);

        g.setColor(Color.BLACK);
        g.setFont(scoreFont);
        g.drawString("Score: " + score, 5, 20);
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
        if(e.getKeyChar() != ' ') return;
        if(health == 0) return;
        if(!running) running = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setCurrentWidth(int width){
        CURRENT_WIDTH = width;
        paddle.setWidth(width);
    }

    public Map getMap() {
        return map;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public SafeWall getSafeWall() {
        return safeWall;
    }

    @Override
    public void onClick(MouseEvent e) {
        if(health == 0) return;
        if(e.getButton() == MouseEvent.BUTTON1){
            if(!running) running = true;
        }
    }

    @Override
    public void onRelease(MouseEvent e) {

    }

    @Override
    public void onPressed(MouseEvent e) {

    }

    public void addToScore(int add) {
        score += add;
    }
}
