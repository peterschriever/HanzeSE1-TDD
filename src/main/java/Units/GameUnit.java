package Units;

import Actions.MoveAction;
import Game.Coord;
import Game.Field;
import Game.GameBoard;
import Game.Hive;
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

    protected boolean canFloat(GameBoard gb) {
        try {
            this.field.removeUnit(this); // unit is now floating (does not exist on board)
            if (!gb.isSwarm()) {
                this.field.acceptUnit(this); // put unit back in its original field
                return false; // return empty, because moving would break the swarm
            }
        } catch (Hive.IllegalMove illegalMove) {
            // this unit is not the top-most unit of the Field, we cannot move
            return false;
        }
        return true;
    }

    @Override
    public abstract String toString();
}
