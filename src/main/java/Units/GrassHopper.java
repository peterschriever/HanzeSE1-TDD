package Units;

import Game.Hive;

public class GrassHopper implements GameUnit {

    public GrassHopper(Hive.Player player) {

    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }

}
