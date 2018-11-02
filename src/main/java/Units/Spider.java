package Units;

import Game.Hive;
import Game.Pair;

import java.util.List;

public class Spider extends GameUnit {
    private Hive.Colour colour;

    public Spider(Hive.Colour colour) {
        this.colour = colour;
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
