package me.hasenzahn1.breakout.input.motion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseMoveListener implements MouseMotionListener, IMouseMotionRegisterable {

    private ArrayList<IMouseMovable> observers;

    public MouseMoveListener() {
        observers = new ArrayList<>();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(int i = observers.size() - 1; i >= 0; i--){
            observers.get(i).moveTo(e.getPoint().x, e.getPoint().y);
        }
    }

    @Override
    public void register(IMouseMovable mouseMovable) {
        observers.add(mouseMovable);
    }

    @Override
    public void unregister(IMouseMovable movable) {
        observers.remove(movable);
    }
}
