package Player;

import Actions.Action;
import Game.Hive;

public class Human extends Player {
    public Human(Hive.Colour c) {
        super(c);
    }

    @Override
    public Action chooseAction() {
        System.out.println(this.queenbee);
        return null;
    }
}
