package Units;

import Actions.MoveAction;
import Game.*;

import java.util.ArrayList;
import java.util.List;

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

        return checkNeighboursForValidMoves(fromCoord, gb);
    }

    private List<MoveAction> checkNeighboursForValidMoves(Coord fromCoord, GameBoard gb) {
        List<Field> fields = gb.getFieldsWithUnits();
        ArrayList<MoveAction> validMoves = new ArrayList<>(fields.size() * 3);
        for (Field f : fields) {
            // @TODO: implement
            // loop over all fields with units
            // retrieve their neighbours
            // if the neighbour field is empty, try to build a path to this field
            // during the pathbuilding use TODO: WouterFunc
        }
        return validMoves;
    }

    @Override
    public String toString() {
        return "SoldierAnt(" + colour + ")";
    }
}
