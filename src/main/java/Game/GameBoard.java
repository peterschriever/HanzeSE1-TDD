package Game;

import Actions.Action;

public class GameBoard {
    private final Field root = new Field(0, 0);

    public void applyAction(Action action) {
        root.acceptUnit(action.getUnit());
    }

    public Field getRootField() {
        return root;
    }
}
