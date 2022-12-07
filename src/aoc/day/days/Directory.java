package aoc.day.days;

public class Directory {
    private String name;
    private Directory parent;

    private int Size;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getParent() {
        return parent;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
