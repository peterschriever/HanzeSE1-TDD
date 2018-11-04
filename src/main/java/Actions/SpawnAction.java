package Actions;

import Game.Coord;
import Units.GameUnit;

public class SpawnAction extends Action {
    private Coord spawnCoord;

    public SpawnAction(GameUnit unit, Coord spawnCoord) {
        this.unit = unit;
        this.spawnCoord = spawnCoord;
    }

    @Override
    public GameUnit getUnit() {
        return unit;
    }

    public Coord getSpawnCoord() {
        return spawnCoord;
    }

    @Override
    public String toString() {
        return this.getClass() + "@" + this.hashCode() + ":" + this.getUnit().toString() + "[" + this.spawnCoord.q + "," + this.spawnCoord.r + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SpawnAction)) return false;
        SpawnAction other = (SpawnAction) o;
        if (!this.spawnCoord.q.equals(other.spawnCoord.q)) return false;
        if (!this.spawnCoord.r.equals(other.spawnCoord.r)) return false;
        return this.getUnit().equals(other.getUnit());
    }
}
