package Units;

import Game.Hive.Colour;
import Game.Pair;

import java.util.List;

public abstract class GameUnit {

    public abstract Colour getColour();

    public abstract List<Pair[]> generateValidPaths();

    @Override
    public boolean equals(Object obj) {
        if(!this.getClass().equals(obj.getClass()))
            return false;
        GameUnit o = (GameUnit) obj;
        return this.getColour().equals(o.getColour());
    }
}
