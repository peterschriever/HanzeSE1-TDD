package nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.Units.GameUnit;

import java.util.Stack;
import java.util.stream.Collectors;

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
        unit.setField(this);
        units.push(unit);
    }

    public GameUnit removeUnit(GameUnit unit) throws Hive.IllegalMove {
        if (unit != units.peek()) throw new Hive.IllegalMove("You may only move the top-most unit of the field");
        unit.setField(null);
        return units.pop();
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
        return ((Field) o).getUnits().peek().equals(this.getUnits().peek());
    }

    @Override
    public String toString() {
        String unitsStr = units.stream().map(Object::toString).collect(Collectors.joining(","));
        return "q: " + q + ", r: " + r + ", units: [ " + unitsStr + " ], size: " + units.size();
    }
}
