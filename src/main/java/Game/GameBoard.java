package Game;

import Actions.Action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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

    /**
     * Returns true if the player is forced to make the first move.
     * @return boolean
     */
    public boolean shouldMakeFirstMove() {
        return this.size() == 0;
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

    public ArrayList<Field> getFieldsWithUnits() {
        ArrayList<Field> values = new ArrayList<>();
        for(Field f : this.board.values()) {
            if(f.getUnits().size() > 0) {
                values.add(f);
            }
        }
        return values;
    }

    public boolean isSwarm() {
        int real_size = 0;
        Field first_field = null;

        for (Field next : this.board.values()) {
            if (first_field == null && next.getUnits().size() > 0) {
                first_field = next;
            }
            real_size += next.getUnits().size();
        }
        if(real_size == 0) {
            return true;
        }

        ArrayList<Field> visited = new ArrayList<>();
        visited.add(first_field);
        int size = this.sizeOfSubSwarm(first_field, visited);

        return size == real_size;
    }
    private int sizeOfSubSwarm(Field test, ArrayList<Field> visited) {
        if(test.getUnits().size() == 0) {
            return 0;
        }
        int size = test.getUnits().size();
        for(Field n : this.getNeighboursForField(test.getQ(), test.getR()).values()) {
            if(visited.contains(n)) {
                continue;
            }
            if(n.getUnits().size() > 0) {
                visited.add(n);
                size = size + this.sizeOfSubSwarm(n, visited);
            }
        }
        return size;
    }

    public int size() {
        return this.board.size();
    }
}
