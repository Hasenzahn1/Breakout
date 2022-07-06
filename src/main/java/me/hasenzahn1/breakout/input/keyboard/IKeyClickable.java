package me.hasenzahn1.breakout.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface IKeyClickable {

    void keyTyped(KeyEvent e);
    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);

}
