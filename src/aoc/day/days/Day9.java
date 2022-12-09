package aoc.day.days;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import aoc.day.Day;

import static aoc.day.days.Day9.markedFields;

public class Day9 extends Day {
    public static HashSet<Koordinate> markedFields = new HashSet<>();

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

        markedFields.add(new Koordinate(0,0));
        return markedFields.size();

    }

    @Override
    public Object part2() {
        return -1;
    }
}

class Node {
    private Koordinate koordinate;
    private Node child;
    private Node parent;
    private int id;

    List<ChildField> childFields;

    public Node(int id) {
        this.id = id;
        this.koordinate = new Koordinate(0,0);

        if (id != 9) {
            this.child = new Node(id + 1);
            child.parent = this;
        }

    }
    public void move(int distance, char direction) {
        for (int i = 0; i < distance; i++) {
            childFields= new ArrayList<>();
            if (direction == 'R') {
                koordinate.setX(koordinate.getX()+1);

                childFields.add(new ChildField(2,new Koordinate(koordinate.getX()-1,koordinate.getY())));
                childFields.add(new ChildField(1,new Koordinate(koordinate.getX()-1,koordinate.getY()+1)));
                childFields.add(new ChildField(1,new Koordinate(koordinate.getX()-1,koordinate.getY()-1)));

            } else if (direction == 'L') {
                koordinate.setX(koordinate.getX()-1);

                childFields.add(new ChildField(2,new Koordinate(koordinate.getX()+1,koordinate.getY())));
                childFields.add(new ChildField(1,new Koordinate(koordinate.getX()+1,koordinate.getY()+1)));
                childFields.add(new ChildField(1,new Koordinate(koordinate.getX()+1,koordinate.getY()-1)));

            } else if (direction == 'U') {
                koordinate.setY(koordinate.getY()+1);

                childFields.add(new ChildField(2,new Koordinate(koordinate.getX(),koordinate.getY()-1)));
                childFields.add(new ChildField(1,new Koordinate(koordinate.getX()+1,koordinate.getY()-1)));
                childFields.add(new ChildField(1,new Koordinate(koordinate.getX()-1,koordinate.getY()-1)));

            } else if (direction == 'D') {
                koordinate.setY(koordinate.getY()-1);

                childFields.add(new ChildField(2,new Koordinate(koordinate.getX(),koordinate.getY()+1)));
                childFields.add(new ChildField(1,new Koordinate(koordinate.getX()+1,koordinate.getY()+1)));
                childFields.add(new ChildField(1,new Koordinate(koordinate.getX()-1,koordinate.getY()+1)));

            }
            childFields.add(new ChildField(3,new Koordinate(koordinate.getX(),koordinate.getY())));

            if (this.koordinate.equals(new Koordinate(4,4))) {
                System.out.println();
            }
            child.followParent();
        }
    }
    public void followParent(){
        if (parent != null) {
            List<Koordinate> surroundingFields = getSurroundingFields();

            List<ChildField> intersect = intersection(parent.childFields, surroundingFields);
            Collections.sort(intersect);

            if (intersect.contains(koordinate)) return;
            if (intersect.size() == 4) return;
            if (intersect.size()==0){
                System.out.println("Error");
            }
            if(intersect.get(0).equals(koordinate))return;



            Koordinate oldKoordinate = new Koordinate(koordinate.getX(),koordinate.getY());
            koordinate = intersect.get(0);
            this.childFields = getChildFields(koordinate,oldKoordinate);

            if(id==4&&koordinate.equals(new Koordinate(2,7))){
                System.out.println("intersect = " + intersect);
            }

            if (this.child != null) {
                this.child.followParent();
            }else {
                markedFields.add(koordinate);
            }
        }
    }

    private ChildField containsDuplicate(List<ChildField> childFields) {
        for (int i = 0; i < childFields.size(); i++) {
            for (int j = 0; j < childFields.size(); j++) {
                if (i != j) {
                    if (childFields.get(i).equals(childFields.get(j))) {
                        return childFields.get(i);
                    }
                }
            }
        }
        return null;
    }
    private List<ChildField> intersection(List<ChildField> list1, List<Koordinate> list2) {
        List<ChildField> list = new ArrayList<>();

        for (ChildField t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    private List<ChildField> getChildFields(Koordinate koordinate, Koordinate oldKoordinate) {
        //direction
        int x = koordinate.getX() - oldKoordinate.getX();
        int y = koordinate.getY() - oldKoordinate.getY();

        List<ChildField> childFields = new ArrayList<>();

        if (x >= 1){
            childFields.add(new ChildField(2,new Koordinate(koordinate.getX()-1,koordinate.getY())));
            childFields.add(new ChildField(1,new Koordinate(koordinate.getX()-1,koordinate.getY()+1)));
            childFields.add(new ChildField(1,new Koordinate(koordinate.getX()-1,koordinate.getY()-1)));
        } else if (x <= -1) {
            childFields.add(new ChildField(2, new Koordinate(koordinate.getX() + 1, koordinate.getY())));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() + 1)));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() - 1)));
        }

        if (y >= 1){
            childFields.add(new ChildField(2,new Koordinate(koordinate.getX(),koordinate.getY()-1)));
            childFields.add(new ChildField(1,new Koordinate(koordinate.getX()+1,koordinate.getY()-1)));
            childFields.add(new ChildField(1,new Koordinate(koordinate.getX()-1,koordinate.getY()-1)));
        } else if (y <= -1) {
            childFields.add(new ChildField(2, new Koordinate(koordinate.getX(), koordinate.getY() + 1)));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() + 1)));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() - 1, koordinate.getY() + 1)));
        }

        return childFields;

    }

    private List<Koordinate> getSurroundingFields() {
        List<Koordinate> surroundingFields = new ArrayList<>();
        surroundingFields.add(new Koordinate(koordinate.getX()+1,koordinate.getY()));
        surroundingFields.add(new Koordinate(koordinate.getX()-1,koordinate.getY()));
        surroundingFields.add(new Koordinate(koordinate.getX(),koordinate.getY()+1));
        surroundingFields.add(new Koordinate(koordinate.getX(),koordinate.getY()-1));

        surroundingFields.add(new Koordinate(koordinate.getX()+1,koordinate.getY()+1));
        surroundingFields.add(new Koordinate(koordinate.getX()-1,koordinate.getY()-1));
        surroundingFields.add(new Koordinate(koordinate.getX()+1,koordinate.getY()-1));
        surroundingFields.add(new Koordinate(koordinate.getX()-1,koordinate.getY()+1));

        surroundingFields.add(new Koordinate(koordinate.getX(),koordinate.getY()));

        return surroundingFields;
    }

    private Koordinate getKoordinate() {
        return koordinate;
    }
}

class ChildField extends Koordinate implements Comparable<ChildField>{
    int priority;
    Koordinate koordinate;
    public ChildField(int priority, Koordinate koordinate) {
        super(koordinate.getX(),koordinate.getY());
        this.priority = priority;
    }

    @Override
    public int compareTo(ChildField o) {
        return -1 * Integer.compare(priority,o.priority);
    }
}

class Koordinate {
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
        return "X"+x+" | Y"+y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!Koordinate.class.isAssignableFrom(o.getClass()))return false;
        return x == ((Koordinate) o).getX() &&
                y == ((Koordinate) o).getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}