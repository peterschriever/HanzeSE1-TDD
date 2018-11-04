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
        return this.getClass() + "@" + this.hashCode() + ":" + this.getUnit().toString() + "[" + this.spawnCoord.x + "," + this.spawnCoord.y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if(!o.getClass().equals(this.getClass()))
            return false;
        SpawnAction other = (SpawnAction) o;
        if(!this.spawnCoord.x.equals(other.spawnCoord.x))
            return false;
        if(!this.spawnCoord.y.equals(other.spawnCoord.y))
            return false;
        return this.getUnit().equals(other.getUnit());
    }
}
