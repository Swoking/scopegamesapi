package fr.dinnerwolph.scopegamesapi.utils;

public enum GameState {
    LOBBY("Lobby"),
    IN_GAME("In Game"),
    END("End");
    
    private String state;
    private GameState gamestate;

    private GameState(String state) {
        this.state = state;
    }

    public GameState getGameState() {
        return this.gamestate;
    }

    public void setGamestate(GameState gamestate) {
        this.gamestate = gamestate;
    }
}