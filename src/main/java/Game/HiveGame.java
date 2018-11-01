package Game;

import AI.PlayerAI;
import Actions.Action;

import java.util.HashMap;

public class HiveGame implements Hive {
    private final HashMap<Player, PlayerAI> playerAIs = new HashMap<>();
    private final GameBoard board = new GameBoard();
    private final PlayLog playLog = new PlayLog();
    private Player turn = Player.WHITE;

    public HiveGame(PlayerAI playerWhite, PlayerAI playerBlack) {
        playerAIs.put(Player.WHITE, playerWhite);
        playerAIs.put(Player.BLACK, playerBlack);
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
    public boolean isWinner(Player player) {
        return false;
    }

    @Override
    public boolean isDraw() {
        return false;
    }

    public Player getTurn() {
        return turn;
    }

    public PlayerAI getPlayerAI(Player player) {
        return playerAIs.get(player);
    }

    public void playTurn() {
        PlayerAI playerAI;
        if (turn == Player.WHITE) {
            turn = Player.BLACK; // turn changes prematurely
            playerAI = playerAIs.get(Player.WHITE);
        } else {
            turn = Player.WHITE; // turn changes prematurely
            playerAI = playerAIs.get(Player.BLACK);
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
