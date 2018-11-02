package Units;

import Game.Hive;

public class Spider implements GameUnit {
    private Hive.Player player;

    public Spider(Hive.Player player) {
        this.player = player;
    }

    @Override
    public Hive.Player getPlayer() {
        return this.player;
    }
}
