package aoc.day.days.day9;

class ChildField extends Koordinate implements Comparable<ChildField> {
    int priority;
    Koordinate koordinate;

    public ChildField(int priority, Koordinate koordinate) {
        super(koordinate.getX(), koordinate.getY());
        this.priority = priority;
    }

    @Override
    public int compareTo(ChildField o) {
        return -1 * Integer.compare(priority, o.priority);
    }
}
