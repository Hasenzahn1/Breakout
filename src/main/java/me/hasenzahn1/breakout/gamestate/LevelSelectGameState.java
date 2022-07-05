package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.gui.Button;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.map.MapLoader;

import java.awt.*;

public class LevelSelectGameState extends GameState{

    private Button test;
    private Map map;


    @Override
    public void start() {
        System.out.println("Start");
        test = new Button(0,0, ImageLoader.loadImage("gui/levelselection.png"),ImageLoader.loadImage("gui/levelselection_pressed.png"),(button)->{
            map = MapLoader.loadMap("test.blf");
            System.out.println(map);
        });
        test.setResetOnRelease(true);
        System.out.println(test);
    }

    @Override
    public void end() {
        test.remove();
    }

    @Override
    public void tick(double deltaTime) {
        test.tick(deltaTime);
        if(map != null) map.tick(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        test.render(g);
        if(map != null) map.render(g);
    }
}
