package Actions;

import Units.GameUnit;

public class SpawnAction extends Action {

    public SpawnAction(GameUnit unit) {
        this.unit = unit;
    }

    @Override
    public GameUnit getUnit() {
        return unit;
    }
}
