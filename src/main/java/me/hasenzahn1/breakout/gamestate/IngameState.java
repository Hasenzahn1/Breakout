package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

import java.awt.*;

public class IngameState extends GameState{

    private Map map;

    @Override
    public void start() {
        final long start = System.currentTimeMillis();
        new BreakoutRunnable(){

            @Override
            public void run() {
                long stop = System.currentTimeMillis();
                System.out.println("Executed after: " + (stop - start) / 1000.0 + " seconds");
            }
        }.runTaskLater(Breakout.getInstance(), 10);
    }

    @Override
    public void end() {
        map = null;
    }

    @Override
    public void tick(double deltaTime) {
        if(map != null) map.tick(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        if(map != null) map.render(g);
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
