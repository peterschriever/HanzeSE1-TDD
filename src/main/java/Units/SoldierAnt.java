package Units;

import Actions.MoveAction;
import Game.Hive;
import Game.Coord;

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
    public List<MoveAction> generateValidMoves(int fromX, int toX) {
        return null;
    }
}
