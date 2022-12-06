package aoc.day.days;

import aoc.day.Day;
import java.util.*;

public class Day6 extends Day {

    @Override
    public Object part1() {
        List<String> rawInput = getDayInput();
        int result = 0;
        for (String line : rawInput) {
            boolean exit = false;
             for(int i = 0; i < line.length()-4 && !exit; i++) {
                String sub = line.substring(i, i+4);
                if(isUnique(sub)) {
                    result  += i+4;
                    exit= true;
                }

             }

        }
        return result;
    }

    private boolean isUnique(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public Object part2() {
        List<String> rawInput = getDayInput();
        int result = 0;
        for (String line : rawInput) {
            boolean exit = false;
            for(int i = 0; i < line.length()-14 && !exit; i++) {
                String sub = line.substring(i, i+14);
                if(isUnique(sub)) {
                    result  += i+14;
                    exit= true;
                }

            }

        }
        return result;
    }

}
