package nl.hanze.hive.Units;

import nl.hanze.hive.Actions.MoveAction;
import nl.hanze.hive.Game.*;
import nl.hanze.hive.Hive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueenBee extends GameUnit {
    private final static Hive.Tile tile = Hive.Tile.QUEEN_BEE;
    private Hive.Player colour;

    public QueenBee(Hive.Player colour) {
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
        Collection<Field> neighbours = gb.getNeighboursForField(fromCoord).values();
        ArrayList<MoveAction> validMoves = new ArrayList<>(6);
        Coord toCoord;
        for (Field N : neighbours) {
            if (N.getUnits().size() > 0) continue;
            // no unit in neighbouring field
            Collection<Field> nOfN = gb.getNeighboursForField(N).values();
            // also test neighbours of `N`, if there are no units we cannot move there
            long count = nOfN.stream().filter(f -> f.getUnits().size() != 0).count();
            if (count == 0) continue;
            toCoord = new Coord(N);
            // we only need to test with canMoveFromAToB for empty fields
            // otherwise we can climb on top of units
            if (canMoveFromAToB(fromCoord, toCoord)) {
                validMoves.add(new MoveAction(this, fromCoord, toCoord));
            }
        }
        return validMoves;
    }

    @Override
    public char getCharacter() { return 'Q'; }

    @Override
    public String toString() {
        return "QueenBee(" + colour + ")";
    }
}
