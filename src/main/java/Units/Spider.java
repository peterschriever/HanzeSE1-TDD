package Units;

import Actions.MoveAction;
import Game.Hive;

import java.util.List;

public class Spider extends GameUnit {
    private Hive.Colour colour;

    public Spider(Hive.Colour colour) {
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
