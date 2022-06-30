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
        button = new Button(10, 10, ImageLoader.loadImage("gui/levelselection.png"), ImageLoader.loadImage("gui/levelselection_pressed.png"), (button) -> {
            game.getGameStateManager().setGameState(GameState.LEVEL_SELECT_STATE);
        });
        button.setResetOnRelease(true); // If the Button resets on Release (Level Select Btn)

        button1 = new Button(288, 600, ImageLoader.loadImage("gui/SFX_off.png"), ImageLoader.loadImage("gui/SFX_on.png"), (button) -> {
            game.getSettings().toggleSfxOn();
        });
        button1.setClicked(game.getSettings().isSfxOn());

        button2 = new Button(100, 600, ImageLoader.loadImage("gui/kbm_keyboard.png"), ImageLoader.loadImage("gui/kbm_mouse.png"), (button) ->{
            game.getSettings().toggleMouseActive();
        });
        button2.setClicked(game.getSettings().isMouseActive());

        button3 = new Button(420, 600, ImageLoader.loadImage("gui/music_off.png"), ImageLoader.loadImage("gui/music_on.png"), (button) -> {
            game.getSettings().toggleMusicOn();
        });
        button3.setClicked(game.getSettings().isMusicOn());
    }

    @Override
    public void end() {
        button.remove(); //Remove in end because of listener
        button1.remove();
        button2.remove();
        button3.remove();
    }

    @Override
    public void tick(double deltaTime) {
    }

    @Override
    public void render(Graphics g) {
        button.render(g); //Always render the button
        button1.render(g);
        button2.render(g);
        button3.render(g);

    }
}
