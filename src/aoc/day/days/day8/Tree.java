package aoc.day.days.day8;

public class Tree {
    private int heigth;
    private int isVisible;

    private int x;
    private int y;

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public Tree(int heigth, int x, int y) {
        this.heigth = heigth;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return ""+heigth+isVisible;
    }
}
