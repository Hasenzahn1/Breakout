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
        button = new Button(10, 10, ImageLoader.loadImage("gui/kbm_mouse.png"), ImageLoader.loadImage("gui/kbm_keyboard.png"), (button) -> {
            System.out.println(button + " clicked");
        });
        button.setResetOnRelease(false); // If the Button resets on Release (Level Select Btn)
        button.setClicked(true); // Set the state of the button (false=image1/true=image2)
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
