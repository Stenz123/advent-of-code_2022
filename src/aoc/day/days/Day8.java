package aoc.day.days;

import java.util.*;
import aoc.day.Day;
import aoc.day.days.day8.Tree;

public class Day8 extends Day {

    public List<List<Tree>> trees = new ArrayList<>();
    public Day8() {
        List<String> rawInput = getDayInput();

        for (int j = 0; j < rawInput.size(); j++) {
            String line = rawInput.get(j);
            List<Tree> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                int height =  Integer.parseInt(String.valueOf(line.charAt(i)));
                row.add(new Tree(height, j, i));
            }
            trees.add(row);
        }
    }

    @Override
    public Object part1() {
    int count = 0;
        checkFromDirection(0);
        checkFromDirection(1);
        checkFromDirection(2);
        checkFromDirection(3);

        for (List<Tree> row : trees) {
            for (Tree tree : row) {
                if (tree.getIsVisible() == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    private void checkFromDirection(int direction) {
        if (direction == 0) {
            // check left

            for (int i = 0; i < trees.size(); i++) {
                int lastHeight = -1;
                for (int j = 0; j < trees.get(i).size(); j++) {
                    if (trees.get(i).get(j).getHeigth() > lastHeight) {
                        lastHeight = trees.get(i).get(j).getHeigth();
                        trees.get(i).get(j).setIsVisible(1);
                    }
                }
            }
        } else if (direction == 1) {
            // check right
            for (int i = 0; i < trees.size(); i++) {
                int lastHeight = -1;
                for (int j = trees.get(i).size()-1; j >= 0; j--) {
                    if (trees.get(i).get(j).getHeigth() > lastHeight) {
                        lastHeight = trees.get(i).get(j).getHeigth();
                        trees.get(i).get(j).setIsVisible(1);
                    }
                }
            }
        } else if (direction == 2) {
            // check up
            //for each collums
            for (int i = 0; i < trees.get(0).size(); i++) {
                int lastHeight = -1;
                //for each rows
                for (int j = 0; j < trees.size(); j++) {
                    if (trees.get(j).get(i).getHeigth() > lastHeight) {
                        lastHeight = trees.get(j).get(i).getHeigth();
                        trees.get(j).get(i).setIsVisible(1);
                    }
                }
            }
        } else if (direction == 3) {
            // check
            //for each collumn revers
            for (int i = trees.get(0).size()-1; i >= 0; i--) {
                int lastHeight = -1;
                //for each rows
                for (int j = trees.size()-1; j >= 0; j--) {
                    if (trees.get(j).get(i).getHeigth() > lastHeight) {
                        lastHeight = trees.get(j).get(i).getHeigth();
                        trees.get(j).get(i).setIsVisible(1);
                    }
                }
            }
        }
    }



    @Override
    public Object part2() {
        int highest = 0;
        for (int i = 0; i < trees.size(); i++) {
            for (int j = 0; j < trees.get(i).size(); j++) {
                if (checkFromDirectionFromSpecificTree(i,j) > highest) {
                    highest = checkFromDirectionFromSpecificTree(i,j);
                }
            }
        }
        return highest;
    }

    private int checkFromDirectionFromSpecificTree(int x, int y) {
        // check left
        int count = 1;

        int count2 = 0;
        int lastHeight = trees.get(y).get(x).getHeigth();
        for (int i = x+1; i < trees.get(x).size(); i++) {
            if (trees.get(y).get(i).getHeigth() >= lastHeight) {
                count2++;
                break;
            }else {
                count2++;
            }
        }
        count=sumCount(count2, count);
        count2 = 0;
        // check right
        for (int i = x-1; i >= 0; i--) {
            if (trees.get(y).get(i).getHeigth() >= lastHeight) {
                count2++;
                break;
            }else {
                count2++;
            }
        }

        // check up
        count=sumCount(count2, count);
        count2 = 0;
        for (int i = y-1; i >= 0; i--) {
            if (trees.get(i).get(x).getHeigth() >= lastHeight) {
                count2++;
                break;
            }else {
                count2++;
            }
        }

        // check
        //for each collumn revers
        count=sumCount(count2, count);
        count2 = 0;
        for (int i = y+1; i < trees.size(); i++) {
            if (trees.get(i).get(x).getHeigth() >= lastHeight) {
                count2++;
                break;
            }else {
                count2++;
            }
        }
        count=sumCount(count2, count);
        return count;
    }
    private int sumCount(int count, int count2) {
        if (count == 0) {
            return count2;
        } else {
            return count * count2;
        }
    }

}