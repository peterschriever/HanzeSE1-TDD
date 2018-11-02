package Actions;

import Game.Pair;
import Units.GameUnit;

public class SpawnAction extends Action {
    private Pair<Integer, Integer> spawnCoords;

    public SpawnAction(GameUnit unit, Pair<Integer, Integer> spawnCoords) {
        this.unit = unit;
        this.spawnCoords = spawnCoords;
    }

    @Override
    public GameUnit getUnit() {
        return unit;
    }

    public Pair<Integer, Integer> getSpawnCoords() {
        return spawnCoords;
    }
}
