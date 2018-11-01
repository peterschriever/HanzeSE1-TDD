package Actions;

import Units.GameUnit;

public class MoveAction extends Action {

    public MoveAction(GameUnit unit) {
        this.unit = unit;
    }

    @Override
    public GameUnit getUnit() {
        return unit;
    }
}
