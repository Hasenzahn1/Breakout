package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.gui.Button;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.map.MapLoader;
import me.hasenzahn1.breakout.math.BoundingBox;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Locale;

public class GameEndOverlay implements IDrawable {

    private Button levelSelectButton;
    private Button retryButton;
    private Button mainMenuButton;
    private String title;
    private BoundingBox bb;
    private int x, y, width, height;
    private final BufferedImage background;

    private boolean shown;

    private Font titleFont, scoreFont;
    private int score;
    private boolean showOtherText;

    public GameEndOverlay(String title, int score){
        this.title = title;
        this.score = score;
        background = ImageLoader.loadImage("gameEnd_overlay.png");
        shown = false;

        width = 300;
        height = 400;
        x = (Breakout.getInstance().getWidth() - width) / 2;
        y = (Breakout.getInstance().getHeight() - height) / 2;

        bb = new BoundingBox(x, y, width, height);

        titleFont = new Font("TimesRoman", Font.PLAIN, 40);
        scoreFont = new Font("TimesRoman", Font.PLAIN, 20);

        levelSelectButton = new Button(x, y, 200, 200 / 4, ImageLoader.loadImage("gui/levelselection_pressed.png"), ImageLoader.loadImage("gui/levelselection.png") ,(btn) -> {
            Breakout.getInstance().getGameStateManager().setGameState(GameState.LEVEL_SELECT_STATE);
        });
        levelSelectButton.setResetOnRelease(true);
        levelSelectButton.setX(x + (width - levelSelectButton.getWidth()) / 2);
        levelSelectButton.setY(y + height - levelSelectButton.getHeight() * 2 - 20 * 2);

        mainMenuButton = new Button(x, y, 200, 200 / 4, ImageLoader.loadImage("gui/mainMenu_pressed.png"), ImageLoader.loadImage("gui/mainMenu.png") ,(btn) -> {
            Breakout.getInstance().getGameStateManager().setGameState(GameState.MAIN_MENU_STATE);
        });
        mainMenuButton.setResetOnRelease(true);
        mainMenuButton.setX(x + (width - mainMenuButton.getWidth()) / 2);
        mainMenuButton.setY(y + height - mainMenuButton.getHeight() * 3 - 20 * 3);

        retryButton = new Button(x, y, 200, 50, ImageLoader.loadImage("gui/retry_pressed.png"), ImageLoader.loadImage("gui/retry.png"), (btn) -> {
            IngameState state = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);
            Map map = MapLoader.loadMap(state.getMap().getName());
            state.setMap(map);
            Breakout.getInstance().getGameStateManager().setGameState(GameState.INGAME_STATE);
        });
        retryButton.setResetOnRelease(true);
        retryButton.setX(x + (width - retryButton.getWidth()) / 2);
        retryButton.setY(y + height - retryButton.getHeight() - 20);
    }

    public void end(){
        levelSelectButton.remove();
        retryButton.remove();
    }

    public void show(){
        shown = true;
        levelSelectButton.register();
        mainMenuButton.register();
        retryButton.register();
        showOtherText = ((IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE)).getMap().getName().contains("map_6") && title.contains("won");
    }

    public void hide(){
        shown = false;

        end();
    }

    @Override
    public void tick(double deltaTime) {
        if(!shown) return;

        levelSelectButton.tick(deltaTime);
        retryButton.tick(deltaTime);
        mainMenuButton.tick(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        if(!shown) return;

        g.drawImage(background, x, y, width, height, null);
        //g.setColor(Color.BLACK);
        //g.fillRect(x, y, width, height);



        /*Font font = Font.getFont("Default");
        if(font == null){
            throw new RuntimeException("Unknown Font with name: Serif");
        }

         */

        g.setColor(Color.WHITE);
        g.setFont(titleFont);
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, bb.getMiddleX() - titleWidth / 2, y + 10 + g.getFontMetrics().getHeight());
        int py = y + 10 + g.getFontMetrics().getHeight() + 10;

        g.setColor(Color.WHITE);
        g.setFont(scoreFont);
        String text = "Score:";
        String text2 = score + "";
        int scoreText1Width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, bb.getMiddleX() - scoreText1Width / 2, py + g.getFontMetrics().getHeight());
        int scoreText2Width = g.getFontMetrics().stringWidth(text2);
        g.drawString(text2, bb.getMiddleX() - scoreText2Width / 2, py + g.getFontMetrics().getHeight() * 2);

        if(showOtherText){
            String otherText = "I always come back";
            int otwidth = g.getFontMetrics().stringWidth(otherText);
            g.drawString(otherText, bb.getMiddleX() - otwidth / 2, py + g.getFontMetrics().getHeight() * 3 + 10);
        }

        levelSelectButton.render(g);
        retryButton.render(g);
        mainMenuButton.render(g);

    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTitle(String s) {
        this.title = s;
    }
}
