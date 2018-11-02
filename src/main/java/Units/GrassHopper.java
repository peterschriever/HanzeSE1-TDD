package Units;

import Game.Hive;

public class GrassHopper implements GameUnit {
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
}
