package Game;

public class Coord {
    public Integer x;
    public Integer y;

    public Coord (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.x != null ? this.x.hashCode() : 0);
        hash = 31 * hash + (this.y != null ? this.y.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != this.getClass()) return false;

        Coord coord = ((Coord) o);
        if (!this.x.equals(coord.x)) return false;
        return this.y.equals(coord.y);
    }
}
