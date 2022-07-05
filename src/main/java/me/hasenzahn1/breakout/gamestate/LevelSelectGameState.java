package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.gui.Button;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.map.MapLoader;

import java.awt.*;

public class LevelSelectGameState extends GameState{

    private Button test;


    @Override
    public void start() {
      test = new Button(0,0, ImageLoader.loadImage("gui/levelselection.png"),ImageLoader.loadImage("gui/levelselection.png"),(button)->{
          Map map = MapLoader.loadMap("test.blf");
          System.out.println(map);
      });
    }

    @Override
    public void end() {
        test.remove();
    }

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
        test.render(g);
    }
}
