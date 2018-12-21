package nl.hanze.hive.Units;

import nl.hanze.hive.Actions.MoveAction;
import nl.hanze.hive.Game.Coord;
import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Game.GameBoard;
import nl.hanze.hive.Game.HiveGameFactory;
import nl.hanze.hive.Hive;

import java.util.ArrayList;
import java.util.List;

public abstract class GameUnit {
    protected Field field;

    public static GameUnit createUnitFromTile(Hive.Tile tile, Hive.Player colour) {
        if(tile == Hive.Tile.BEETLE) return new Beetle(colour);
        if(tile == Hive.Tile.GRASSHOPPER) return new GrassHopper(colour);
        if(tile == Hive.Tile.QUEEN_BEE) return new QueenBee(colour);
        if(tile == Hive.Tile.SOLDIER_ANT) return new SoldierAnt(colour);
        if(tile == Hive.Tile.SPIDER) return new Spider(colour);
        return null;
    }

    public abstract Hive.Player getColour();

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
        if(!aNeighbours.contains(b))
            return false;
        boolean units = false;
        for(Field testUnit: bNeighbours){
            if(!testUnit.getUnits().isEmpty() && !testUnit.equals(a))
                units = true;
        }
        if(!units)
            return false;
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
        return gb.isSwarmWithout(this);
    }

    public abstract Hive.Tile getTile();

    @Override
    public abstract String toString();
}
