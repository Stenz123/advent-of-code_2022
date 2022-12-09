package aoc.day.days.day9;

import java.util.Objects;

public class Koordinate {
    private int x;
    private int y;

    public Koordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "X" + x + " | Y" + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!Koordinate.class.isAssignableFrom(o.getClass())) return false;
        return x == ((Koordinate) o).getX() &&
                y == ((Koordinate) o).getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
