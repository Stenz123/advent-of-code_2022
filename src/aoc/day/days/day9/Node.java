package aoc.day.days.day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static aoc.day.days.Day9.markedFields;

public class Node {
    private Koordinate koordinate;
    private Node child;
    private Node parent;
    public int id;

    List<ChildField> childFields;

    private static List<Node> childrean = new ArrayList<>();
    private static Node head;


    public Node(int id) {
        markedFields.clear();
        this.id = id;
        this.koordinate = new Koordinate(11, 5);
        markedFields.add(koordinate);

        if (id == 0) {
            head = this;
        }
        if (id != 9) {
            this.child = new Node(id + 1);
            child.parent = this;
            childrean.add(child);
        }
    }

    public void move(int distance, char direction) {


        for (int i = 0; i < distance; i++) {
            childFields = new ArrayList<>();
            if (direction == 'R') {
                koordinate.setX(koordinate.getX() + 1);

                childFields.add(new ChildField(2, new Koordinate(koordinate.getX() - 1, koordinate.getY())));
                childFields.add(new ChildField(1, new Koordinate(koordinate.getX() - 1, koordinate.getY() + 1)));
                childFields.add(new ChildField(1, new Koordinate(koordinate.getX() - 1, koordinate.getY() - 1)));

            } else if (direction == 'L') {
                koordinate.setX(koordinate.getX() - 1);

                childFields.add(new ChildField(2, new Koordinate(koordinate.getX() + 1, koordinate.getY())));
                childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() + 1)));
                childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() - 1)));

            } else if (direction == 'U') {
                koordinate.setY(koordinate.getY() + 1);

                childFields.add(new ChildField(2, new Koordinate(koordinate.getX(), koordinate.getY() - 1)));
                childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() - 1)));
                childFields.add(new ChildField(1, new Koordinate(koordinate.getX() - 1, koordinate.getY() - 1)));

            } else if (direction == 'D') {
                koordinate.setY(koordinate.getY() - 1);

                childFields.add(new ChildField(2, new Koordinate(koordinate.getX(), koordinate.getY() + 1)));
                childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() + 1)));
                childFields.add(new ChildField(1, new Koordinate(koordinate.getX() - 1, koordinate.getY() + 1)));

            }
            childFields.add(new ChildField(3, new Koordinate(koordinate.getX(), koordinate.getY())));


            child.followParent();
        }
        //util.printGrid(26, 20, head, childrean);
    }

    public void followParent() {
        if (parent != null) {
            //printGrid(20,20,head,childrean);
            List<Koordinate> surroundingFields = getSurroundingFields();

            List<ChildField> intersect = intersection(parent.childFields, surroundingFields);
            Collections.sort(intersect);

            if (intersect.contains(koordinate)) return;
            if(intersect.get(0).equals(koordinate))return;


            Koordinate oldKoordinate = new Koordinate(koordinate.getX(), koordinate.getY());
            koordinate = intersect.get(0);
            this.childFields = getChildFields(koordinate, oldKoordinate);

            if (this.child != null) {
                //util.printGrid(26,20,head,childrean);
                //System.out.println(id+" "+koordinate);
                this.child.followParent();
            } else {
                markedFields.add(koordinate);
            }
        }
    }

    private List<ChildField> intersection(List<ChildField> list1, List<Koordinate> list2) {
        List<ChildField> list = new ArrayList<>();

        for (ChildField t : list1) {
            if (list2.contains(t)) {
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

        if (x >= 1) {
            childFields.add(new ChildField(2, new Koordinate(koordinate.getX() - 1, koordinate.getY())));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() - 1, koordinate.getY() + 1)));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() - 1, koordinate.getY() - 1)));
        } else if (x <= -1) {
            childFields.add(new ChildField(2, new Koordinate(koordinate.getX() + 1, koordinate.getY())));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() + 1)));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() - 1)));
        }

        if (y >= 1) {
            childFields.add(new ChildField(2, new Koordinate(koordinate.getX(), koordinate.getY() - 1)));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() - 1)));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() - 1, koordinate.getY() - 1)));
        } else if (y <= -1) {
            childFields.add(new ChildField(2, new Koordinate(koordinate.getX(), koordinate.getY() + 1)));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() + 1, koordinate.getY() + 1)));
            childFields.add(new ChildField(1, new Koordinate(koordinate.getX() - 1, koordinate.getY() + 1)));
        }
        childFields.add(new ChildField(3, new Koordinate(koordinate.getX(), koordinate.getY())));

        return childFields;


    }

    private List<Koordinate> getSurroundingFields() {
        List<Koordinate> surroundingFields = new ArrayList<>();
        surroundingFields.add(new Koordinate(koordinate.getX() + 1, koordinate.getY()));
        surroundingFields.add(new Koordinate(koordinate.getX() - 1, koordinate.getY()));
        surroundingFields.add(new Koordinate(koordinate.getX(), koordinate.getY() + 1));
        surroundingFields.add(new Koordinate(koordinate.getX(), koordinate.getY() - 1));

        surroundingFields.add(new Koordinate(koordinate.getX() + 1, koordinate.getY() + 1));
        surroundingFields.add(new Koordinate(koordinate.getX() - 1, koordinate.getY() - 1));
        surroundingFields.add(new Koordinate(koordinate.getX() + 1, koordinate.getY() - 1));
        surroundingFields.add(new Koordinate(koordinate.getX() - 1, koordinate.getY() + 1));

        surroundingFields.add(new Koordinate(koordinate.getX(), koordinate.getY()));

        return surroundingFields;
    }


    public Koordinate getCoordinate() {
        return koordinate;
    }
}
