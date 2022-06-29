package me.hasenzahn1.breakout.gamestate;

import java.awt.*;

public class MainMenuGameState extends GameState{

    private float x = 0;

    @Override
    public void start() {
        x = 0;
    }

    @Override
    public void end() {

    }

    @Override
    public void tick(double deltaTime) {
        x += 1 * deltaTime;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) x, 0, 10, 10);
    }
}
