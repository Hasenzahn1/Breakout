package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.Ball;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

import java.awt.*;

public class IngameState extends GameState{

    private Map map;
    private Ball ball;

    @Override
    public void start() {
        ball = new Ball(324,700);

    }

    @Override
    public void end() {
        map = null;
    }

    @Override
    public void tick(double deltaTime) {
        ball.tick(deltaTime);
        if(map != null) map.tick(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        ball.render(g);
        if(map != null) map.render(g);
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
