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

        ArrayList<MoveAction> moves = new ArrayList<>();
        ArrayList<Field> visited = new ArrayList<>();
        Field fromField = gb.get(fromCoord.q, fromCoord.r);
        return checkNeighboursForValidMoves(fromField, fromField, visited, moves);
    }

    private ArrayList<MoveAction> checkNeighboursForValidMoves(Field fromField, Field check, ArrayList<Field> visited, ArrayList<MoveAction> moves) {
        GameBoard b = HiveGameFactory.getInstance().getBoard();
        int fq = check.getQ();
        int fr = check.getR();
        for(Field neighbour: b.getNeighboursForField(check).values()) {
            if(visited.contains(neighbour))
                continue;
            visited.add(neighbour);
            int tq = neighbour.getQ();
            int tr = neighbour.getR();
            if(neighbour.getUnits().isEmpty()) {
                boolean hasNeighbouringUnits = false;
                for(Field n_of_n: b.getNeighboursForField(neighbour).values()) {
                    if(!n_of_n.getUnits().isEmpty()) {
                        if(this != n_of_n.getUnits().peek())
                            hasNeighbouringUnits = true;
                    }
                }
                // If this is true, candidate for moves
                if(hasNeighbouringUnits) {
                    if(canMoveFromAToB(fq, fr, tq, tr)) {
                        MoveAction m = new MoveAction(this, new Coord(fromField.getQ(), fromField.getR()), new Coord(tq, tr));
//                        if(!moves.contains(m))
                        moves.add(m);
                        checkNeighboursForValidMoves(fromField, neighbour, visited, moves);
                    }
                }
            }
        }
        return moves;
    }

    @Override
    public char getCharacter() { return 'A'; }

    @Override
    public String toString() {
        return "SoldierAnt(" + colour + ")";
    }
}
