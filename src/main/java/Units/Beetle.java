package Units;

import Actions.MoveAction;
import Game.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Beetle extends GameUnit {
    private Hive.Colour colour;

    public Beetle(Hive.Colour colour) {
        this.colour = colour;
    }

    @Override
    public Hive.Colour getColour() {
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
        for (Field N : neighbours) {
            if (N.getUnits().size() == 0) {
                // no unit in neighbouring field
                Collection<Field> nOfN = gb.getNeighboursForField(N).values();
                // also test neighbours of `test`, if there are no units we cannot move there
                long count = nOfN.stream().filter(f -> f.getUnits().size() != 0).count();
                if (count == 0) continue;
            }
            validMoves.add(new MoveAction(this, fromCoord, new Coord(N)));
        }
        return validMoves;
    }

    @Override
    public String toString() {
        return "Beetle(" + colour + ")";
    }
}
