package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.display.IDrawable;

import java.awt.*;

public abstract class GameState implements IDrawable {

    public static final int MAIN_MENU_STATE = 0;
    public static final int LEVEL_SELECT_STATE = 1;
    public static final int INGAME_STATE = 2;

    public abstract void start();
    public abstract void end();

    public abstract void tick(double deltaTime);
    public abstract void render(Graphics g);

}
