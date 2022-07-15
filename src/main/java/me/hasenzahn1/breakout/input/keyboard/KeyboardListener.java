package me.hasenzahn1.breakout.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardListener implements KeyListener, IKeyRegisterable{

    private final ArrayList<IKeyClickable> clickables;

    public KeyboardListener() {
        clickables = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for(int i = clickables.size() - 1; i >= 0; i--){
            if(i >= clickables.size()) continue;
            clickables.get(i).keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(int i = clickables.size() - 1; i >= 0; i--){
            clickables.get(i).keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(int i = clickables.size() - 1; i >= 0; i--){
            clickables.get(i).keyReleased(e);
        }
    }

    @Override
    public void register(IKeyClickable clickable) {
        clickables.add(clickable);
    }

    @Override
    public void unregister(IKeyClickable clickable) {
        clickables.remove(clickable);
    }
}
