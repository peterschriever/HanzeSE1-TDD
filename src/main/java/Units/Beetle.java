package Units;

import Game.Hive;

public class Beetle implements GameUnit {
    private Hive.Colour colour;

    public Beetle(Hive.Colour colour) {
        this.colour = colour;
    }

    @Override
    public Hive.Colour getColour() {
        return this.colour;
    }
}
