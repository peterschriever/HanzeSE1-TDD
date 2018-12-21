package nl.hanze.hive.Units;

import nl.hanze.hive.Actions.MoveAction;
import nl.hanze.hive.Game.Coord;
import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Game.GameBoard;
import nl.hanze.hive.Game.HiveGameFactory;
import nl.hanze.hive.Hive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GrassHopper extends GameUnit {
    private final static Hive.Tile tile = Hive.Tile.GRASSHOPPER;
    private Hive.Player colour;

    public GrassHopper(Hive.Player colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }

    @Override
    public Hive.Tile getTile() {
        return tile;
    }

    @Override
    public Hive.Player getColour() {
        return this.colour;
    }

    @Override
    public List<MoveAction> generateValidMoves(Coord fromCoord) {
        // beetle may move 1 space, as long as it does not break the swarm
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        // try to pickup unit
        if (!this.canFloat(gb)) return null;
        // find valid fields to move to
        return checkNeighboursForValidMoves(fromCoord, gb);
    }

    private List<MoveAction> checkNeighboursForValidMoves(Coord fromCoord, GameBoard gb) {
        LinkedList<MoveAction> validMoves = new LinkedList<>();
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {1, 0}, {1, -1}};
        int Q = fromCoord.q;
        int R = fromCoord.r;
        for (int[] D : directions) {
            MoveAction result = recursiveHopDirections(Q, R, D, gb, new ArrayList<>(16), fromCoord);
            if (result == null) continue;
            validMoves.add(result);
        }

        return validMoves;
    }

    private MoveAction recursiveHopDirections(int Q, int R, int[] D, GameBoard gb, List<Field> visited, Coord fromCoord) {
        // get the neighbour field from (Q, R), in direction D
        Field f = gb.get(Q + D[0], R + D[1]);
        // if it has units; keep a visited list
        if (f.getUnits().size() != 0) {
            visited.add(f);
            return recursiveHopDirections(f.getQ(), f.getR(), D, gb, visited, fromCoord);
        } else {
            // it did not have units;
            if (visited.size() == 0) return null; // stop checking this direction
            // clear the visited list
            visited.clear();
            // return a valid MoveAction
            return new MoveAction(this, fromCoord, new Coord(f));
        }
    }

    @Override
    public char getCharacter() {
        return 'G';
    }

    @Override
    public String toString() {
        return "GrassHopper(" + colour + ")";
    }
}
