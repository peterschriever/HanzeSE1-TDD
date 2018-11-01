package Game;

import Actions.Action;
import Units.QueenBee;

public class GameBoard {
    private final Field root = new Field(0, 0);

    public void applyAction(Action action) {
        root.acceptUnit(new QueenBee(action.player));
    }

    public Field getRootField() {
        return root;
    }
}
