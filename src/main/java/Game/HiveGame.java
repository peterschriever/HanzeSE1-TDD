package Game;

import AI.PlayerAI;
import Actions.Action;

import java.util.HashMap;

public class HiveGame implements Hive {
    private final HashMap<Colour, PlayerAI> playerAIs = new HashMap<>();
    private final GameBoard board = new GameBoard();
    private final PlayLog playLog = new PlayLog();
    private Colour turn = Colour.WHITE;

    public HiveGame(PlayerAI playerWhite, PlayerAI playerBlack) {
        playerAIs.put(Colour.WHITE, playerWhite);
        playerAIs.put(Colour.BLACK, playerBlack);
    }

    public HiveGame() {}

    public void setPlayerAI(Colour colour, PlayerAI player) {
        playerAIs.put(colour, player);
    }

    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {

    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {

    }

    @Override
    public void pass() throws IllegalMove {

    }

    @Override
    public boolean isWinner(Colour colour) {
        return false;
    }

    @Override
    public boolean isDraw() {
        return false;
    }

    public Colour getTurn() {
        return turn;
    }

    public PlayerAI getPlayerAI(Colour colour) {
        return playerAIs.get(colour);
    }

    public void playTurn() {
        PlayerAI playerAI;
        if (turn == Colour.WHITE) {
            turn = Colour.BLACK; // turn changes prematurely
            playerAI = playerAIs.get(Colour.WHITE);
        } else {
            turn = Colour.WHITE; // turn changes prematurely
            playerAI = playerAIs.get(Colour.BLACK);
        }
        Action action = playerAI.chooseAction();
        playLog.writeLog(action);
        board.applyAction(action);
    }

    public GameBoard getBoard() {
        return board;
    }

    public PlayLog getPlayLog() {
        return playLog;
    }
}
