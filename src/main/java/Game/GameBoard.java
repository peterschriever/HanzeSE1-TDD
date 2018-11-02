package Game;

import Actions.Action;

import java.util.HashMap;

public class GameBoard {
    private HashMap<Pair<Integer, Integer>, Field> board = new HashMap<>();

    public GameBoard() {
        this.board.put(new Pair<>(0, 0), new Field(0, 0));
    }

    public void applyAction(Action action) {
        Field root = board.get(new Pair<>(0, 0));
        root.acceptUnit(action.getUnit());
    }

    public Field addNewField(int q, int r) {
        Field f = new Field(q, r);
        this.board.put(new Pair<>(q, r), f);
        return f;
    }

    public HashMap<Pair<Integer, Integer>, Field> getNeighboursForField(int q, int r) {
        HashMap<Pair<Integer, Integer>, Field> neighbours = new HashMap<>();
        int[][] adjustments = {{0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {1, 0}, {1, -1}};
        for (int[] a : adjustments) {
            int new_q = q + a[0];
            int new_r = r + a[1];
            neighbours.put(new Pair<>(new_q, new_r), this.get(new_q, new_r));
        }
        return neighbours;
    }

    public Field get(int q, int r) {
        Field f = this.board.get(new Pair<>(q, r));
        if (f == null) {
            f = this.addNewField(q, r);
        }
        return f;
    }

    public int size() {
        return this.board.size();
    }
}
