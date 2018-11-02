package Units;

import Game.Hive;

public class SoldierAnt implements GameUnit {
    private Hive.Player player;

    public SoldierAnt(Hive.Player player) {
        this.player = player;
    }

    @Override
    public Hive.Player getPlayer() {
        return this.player;
    }
}
