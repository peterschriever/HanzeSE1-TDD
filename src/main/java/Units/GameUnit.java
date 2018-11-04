package Units;

import Actions.MoveAction;
import Game.Coord;
import Game.Field;
import Game.GameBoard;
import Game.Hive;
import Game.Hive.Colour;
import Game.HiveGameFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class GameUnit {
    protected Field field;

    public abstract Colour getColour();

    public abstract char getCharacter();

    public abstract List<MoveAction> generateValidMoves(Coord fromCoord);

    public boolean canMoveFromAToB(Coord fromCoord, Coord toCoord) {
        return canMoveFromAToB(fromCoord.q, fromCoord.r, toCoord.q, toCoord.r);
    }

    public boolean canMoveFromAToB(int fromQ, int fromR, int toQ, int toR) {
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        Field a = gb.get(fromQ, fromR);
        Field b = gb.get(toQ, toR);
        List<Field> aNeighbours = new ArrayList<>(gb.getNeighboursForField(a).values());
        List<Field> bNeighbours = new ArrayList<>(gb.getNeighboursForField(b).values());
        Field match_one = null;
        Field match_two = null;
        for (Field f : aNeighbours) {
            if (bNeighbours.contains(f)) {
                if (match_one == null)
                    match_one = f;
                else
                    match_two = f;
            }
        }
        return match_one.getUnits().size() == 0 || match_two.getUnits().size() == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().equals(obj.getClass()))
            return false;
        GameUnit o = (GameUnit) obj;
        return this.getColour().equals(o.getColour());
    }

    public void setField(Field field) {
        this.field = field;
    }

    protected boolean canFloat(GameBoard gb) {
        if (!gb.isSwarmWithout(this)) {
            System.out.println("NO");
            return false; // return empty, because moving would break the swarm
        } else {
            System.out.println("YA");
            return true;
        }
    }

    @Override
    public abstract String toString();
}
