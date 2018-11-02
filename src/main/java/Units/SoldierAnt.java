package Units;

import Actions.MoveAction;
import Game.Hive;
import Game.Pair;

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
    public List<MoveAction> generateValidPaths(int fromX, int toX) {
        return null;
    }
}
