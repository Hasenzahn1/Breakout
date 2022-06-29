package me.hasenzahn1.breakout.display;

import java.awt.*;

public interface IDrawable {

    void tick(double deltaTime);
    void render(Graphics g);

}
