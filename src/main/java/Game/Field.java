package Game;

import Units.GameUnit;

import java.util.Stack;

public class Field {
    private int q;
    private int r;
    private Stack<GameUnit> units;

    public Field(int q, int r) {
        this.q = q;
        this.r = r;
        this.units = new Stack<>();
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public void acceptUnit(GameUnit unit) {
        units.push(unit);
    }

    public Stack<GameUnit> getUnits() {
        return units;
    }
}
