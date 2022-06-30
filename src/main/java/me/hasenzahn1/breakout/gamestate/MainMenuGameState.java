package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.gui.Button;
import me.hasenzahn1.breakout.image.ImageLoader;

import java.awt.*;

public class MainMenuGameState extends GameState{

    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;

    private Breakout game;

    public MainMenuGameState(Breakout game) {
        this.game = game;
    }

    @Override
    public void start() {
        button = new Button(10, 10, 256, 128, ImageLoader.loadImage("gui/SFX_on.png"), ImageLoader.loadImage("gui/SFX_off.png"), (button) -> {
            System.out.println(button + " clicked");
        });
        button.setResetOnRelease(false); // If the Button resets on Release (Level Select Btn)
        button.setClicked(false); // Set the state of the button (false=image1/true=image2)
        button1 = new Button(288, 600, ImageLoader.loadImage("gui/SFX_off.png"), ImageLoader.loadImage("gui/SFX_on.png"), (button) -> {
            System.out.println("toggle SFX");
        });
        button1.setClicked(true);
        button2 = new Button(100, 600, ImageLoader.loadImage("gui/kbm_keyboard.png"), ImageLoader.loadImage("gui/kbm_mouse.png"), (button) ->{
            System.out.println("toggle ding(mouse)");
        });
    }

    @Override
    public void end() {
        button.remove(); //Remove in end because of listener
        button1.remove();
    }

    @Override
    public void tick(double deltaTime) {
    }

    @Override
    public void render(Graphics g) {
        button.render(g); //Always render the button
        button1.render(g);
    }
}
