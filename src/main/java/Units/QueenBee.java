package Units;

import Actions.MoveAction;
import Game.Hive;
import Game.Coord;

import java.util.List;

public class QueenBee extends GameUnit {
    private Hive.Colour colour;

    public QueenBee(Hive.Colour colour) {
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
        return null;
    }

    @Override
    public String toString() {
        return "QueenBee(" + colour + ")";
    }
}
