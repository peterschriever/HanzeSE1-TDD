package Game;

import Actions.MoveAction;
import Actions.SpawnAction;
import Game.Hive.Player;
import Player.Actor;
import Actions.Action;
import Units.*;

import java.util.ArrayList;
import java.util.HashMap;

public class HiveWrapper {
    private final HashMap<Hive.Player, Actor> playerAIs = new HashMap<>();
    private final GameBoard board = new GameBoard();
    private final PlayLog playLog = new PlayLog();
    private Player turn = Hive.Player.WHITE;

    public HiveWrapper(Actor playerWhite, Actor playerBlack) {
        playerAIs.put(Hive.Player.WHITE, playerWhite);
        playerAIs.put(Hive.Player.BLACK, playerBlack);
    }

    public HiveWrapper() {}

    public void setPlayerAI(Player colour, Actor player) {
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
            MoveAction a = (MoveAction) action;
            move(a.getFrom().q, a.getFrom().r, a.getTo().q, a.getTo().r);
        }
    }

    public void play(GameUnit unit, int q, int r) {
        Field field = this.board.get(q, r);
        field.acceptUnit(unit);
    }

    public void move(int fromQ, int fromR, int toQ, int toR) {
        GameUnit u = board.get(fromQ, fromR).getUnits().pop();
        board.get(toQ, toR).getUnits().push(u);
    }

    public void pass() {
        System.out.println("Actor had to pass!");
    }

    public boolean isWinner(Hive.Player colour) {
        Hive.Player enemy = (Player.BLACK == colour) ? Hive.Player.WHITE : Hive.Player.BLACK;

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

    public boolean isDraw() {
        return isWinner(Hive.Player.BLACK) && isWinner(Hive.Player.WHITE);
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

    public Hive.Player getTurn() {
        return turn;
    }

    public Actor getPlayerAI(Hive.Player colour) {
        return playerAIs.get(colour);
    }

    public void playTurn() {
        Actor player;
        if (turn == Hive.Player.WHITE) {
            turn = Player.BLACK; // turn changes prematurely
            player = playerAIs.get(Hive.Player.WHITE);
        } else {
            turn = Player.WHITE; // turn changes prematurely
            player = playerAIs.get(Hive.Player.BLACK);
        }
        Action action = player.chooseAction();
        if(action == null){
            pass();
        } else {
            playLog.writeLog(action);
            applyAction(action);
        }
    }

    public GameBoard getBoard() {
        return board;
    }

    public PlayLog getPlayLog() {
        return playLog;
    }
}
