package Game;

import Actions.MoveAction;
import Actions.SpawnAction;
import Player.Player;
import Actions.Action;
import Units.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HiveGame implements Hive {
    private final HashMap<Colour, Player> playerAIs = new HashMap<>();
    private final GameBoard board = new GameBoard();
    private final PlayLog playLog = new PlayLog();
    private Colour turn = Colour.WHITE;

    public HiveGame(Player playerWhite, Player playerBlack) {
        playerAIs.put(Colour.WHITE, playerWhite);
        playerAIs.put(Colour.BLACK, playerBlack);
    }

    public HiveGame() {}

    public void setPlayerAI(Colour colour, Player player) {
        playerAIs.put(colour, player);
    }

    public void applyAction(Action action) {
        if (action instanceof SpawnAction) {
            SpawnAction spawnAction = ((SpawnAction) action);
            play(spawnAction.getUnit(), spawnAction.getSpawnCoord().q, spawnAction.getSpawnCoord().r);
            if(spawnAction.getUnit() instanceof QueenBee)
                playerAIs.get(spawnAction.getUnit().getColour()).queenbee--;
            if(spawnAction.getUnit() instanceof Beetle)
                playerAIs.get(spawnAction.getUnit().getColour()).beetle--;
            if(spawnAction.getUnit() instanceof SoldierAnt)
                playerAIs.get(spawnAction.getUnit().getColour()).ant--;
            if(spawnAction.getUnit() instanceof Spider)
                playerAIs.get(spawnAction.getUnit().getColour()).spider--;
            if(spawnAction.getUnit() instanceof GrassHopper)
                playerAIs.get(spawnAction.getUnit().getColour()).grasshopper--;
        }
        if (action instanceof MoveAction) {

        }
    }

    @Override
    public void play(GameUnit unit, int q, int r) {
        Field field = this.board.get(q, r);
        field.acceptUnit(unit);
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) {

    }

    @Override
    public void pass() {

    }

    @Override
    public boolean isWinner(Colour colour) {
        Colour enemy = (Colour.BLACK == colour) ? Colour.WHITE : Colour.BLACK;

        for (Field fieldWithUnit : this.board.getFieldsWithUnits()) {
            ArrayList<GameUnit> units = new ArrayList<>(fieldWithUnit.getUnits());
            for(GameUnit u : units) {
                if(u instanceof QueenBee && u.getColour() == enemy){
                    int surrounding = this.getCountOfSurroundingUnits(fieldWithUnit.getQ(), fieldWithUnit.getR());
                    return surrounding == 6;
                }
            }
        }
        return false;
    }
    @Override
    public boolean isDraw() {
        return isWinner(Colour.BLACK) && isWinner(Colour.WHITE);
    }

    public int getCountOfSurroundingUnits(int q, int r) {
        Field f = this.board.get(q, r);
        int count = 0;
        for(Field n : this.board.getNeighboursForField(f).values()) {
            if(n.getUnits().size() > 0) {
                count++;
            }
        }
        return count;
    }



    public Colour getTurn() {
        return turn;
    }

    public Player getPlayerAI(Colour colour) {
        return playerAIs.get(colour);
    }

    public void playTurn() {
        Player player;
        if (turn == Colour.WHITE) {
            turn = Colour.BLACK; // turn changes prematurely
            player = playerAIs.get(Colour.WHITE);
        } else {
            turn = Colour.WHITE; // turn changes prematurely
            player = playerAIs.get(Colour.BLACK);
        }
        Action action = player.chooseAction();
        playLog.writeLog(action);
        applyAction(action);
    }

    public GameBoard getBoard() {
        return board;
    }

    public PlayLog getPlayLog() {
        return playLog;
    }
}
