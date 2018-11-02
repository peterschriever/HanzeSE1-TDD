package Actions;

import Units.GameUnit;

public abstract class Action {
    protected GameUnit unit;

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }

    public abstract GameUnit getUnit();
}
