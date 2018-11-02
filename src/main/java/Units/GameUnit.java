package Units;

import Game.Hive.Colour;
import Game.Pair;

import java.util.List;

public abstract class GameUnit {

    public abstract Colour getColour();

    public abstract List<Pair[]> generateValidPaths();

}
