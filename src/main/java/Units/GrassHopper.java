package Units;

import Game.Hive;
import Game.Pair;

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
    public List<Pair[]> generateValidPaths() {
        return null;
    }
}
