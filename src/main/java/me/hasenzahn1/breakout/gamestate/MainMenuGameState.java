package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.gui.Button;
import me.hasenzahn1.breakout.image.ImageLoader;

import java.awt.*;

public class MainMenuGameState extends GameState{

    private Button button;

    public MainMenuGameState() {
    }

    @Override
    public void start() {
        button = new Button(10, 10, 64, 16, ImageLoader.loadImage("gui/levelselection.png"), ImageLoader.loadImage("gui/levelselection_pressed.png"), (button) -> {
            System.out.println(button + " clicked");
        });
        button.setResetOnRelease(true); // If the Button resets on Release (Level Select Btn)
        button.setClicked(false); // Set the state of the button (false=image1/true=image2)
    }

    @Override
    public void end() {
        button.remove(); //Remove in end because of listener
    }

    @Override
    public void tick(double deltaTime) {
    }

    @Override
    public void render(Graphics g) {
        button.render(g); //Always render the button
    }
}
