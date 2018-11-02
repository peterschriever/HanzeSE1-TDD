package Actions;

import Game.Pair;
import Units.GameUnit;

public class MoveAction extends Action {
    private Pair from;
    private Pair to;

    public MoveAction(GameUnit unit, Pair from, Pair to) {
        this.unit = unit;
        this.from = from;
        this.to = to;
    }

    @Override
    public GameUnit getUnit() {
        return unit;
    }

    public Pair getFrom() {
        return this.from;
    }

    public Pair getTo() {
        return this.to;
    }
}
