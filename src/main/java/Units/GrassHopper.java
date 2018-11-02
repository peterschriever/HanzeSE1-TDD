package Units;

import Game.Hive;

public class GrassHopper implements GameUnit {
    private Hive.Player player;

    public GrassHopper(Hive.Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }

    @Override
    public Hive.Player getPlayer() {
        return this.player;
    }
}
