package Game;

import Actions.Action;
import Actions.SpawnAction;
import Units.GameUnit;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {
    private HashMap<Coord, Field> board = new HashMap<>();
    private final int TOTAL_UNITS = 22;

    public GameBoard() {
        this.board.put(new Coord(0, 0), new Field(0, 0));
    }


    public Field addNewField(int q, int r) {
        Field f = new Field(q, r);
        this.board.put(new Coord(q, r), f);
        return f;
    }

    /**
     * Returns true if the player is forced to make the first move.
     *
     * @return boolean
     */
    public boolean shouldMakeFirstMove() {
        return this.size() == 0;
    }

    public HashMap<Coord, Field> getNeighboursForField(Field f) {
        return this.getNeighboursForField(f.getQ(), f.getR());
    }

    public HashMap<Coord, Field> getNeighboursForField(Coord coord) {
        return this.getNeighboursForField(coord.q, coord.r);
    }

    public HashMap<Coord, Field> getNeighboursForField(int q, int r) {
        HashMap<Coord, Field> neighbours = new HashMap<>();
        int[][] adjustments = {{0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {1, 0}, {1, -1}};
        for (int[] a : adjustments) {
            int new_q = q + a[0];
            int new_r = r + a[1];
            neighbours.put(new Coord(new_q, new_r), this.get(new_q, new_r));
        }
        return neighbours;
    }

    public Field get(int q, int r) {
        Field f = this.board.get(new Coord(q, r));
        if (f == null) {
            f = this.addNewField(q, r);
        }
        return f;
    }

    public ArrayList<Field> getFieldsWithUnits() {
        ArrayList<Field> values = new ArrayList<>(TOTAL_UNITS);
        for (Field f : this.board.values()) {
            if (f.getUnits().size() > 0) {
                values.add(f);
            }
        }
        return values;
    }

    private Field getFirstField() {
        for (Field next : this.board.values()) {
            if (next.getUnits().size() > 0) {
                return next;
            }
        }
        return null;
    }

    private Field getFirstFieldWithout(GameUnit without) {
        for (Field next : this.board.values()) {
            if (next.getUnits().size() > 0) {
                if(without != null)
                    if(next.getUnits().peek().equals(without))
                        continue;
                return next;
            }
        }
        return null;
    }

    private int getRealSize() {
        int real_size = 0;
        for (Field next : this.board.values()) {
            real_size += next.getUnits().size();
        }
        return real_size;
    }

    public boolean isSwarm() {
        int real_size = getRealSize();
        Field first_field = getFirstField();

        if (real_size == 0 || first_field == null) {
            return true;
        }

        ArrayList<Field> visited = new ArrayList<>();
        visited.add(first_field);
        int size = this.sizeOfSubSwarm(first_field, visited, null);

        return size == real_size;
    }

    public boolean isSwarmWithout(GameUnit without) {
        int real_size = getRealSize();
        Field first_field = getFirstFieldWithout(without);

        if (real_size == 0 || first_field == null) {
            return true;
        }

        ArrayList<Field> visited = new ArrayList<>();
        visited.add(first_field);
        int size = this.sizeOfSubSwarm(first_field, visited, without);
        size = (without == null) ? size : size + 1;
        System.out.println(size);
        System.out.println(real_size);

        return size == real_size;
    }

    private int sizeOfSubSwarm(Field test, ArrayList<Field> visited, GameUnit without) {
        if (test.getUnits().size() == 0) {
            return 0;
        }
        if(without != null) {
            if (test.getUnits().peek() == without) {
                return 0;
            }
        }
        int size = test.getUnits().size();
        for (Field n : this.getNeighboursForField(test.getQ(), test.getR()).values()) {
            if (visited.contains(n)) {
                continue;
            }

            if (n.getUnits().size() > 0) {
                visited.add(n);
                size = size + this.sizeOfSubSwarm(n, visited, without);
            }
        }
        return size;
    }

    public int size() {
        return this.board.size();
    }
}
