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

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) return false;
        if (((Field) o).q != this.q) return false;
        if (((Field) o).r != this.r) return false;
        if (((Field) o).getUnits().size() != this.getUnits().size()) return false;
        if (this.getUnits().size() < 1) return true;
        return ((Field) o).getUnits().peek() == this.getUnits().peek();
    }

    @Override
    public String toString() {
        String unitsStr = "unfinished";
        return "q: " + q + ", r: " + r + ", units: [ " + unitsStr + " ]";
    }
}
