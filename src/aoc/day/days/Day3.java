package aoc.day.days;

import aoc.day.Day;

import java.util.*;

public class Day3 extends Day {
    @Override
    public Object part1() {
        List<String> rawInput = this.getDayInput();

        List<Character> overlap = new ArrayList<>();
        for (String line:rawInput) {
            String c1 = line.substring(0,line.length()/2);
            String c2 = line.substring(line.length()/2,line.length());
            List<Character> compartmen1 = new ArrayList<>();
            List<Character> compartmen2 = new ArrayList<>();
            for (int i = 0;i<c1.length();i++) {
                compartmen1.add(c1.charAt(i));
                compartmen2.add(c2.charAt(i));
            }
            compartmen1.retainAll(compartmen2);
            overlap.add(compartmen1.get(0));
        }
        int result = 0;
        for (char o:overlap) {
            result+=numberToValue(o);
        }

        return result;
    }

    private int numberToValue(char c){
        int i = (int)c;
        if (Character.isLowerCase(c)){
            i-=96;
        }else {
            i-=38;
        }
        return i;
    }

    @Override
    public Object part2() {
        List<String> rawInput = this.getDayInput();

        List<Character> overlap = new ArrayList<>();
        for (int i = 0; i<rawInput.toArray().length; i+=3) {
            List<Character> compartmen1 = new ArrayList<>();
            List<Character> compartmen2 = new ArrayList<>();
            List<Character> compartmen3 = new ArrayList<>();
            for (int j = 0; j<rawInput.get(i).length();j++)compartmen1.add(rawInput.get(i).charAt(j));
            for (int j = 0; j<rawInput.get(i+1).length();j++)compartmen2.add(rawInput.get(i+1).charAt(j));
            for (int j = 0; j<rawInput.get(i+2).length();j++)compartmen3.add(rawInput.get(i+2).charAt(j));

            compartmen1.retainAll(compartmen2);
            compartmen1.retainAll(compartmen3);

            overlap.add(compartmen1.get(0));
        }
        int result = 0;
        for (char o:overlap) {
            result+=numberToValue(o);
        }

        return result;
    }
}
