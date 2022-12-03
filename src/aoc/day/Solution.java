package aoc.day;

public class Solution implements Comparable<Solution> {

    private int day;
    private Object solution1 = null;
    private Object solution2 = null;

    public Solution(int day, Object solution1, Object solution2) {
        this.solution1 = solution1;
        this.solution2 = solution2;
        this.day = day;
    }
    public Solution(int day, Object solution1){
        this.day=day;
        this.solution1=solution1;
        this.solution2=-1;
    }
    public Solution(int day){
        this.day=day;
        this.solution1=-1;
        this.solution2=-1;
    }

    public Object getSolution1() {
        return solution1;
    }
    private Object getSolution2() {
        return solution2;
    }
    private int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setSolution1(Object solution1) {
        this.solution1 = solution1;
    }

    public void setSolution2(Object solution2) {
        this.solution2 = solution2;
    }

    @Override
    public String toString() {
        return String.format("Day %d: {%s, %s}",day, solution1.toString(), solution2.toString());
    }

    @Override
    public int compareTo(Solution o) {
        return Integer.compare(this.day, o.getDay());
    }
}
