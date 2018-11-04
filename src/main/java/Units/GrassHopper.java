package Units;

import Actions.MoveAction;
import Game.Coord;
import Game.GameBoard;
import Game.Hive;
import Game.HiveGameFactory;

import java.util.List;

public class GrassHopper extends GameUnit {
    private Hive.Colour colour;

    public GrassHopper(Hive.Colour colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }

    @Override
    public Hive.Colour getColour() {
        return this.colour;
    }

    @Override
    public List<MoveAction> generateValidMoves(Coord fromCoord) {
        // beetle may move 1 space, as long as it does not break the swarm
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        // try to pickup unit
        if (!this.canFloat(gb)) return null;
        // find valid fields to move to
        return checkNeighboursForValidMoves(fromCoord, gb);
    }

    private List<MoveAction> checkNeighboursForValidMoves(Coord fromCoord, GameBoard gb) {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


        return null;
    }

    @Override
    public char getCharacter() { return 'G'; }

    @Override
    public String toString() {
        return "GrassHopper(" + colour + ")";
    }
}
