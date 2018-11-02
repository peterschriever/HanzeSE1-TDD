package Actions;

import Game.Pair;
import Units.GameUnit;

public class SpawnAction extends Action {
    private Pair spawnCoords;

    public SpawnAction(GameUnit unit, Pair spawnCoords) {
        this.unit = unit;
        this.spawnCoords = spawnCoords;
    }

    @Override
    public GameUnit getUnit() {
        return unit;
    }

    public Pair getSpawnCoords() {
        return spawnCoords;
    }
}
