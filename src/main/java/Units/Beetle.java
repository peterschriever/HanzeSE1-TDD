package Units;

import Actions.MoveAction;
import Game.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Beetle extends GameUnit {
    private Hive.Player colour;

    public Beetle(Hive.Player colour) {
        this.colour = colour;
    }

    @Override
    public Hive.Player getColour() {
        return this.colour;
    }

    @Override
    public char getCharacter() { return 'B'; }

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
            if (N.getUnits().size() == 0) {
                // no unit in neighbouring field
                Collection<Field> nOfN = gb.getNeighboursForField(N).values();
                // also test neighbours of `N`, if there are no units we cannot move there
                long count = nOfN.stream().filter(f -> f.getUnits().size() != 0).count();
                if (count == 0) continue;
            }
            toCoord = new Coord(N);
            // we only need to test with canMoveFromAToB for empty fields
            // otherwise we can climb on top of units
            if (N.getUnits().size() > 0 || canMoveFromAToB(fromCoord, toCoord)) {
                validMoves.add(new MoveAction(this, fromCoord, toCoord));
            }
        }
        return validMoves;
    }

    @Override
    public String toString() {
        return "Beetle(" + colour + ")";
    }
}
