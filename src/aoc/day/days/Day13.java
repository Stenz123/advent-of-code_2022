package aoc.day.days;

import aoc.day.Day;

import java.util.ArrayList;
import java.util.List;

public class Day13 extends Day {
    @Override
    public Object part1() {
        List<List<Object>> list = parseInput();

        int result = 0;
        int pairCount = 1;
        for (int i = 0; i < list.size(); i+=2) {
            if (compareTo(list.get(i), list.get(i+1))==-1) {
                result += pairCount;
            }
            pairCount++;
        }
        return result;
    }

    public int compareTo(List<Object> left, List<Object> right) {

        if (left.size() == right.size() && left.equals(right)) {
            return 0;
        }

        int minLength = Math.min(left.size(), right.size());
        for (int i = 0; i < minLength; i++) {
            Object leftValue = left.get(i);
            Object rightValue = right.get(i);

            // If both values are integers, compare them directly
            if (leftValue instanceof Integer && rightValue instanceof Integer) {
                int leftInt = (Integer) leftValue;
                int rightInt = (Integer) rightValue;
                if (leftInt < rightInt) {
                    return -1;
                } else if (leftInt > rightInt) {
                    return 1;
                }
            }

            // If both values are lists, compare them recursively
            if (leftValue instanceof List && rightValue instanceof List) {
                int result = compareTo((List<Object>) leftValue, (List<Object>) rightValue);
                if (result != 0) {
                    return result;
                }
            }

            // If exactly one value is an integer, convert it to a list and retry the comparison
            if (leftValue instanceof Integer && rightValue instanceof List) {
                List<Object> leftList = new ArrayList<Object>();
                leftList.add(leftValue);
                int result = compareTo(leftList, (List<Object>) rightValue);
                if (result != 0) {
                    return result;
                }
            }
            if (leftValue instanceof List && rightValue instanceof Integer) {
                List<Object> rightList = new ArrayList<Object>();
                rightList.add(rightValue);
                int result = compareTo((List<Object>) leftValue, rightList);
                if (result != 0) {
                    return result;
                }
            }
        }
        if (left.size() < right.size()) {
            return -1;
        } else if (left.size() > right.size()) {
            return 1;
        }else {
            return 0;
        }
    }

    public List<List<Object>> parseInput(){
        List<String> rawInput = getDayInput();
        List<List<Object>> list = new ArrayList<>();
        for (int i = 0; i < rawInput.size(); i++) {
            if (!rawInput.get(i).equals("")){
                list.add(parseList(rawInput.get(i)));
            }
        }

        return list;
    }
    public int findClosingBracket(int startIndex, String rawInput){
        int j = startIndex;

        int closedBrackets = 0;
        do{
            if (rawInput.charAt(j) == '[') {
                closedBrackets++;
            } else if (rawInput.charAt(j) == ']') {
                closedBrackets--;
            }
            j++;
        }while (closedBrackets != 0);
        return j;
    }

    public List<Object> parseList(String input) {
        //parse input
        //[[1],[2,3,4]]
        if (input.charAt(0) == '[' && input.charAt(input.length() - 1) == ']') {
            input = input.substring(1, input.length() - 1);
        }
        String numberChars = "";

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '[') {
                int endIndex = findClosingBracket(i, input);
                String substring = input.substring(i, endIndex);
                list.add(parseList(substring));
                i = endIndex;
            } else if (c == ']') {
                //end of list
                System.out.println("ERROR end of list should not happen");
            } else if (c == ',') {
                //end of number
                if (!numberChars.equals("")) {
                    list.add(Integer.parseInt(numberChars));
                    numberChars = "";
                }
                numberChars="";
            } else {
                //number
                numberChars += c;

            }
        }
        if (!numberChars.equals("")) {
            list.add(Integer.parseInt(numberChars));
        }
        return list;


    }


    @Override
    public Object part2() {
        return -1;
    }
}
