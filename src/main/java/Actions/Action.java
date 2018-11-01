package Actions;

import Game.Hive;

public abstract class Action {
    public Hive.Player player;

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }
}
