package Game;

public class Coord {
    public Integer q;
    public Integer r;

    public Coord(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public Coord(Field field) {
        this.q = field.getQ();
        this.r = field.getR();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.q != null ? this.q.hashCode() : 0);
        hash = 31 * hash + (this.r != null ? this.r.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != this.getClass()) return false;

        Coord coord = ((Coord) o);
        if (!this.q.equals(coord.q)) return false;
        return this.r.equals(coord.r);
    }
}
