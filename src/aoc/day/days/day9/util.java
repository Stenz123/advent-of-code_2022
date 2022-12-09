package aoc.day.days.day9;

import java.util.List;

import static aoc.day.days.Day9.markedFields;

class util {
    public static void printGrid(int width, int height, Node head, List<Node> tails) {
        for (int i = height; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                if (head.getCoordinate().getX() == j && head.getCoordinate().getY() == i) {
                    System.out.print(" H ");
                } else {
                    boolean isTail = false;
                    for (Node tail : tails) {
                        if (tail.getCoordinate().getX() == j && tail.getCoordinate().getY() == i) {
                            System.out.print(" " + tail.id + " ");
                            isTail = true;
                            break;
                        }
                    }
                    if (!isTail) {
                        boolean found = false;
                        for (Koordinate k : markedFields) {
                            if (k.getX() == j && k.getY() == i) {
                                found = true;
                                System.out.print(" X ");
                                break;
                            }
                        }
                        if (!found) {
                            System.out.print(" . ");
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }



    private void printVisitedGrid() {

        // print grid
        // start at 0,0 (bottom left)
        for (int i = 20; i >= 0; i--) {
            for (int j = 0; j < 25; j++) {
                boolean found = false;
                for (Koordinate k : markedFields) {
                    if (k.getX() == j && k.getY() == i) {
                        found = true;
                        System.out.print("X");
                        break;
                    }
                }
                if (!found) {
                    System.out.print(".");
                }

            }
            System.out.println();
        }
        System.out.println("----------------------------------");
    }}