package aoc.day.days;

import java.util.*;
import aoc.day.Day;

public class Day9 extends Day {
    public static HashSet<MarkedField> markedFields = new HashSet<>();

    @Override
    public Object part1() {
        Node head = new Node(0);

        List<String> rawInput = getDayInput();
        List<Character> dirctions = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        for (int i = 0; i < rawInput.size(); i++) {
            String[] parts = rawInput.get(i).split(" ");
            dirctions.add(parts[0].charAt(0));
            distance.add(Integer.parseInt(parts[1]));
            head.move(distance.get(i),dirctions.get(i));
        }

        markedFields.add(new MarkedField(0,0));
        return markedFields.size();

    }

    @Override
    public Object part2() {
        return -1;
    }
}

class Node {
    private int x;
    private int y;

    private Node child;
    private int id;

    public Node(int id) {
        this.id=id;
        this.x = 0;
        this.y = 0;

        if (id<9){
            this.child=new Node(id+1);
        }
    }
    public void move(int distance, char direction) {
        for (int i = 0; i < distance; i++) {
            if (direction == 'U') {
                y ++;
            } else if (direction == 'D') {
                y --;
            } else if (direction == 'R') {
                x ++;
            } else if (direction == 'L') {
                x --;
            }
            System.out.println("----------");
            System.out.println("H "+x+" "+y);
            updateChild(String.valueOf(direction));
        }

    }
    public void updateChild(String direction) {
        if (this.child == null) {
            Day9.markedFields.add(new MarkedField(this.x, this.y));
            return;
        }
        if (direction.equals("")) {
            return;
        }
        int xDiff = Math.abs(x - child.x);
        int yDiff = Math.abs(y - child.x);
        int oldx = child.x;
        int oldy = child.y;

        System.out.println(this.id+" "+this.x + " " + this.y);
        if (xDiff > 1 || yDiff > 1) {
            if (direction.equals("U")) {
                child.y = y - 1;
                child.x = x;
            } else if (direction.equals("D")) {
                child.y = y + 1;
                child.x = x;
            } else if (direction.equals("R")) {
                child.x = x - 1;
                child.y = y;
            } else if (direction.equals("L")) {
                child.x = x + 1;
                child.y = y;
            } else if (direction.contains("RU")) {
                child.x = x - 1;
                child.y = y - 1;
            } else if (direction.equals("LU")) {
                child.x = x + 1;
                child.y = y - 1;
            } else if (direction.equals("RD")) {
                child.x = x - 1;
                child.y = y + 1;
            } else if (direction.equals("LD")) {
                child.x = x + 1;
                child.y = y + 1;
            }

            //calc out direction
            String directions = "";

            if (child.x > oldx) {
                directions += "R";
            }
            if (child.x < oldx) {
                directions += "L";
            }
            if (child.y > oldy) {
                directions += "U";
            }
            if (child.y < oldy) {
                directions += "D";
            }

            child.updateChild(directions);
        }


    }


}

class MarkedField {
    int x;
    int y;
    public MarkedField(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkedField that = (MarkedField) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x+"|"+y;
    }
}
