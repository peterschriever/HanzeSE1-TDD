package Actions;

import Game.Coord;
import Units.GameUnit;

public class MoveAction extends Action {
    private Coord from;
    private Coord to;

    public MoveAction(GameUnit unit, Coord from, Coord to) {
        this.unit = unit;
        this.from = from;
        this.to = to;
    }

    @Override
    public GameUnit getUnit() {
        return unit;
    }

    public Coord getFrom() {
        return this.from;
    }

    public Coord getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "Move " + unit + ", from: " + from + ", to: " + to + "}";
    }
}
