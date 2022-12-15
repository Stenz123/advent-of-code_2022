package aoc.day.days.day9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        if (o == null || getClass() != o.getClass()) return false;
        Koordinate that = (Koordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public List<Koordinate> getNeighbours() {
        List<Koordinate> result = new ArrayList<>();
        result.add(new Koordinate(x+1,y));
        result.add(new Koordinate(x-1,y));
        result.add(new Koordinate(x,y+1));
        result.add(new Koordinate(x,y-1));
        return result;
    }
}
