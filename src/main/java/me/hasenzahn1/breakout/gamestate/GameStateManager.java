package me.hasenzahn1.breakout.gamestate;

public class GameStateManager {

    private GameState[] gameStates;

    private GameState currentGameState;

    public GameStateManager(){
        gameStates = new GameState[3];
        gameStates[0] = new MainMenuGameState();
        gameStates[1] = new LevelSelectGameState();
        gameStates[2] = new IngameState();
    }

    public void setGameState(int gameState){
        if(currentGameState != null) currentGameState.end();
        currentGameState = gameStates[gameState];
        currentGameState.start();
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }
}
