package Actions;

import Game.Hive;
import Units.GameUnit;

public abstract class Action {
    protected Hive.Player player;
    protected GameUnit unit;

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }

    public Hive.Player getPlayer() {
        return this.player;
    }

    public abstract GameUnit getUnit();
}
