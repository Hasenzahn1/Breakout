package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.gui.Button;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.map.MapLoader;

import java.awt.*;
import java.util.ArrayList;

public class LevelSelectGameState extends GameState{

    ArrayList<Button> buttons;


    @Override
    public void start() {
        buttons = new ArrayList<>();
        for(int i = 0; i < MapLoader.getMaps().size(); i++){
            Button button = new Button((i % 5) * 46 + (i % 5) * 53 + 53, 249 + (i / 5) * 64 + (i /5) * 53, ImageLoader.loadImage("gui/mapselect/map_" + (i + 1) + ".png"), ImageLoader.loadImage("gui/mapselect/map_" + (i + 1) + "_pressed.png"), (btn) -> {
                System.out.println(buttons.indexOf(btn));
                //*grillenzirpen*
            });
            button.setResetOnRelease(true);
            buttons.add(button);
        }
    }

    @Override
    public void end() {
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).remove();
        }
    }

    @Override
    public void tick(double deltaTime) {
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).tick(deltaTime);
        }
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).render(g);
        }
    }
}
