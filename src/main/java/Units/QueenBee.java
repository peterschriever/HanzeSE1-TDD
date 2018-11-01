package Units;

import Game.Hive;

public class QueenBee implements GameUnit {
    private Hive.Player player;

    public QueenBee(Hive.Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }

    @Override
    public String toString() {
        return "QueenBee (" + player + ")";
    }
}
