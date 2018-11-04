package Units;

import Actions.MoveAction;
import Game.*;

import java.util.*;

public class SoldierAnt extends GameUnit {
    private Hive.Colour colour;

    public SoldierAnt(Hive.Colour colour) {
        this.colour = colour;
    }

    @Override
    public Hive.Colour getColour() {
        return this.colour;
    }

    @Override
    public List<MoveAction> generateValidMoves(Coord fromCoord) {
        // SoldierAnt may move any amount of fields, as long as it does not break the swarm
        // the SoldierAnt also has to be able to shuffle through gaps TODO: Wouter Func
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        // try to pick up this unit
        if (!this.canFloat(gb)) return null;

        return checkNeighboursForValidMoves(fromCoord, fromCoord, gb, new LinkedList<>(), new LinkedList<>());
    }

    private List<MoveAction> checkNeighboursForValidMoves(Coord origFrom, Coord fromCoord, GameBoard gb, List<MoveAction> validMoves, LinkedList<Coord> visited) {
        if (visited.contains(fromCoord)) return null;
        Collection<Field> neighbours = gb.getNeighboursForField(fromCoord).values();
        Coord nCoord;
        for (Field N : neighbours) {
            nCoord = new Coord(N);
            if (!canMoveFromAToB(fromCoord, nCoord)) continue;
            if (N.getUnits().size() > 0) continue;

            // no unit in neighbouring field
            Collection<Field> nOfN = gb.getNeighboursForField(N).values();
            // also test neighbours of `N`, if there are no units we cannot move there
            long count = nOfN.stream().filter(f -> f.getUnits().size() != 0).count();
            if (count == 0) continue;

            visited.add(fromCoord);
            validMoves.add(new MoveAction(this, origFrom, nCoord));
            List<MoveAction> moreMoves = checkNeighboursForValidMoves(origFrom, nCoord, gb, validMoves, visited);
            if (moreMoves != null) validMoves.addAll(moreMoves);
        }
        return validMoves;
    }
    @Override
    public char getCharacter() { return 'A'; }

    @Override
    public String toString() {
        return "SoldierAnt(" + colour + ")";
    }
}
