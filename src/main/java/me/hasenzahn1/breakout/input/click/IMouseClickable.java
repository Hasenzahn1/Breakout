package me.hasenzahn1.breakout.input.click;

import java.awt.event.MouseEvent;

public interface IMouseClickable {

    void onClick(MouseEvent e);
    void onRelease(MouseEvent e);
    void onPressed(MouseEvent e);
}
