package Game;

public class Pair <X, Y> {
    public X x;
    public Y y;

    public Pair (X x, Y y) {
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
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (this.x != null ? !this.x.equals(pair.x) : pair.x != null) return false;
            if (this.y != null ? !this.y.equals(pair.y) : pair.y != null) return false;
            return true;
        }
        return false;
    }
}
