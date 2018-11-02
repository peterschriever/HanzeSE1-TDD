package Units;

import Actions.MoveAction;
import Game.Hive.Colour;

import java.util.List;

public abstract class GameUnit {

    public abstract Colour getColour();

    public abstract List<MoveAction> generateValidPaths(int fromX, int toX);

}
