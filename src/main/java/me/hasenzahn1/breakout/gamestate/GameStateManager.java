package me.hasenzahn1.breakout.gamestate;

import me.hasenzahn1.breakout.Breakout;

public class GameStateManager {

    private GameState[] gameStates;

    private GameState currentGameState;

    public GameStateManager(Breakout game){
        gameStates = new GameState[3];
        gameStates[0] = new MainMenuGameState(game);
        gameStates[1] = new LevelSelectGameState();
        gameStates[2] = new IngameState();
    }

    public void setGameState(int gameState){
        if(currentGameState != null) currentGameState.end();
        currentGameState = gameStates[gameState];
        currentGameState.start();
    }

    public GameState getGameState(int gameState){
        return gameStates[gameState];
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }
}