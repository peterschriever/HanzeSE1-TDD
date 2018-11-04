package Units;

import Actions.MoveAction;
import Game.*;

import java.util.HashMap;
import java.util.List;

public class Beetle extends GameUnit {
    private Hive.Colour colour;

    public Beetle(Hive.Colour colour) {
        this.colour = colour;
    }

    @Override
    public Hive.Colour getColour() {
        return this.colour;
    }

    @Override
    public List<MoveAction> generateValidPaths(int fromX, int fromY) {
        // beetle may move 1 space, as long as it does not break the swarm
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        HashMap<Coord, Field> neighbours = gb.getNeighboursForField(fromX, fromY);

        return null;
    }


}
