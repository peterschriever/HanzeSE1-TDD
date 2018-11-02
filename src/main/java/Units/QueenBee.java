package Units;

import Game.Hive;

public class QueenBee implements GameUnit {
    private Hive.Colour colour;

    public QueenBee(Hive.Colour colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }

    @Override
    public String toString() {
        return "QueenBee (" + colour + ")";
    }

    @Override
    public Hive.Colour getColour() {
        return this.colour;
    }
}
