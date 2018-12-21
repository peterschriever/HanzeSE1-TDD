package nl.hanze.hive;

import nl.hanze.hive.Actions.Action;
import nl.hanze.hive.Actions.ActionFactory;
import nl.hanze.hive.Actions.MoveAction;
import nl.hanze.hive.Actions.SpawnAction;
import nl.hanze.hive.Game.HiveGameFactory;
import nl.hanze.hive.Player.Actor;
import nl.hanze.hive.Player.Human;
import nl.hanze.hive.Units.GameUnit;

import java.util.List;
import java.util.stream.Collectors;

public class HiveGame implements Hive {
    public final HiveWrapper wrapper;

    public HiveGame() {
        // HiveGame 'defo' gets called by external test suite
        wrapper = HiveGameFactory.getNew();
        wrapper.setPlayerAI(Player.BLACK, new Human(Player.BLACK));
        wrapper.setPlayerAI(Player.WHITE, new Human(Player.WHITE));
    }

    /**
     * 'play' is basically a SpawnAction in our implementation
     *
     * @param tile Tile to play
     * @param q    Q coordinate of hexagon to play to
     * @param r    R coordinate of hexagon to play to
     * @throws IllegalMove
     */
    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {
        Player turn = wrapper.getTurn();
        Actor actor = wrapper.getPlayerAI(turn);

        List<Action> validActions = ActionFactory.getSpawnActions(actor);
        validActions = validActions.stream().parallel()
            .filter(a -> {
                return a.getUnit().getTile().equals(tile)
                    && ((SpawnAction) a).getSpawnCoord().q.equals(q)
                    && ((SpawnAction) a).getSpawnCoord().r.equals(r);
            })
            .collect(Collectors.toList());
        System.out.println("filtered actions size: " + validActions.size());
        if (validActions.isEmpty()) throw new IllegalMove("You are not allowed to do that.");

        GameUnit unit = GameUnit.createUnitFromTile(tile, turn);
        wrapper.play(unit, q, r);
    }

    /**
     * 'move' is basically a MoveAction in our implementation
     *
     * @param fromQ Q coordinate of the tile to move
     * @param fromR R coordinate of the tile to move
     * @param toQ   Q coordinate of the hexagon to move to
     * @param toR   R coordinate of the hexagon to move to
     * @throws IllegalMove when move is not a valid action
     */
    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
        Player turn = wrapper.getTurn();
        Actor actor = wrapper.getPlayerAI(turn);

        List<Action> validActions = ActionFactory.getMoveActions(actor);
        validActions = validActions.stream().parallel()
            .filter(a -> {
                return ((MoveAction) a).getFrom().q.equals(fromQ)
                    && ((MoveAction) a).getFrom().r.equals(fromR)
                    && ((MoveAction) a).getTo().q.equals(toQ)
                    && ((MoveAction) a).getTo().r.equals(toR);
            })
            .collect(Collectors.toList());
        if (validActions.isEmpty()) throw new IllegalMove("You are not allowed to do that.");

        wrapper.move(fromQ, fromR, toQ, toR);
    }

    /**
     * pass the turn
     *
     * @throws IllegalMove when pass is not a valid action
     */
    @Override
    public void pass() throws IllegalMove {
        Player turn = wrapper.getTurn();
        Actor actor = wrapper.getPlayerAI(turn);

        List<Action> validActions = ActionFactory.generateValidActions(actor);
        if (!validActions.isEmpty()) throw new IllegalMove("You are not allowed to do that.");

        wrapper.pass();
    }

    @Override
    public boolean isWinner(Player colour) {
        return wrapper.isWinner(colour);
    }

    @Override
    public boolean isDraw() {
        return wrapper.isDraw();
    }
}
