package Units;

import Actions.MoveAction;
import Game.Coord;
import Game.Field;
import Game.Hive.Colour;

import java.util.List;

public abstract class GameUnit {
    protected Field field;

    public abstract Colour getColour();

    public abstract List<MoveAction> generateValidMoves(Coord fromCoord);

    @Override
    public boolean equals(Object obj) {
        if(!this.getClass().equals(obj.getClass()))
            return false;
        GameUnit o = (GameUnit) obj;
        return this.getColour().equals(o.getColour());
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public abstract String toString();
}
