package Units;

import Actions.MoveAction;
import Game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
    public List<MoveAction> generateValidMoves(int fromX, int fromY) {
        // beetle may move 1 space, as long as it does not break the swarm
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        HashMap<Coord, Field> neighbours = gb.getNeighboursForField(fromX, fromY);
        ArrayList<MoveAction> validMoves = new ArrayList<>(6);
        Coord fromCoord = new Coord(fromX, fromY);
        // new MoveAction(this, , new Coord(toX, toY));
        for (Field test : neighbours.values()) {
            if (test.getUnits().size() > 0) validMoves.add(new MoveAction(this, fromCoord, new Coord(test)));
        }

        return null;
    }


}
