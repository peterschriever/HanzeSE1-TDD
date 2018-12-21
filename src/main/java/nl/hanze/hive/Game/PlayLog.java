package nl.hanze.hive.Game;

import nl.hanze.hive.Actions.Action;

import java.util.Stack;

public class PlayLog {
    private Stack<Action> moves = new Stack<>();

    public void writeLog(Action action) {
        moves.add(action);
    }

    public int size() {
        return moves.size();
    }
}
