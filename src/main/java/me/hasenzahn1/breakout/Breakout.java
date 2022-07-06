package me.hasenzahn1.breakout;

import me.hasenzahn1.breakout.display.Display;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.GameStateManager;
import me.hasenzahn1.breakout.input.click.IMouseRegisterable;
import me.hasenzahn1.breakout.input.click.MouseListener;
import me.hasenzahn1.breakout.input.keyboard.IKeyRegisterable;
import me.hasenzahn1.breakout.input.keyboard.KeyboardListener;
import me.hasenzahn1.breakout.input.motion.IMouseMotionRegisterable;
import me.hasenzahn1.breakout.input.motion.MouseMoveListener;
import me.hasenzahn1.breakout.settings.Settings;
import me.hasenzahn1.breakout.timings.BreakoutRunnable;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Breakout implements Runnable{

    private static final int FPS = 100000;
    private static Breakout instance;

    //Display Code
    private Display display;
    private final int width, height;
    private final String title;

    //Thread and JFrame Stuff
    private boolean running = false;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private double deltaTime;
    private long start;

    //GameStuff
    private GameStateManager gameStateManager;
    private MouseListener mouseListener;
    private MouseMoveListener mouseMoveListener;
    private KeyboardListener keyboardListener;
    private Settings settings;
    private ArrayList<BreakoutRunnable> runnables;


    public Breakout(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        instance = this;
    }

    private void init(){
        display = new Display(title, width, height);
        start = System.currentTimeMillis();

        mouseListener = new MouseListener();
        mouseMoveListener = new MouseMoveListener();
        keyboardListener = new KeyboardListener();
        display.getFrame().addMouseListener(mouseListener);
        display.getCanvas().addMouseListener(mouseListener);
        display.getFrame().addMouseMotionListener(mouseMoveListener);
        display.getCanvas().addMouseMotionListener(mouseMoveListener);
        display.getFrame().addKeyListener(keyboardListener);
        display.getCanvas().addKeyListener(keyboardListener);

        runnables = new ArrayList<>();

        settings = new Settings();

        gameStateManager = new GameStateManager(this);
        gameStateManager.setGameState(GameState.INGAME_STATE);
    }

    private void tick(){
        deltaTime = (System.currentTimeMillis() - start) / 10.0;
        start = System.currentTimeMillis();

        for (int i = runnables.size() - 1; i >= 0; i--){
            if(runnables.get(i).tick()){
                runnables.remove(i);
            }
        }

        if(gameStateManager.getCurrentGameState() != null) gameStateManager.getCurrentGameState().tick(deltaTime);
   }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        //Drawing Code
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        if(gameStateManager.getCurrentGameState() != null) gameStateManager.getCurrentGameState().render(g);

        //End Drawing
        bs.show();
        g.dispose();
    }

    @Override
    public void run(){
        init();

        double timePerTick = 1000000000.0 / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long prevTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                deltaTime = (now - prevTime) / 10000000.0;
                prevTime = now;

                tick();
                render();

                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start(){
        if(running) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running) return;

        running = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void registerRunnable(BreakoutRunnable runnable){
        runnables.add(runnable);
    }

    public static Breakout getInstance() {
        return instance;
    }

    public IMouseRegisterable getMouseRegisterable(){
        return mouseListener;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public Graphics getGraphics() {
        return g;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Display getDisplay() {
        return display;
    }

    public Settings getSettings() {
        return settings;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public void unregisterRunnable(BreakoutRunnable breakoutRunnable) {
        runnables.remove(breakoutRunnable);
    }

    public IMouseMotionRegisterable getMouseMotionRegisterable(){
        return mouseMoveListener;
    }

    public IKeyRegisterable getKeyRegisterbable(){
        return keyboardListener;
    }
}
