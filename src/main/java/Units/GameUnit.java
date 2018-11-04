package Units;

import Actions.MoveAction;
import Game.Hive.Colour;

import java.util.List;

public abstract class GameUnit {

    public abstract Colour getColour();

    public abstract List<MoveAction> generateValidMoves(int fromX, int toX);

    @Override
    public boolean equals(Object obj) {
        if(!this.getClass().equals(obj.getClass()))
            return false;
        GameUnit o = (GameUnit) obj;
        return this.getColour().equals(o.getColour());
    }
}
