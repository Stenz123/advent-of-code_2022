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
            if (compareTo((List<Object>) list.get(i), (List<Object>) list.get(i+1))){
                result += pairCount;
            }
            pairCount++;
        }
        return result;
    }

    public boolean compareTo(List<Object> list1, List<Object> list2){
        //larger list
        for (int i = 0; i < list1.size(); i++) {
            Object o2;
            Object o1 = list1.get(i);
            try {
                o2 = list2.get(i);
            }catch (Exception e) {
                return true;
            }


            if(o1 instanceof Integer && o2 instanceof Integer){
                if((Integer) o1 > (Integer) o2){
                    return false;
                }
            } else if (o1 instanceof List && o2 instanceof List){
                if (((List<?>) o1).isEmpty() && ((List<?>) o2).isEmpty()){
                    return true;
                }
                if(!compareTo((List<Object>) o1, (List<Object>) o2)){
                    return false;
                }
            }else if (o1 instanceof List && o2 instanceof Integer){
                if (((List<?>) o1).isEmpty()){
                    return false;
                }
                List<Object> listO2 = new ArrayList<>();
                for (int j = 0; j < ((List<?>) o1).size(); j++) {
                    listO2.add(o2);
                }
                if(!compareTo((List<Object>) o1, listO2)){
                    return false;
                }
            }
            else if (o1 instanceof Integer && o2 instanceof List) {
                if (((List<?>) o2).isEmpty()){
                    return false;
                }
                if(((List<?>) o2).size()==0){
                    return false;
                }
                List<Object> listO1 = new ArrayList<>();
                for (int j = 0; j < ((List<?>) o2).size(); j++) {
                    listO1.add(o1);
                }
                if(!compareTo(listO1, (List<Object>) o2)){
                    return false;
                }
            }
        }
        return true;
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
