package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.gamestate.IngameState;

public class ScoreManager {

    private IngameState ingameState;
    private int score;
    private int add;

    public ScoreManager(IngameState ingameState) {
        this.ingameState = ingameState;
        score = 0;
        add = 1;
    }

    public void hit(){
        score += add;
        ingameState.addToScore(add);
        add += 2;
    }

    public void resetMult(){
        add = 1;
    }




}
