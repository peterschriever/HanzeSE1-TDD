package Units;

import Actions.MoveAction;
import Game.Coord;
import Game.Hive;

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
        return null;
    }

    @Override
    public char getCharacter() { return 'G'; }

    @Override
    public String toString() {
        return "GrassHopper(" + colour + ")";
    }
}
