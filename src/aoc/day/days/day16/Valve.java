package aoc.day.days.day16;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Valve {
    private List<Valve> leadsTo;
    private String name;
    private int value;
    private boolean isOpen = false;


    public Valve(String name, int value) {
        this.name = name;
        this.value = value;
        this.isOpen = false;
    }

    public void addLeadsTo(Valve... valves) {
        this.leadsTo = Arrays.asList(valves);
    }

    @Override
    public String toString() {
        return name + " " + value+" "+isOpen;
    }

    public String getName() {
        return name;
    }

    public List<Valve> getLeadsTo() {
        return leadsTo;
    }

    public int getValue() {
        return value;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valve valve = (Valve) o;
        return Objects.equals(name, valve.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
