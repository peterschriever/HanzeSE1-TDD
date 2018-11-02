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

    @Override
    public String toString() {
        return this.getClass() + "@" + this.hashCode() + ":" + this.getUnit().toString() + "[" + this.spawnCoords.x + "," + this.spawnCoords.y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if(!o.getClass().equals(this.getClass()))
            return false;
        SpawnAction other = (SpawnAction) o;
        if(!this.spawnCoords.x.equals(other.spawnCoords.x))
            return false;
        if(!this.spawnCoords.y.equals(other.spawnCoords.y))
            return false;
        return this.getUnit().equals(other.getUnit());
    }
}
