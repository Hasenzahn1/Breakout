package me.hasenzahn1.breakout.input;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseListener implements java.awt.event.MouseListener, IMouseRegisterable {

    private final ArrayList<IMouseClickable> clickables;

    public MouseListener(){
        clickables = new ArrayList<>();
    }

    public void register(IMouseClickable clickable){
        clickables.add(clickable);
    }

    public void unregister(IMouseClickable clickable){
        clickables.remove(clickable);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(IMouseClickable clickable : clickables){
            clickable.onClick(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(IMouseClickable clickable : clickables){
            clickable.onPressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(IMouseClickable clickable : clickables){
            clickable.onRelease(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
