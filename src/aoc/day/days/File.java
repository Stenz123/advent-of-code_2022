package aoc.day.days;

public class File {
    private String name;
    private int size;
    private Directory parent;

public File(String name, int size, Directory parent) {
        this.name = name;
        this.size = size;
        this.parent = parent;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return size+" "+name;
    }
}
