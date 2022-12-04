package aoc.day.days;

import aoc.day.Day;

import java.util.*;


public class Day4 extends Day {
    @Override
    public Object part1() {
        List<List<List<Integer>>> input = getParsedInput();

        int result = 0;
        for (int i = 0; i < input.size();i++){
                if (isCompartmentInCompartment(input.get(i).get(0),input.get(i).get(1))||
                        isCompartmentInCompartment(input.get(i).get(1),input.get(i).get(0))){
                    result++;
                }
        }

        return result;
    }
    @Override
    public Object part2() {
        List<List<List<Integer>>> input = getParsedInput();

        int result = 0;
        for (int i = 0; i < input.size();i++){
            boolean brealLoop = false;
            for(int j = input.get(i).get(0).get(0); (j <= input.get(i).get(0).get(1) && !brealLoop); j++) {
                List<Integer> temp = new ArrayList<>();
                temp.add(j);
                temp.add(j);
                if (isCompartmentInCompartment(temp, input.get(i).get(1))) {
                    result++;
                    brealLoop = true;
                }
            }
        }

        return result;
    }

    private List<List<List<Integer>>> getParsedInput(){
        List<String> rawInput = this.getDayInput();
        List<List<Integer>> b = new ArrayList<>();
        List<List<List<Integer>>> input = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < rawInput.size();i++){
            String[] s = rawInput.get(i).split(",");
            for (int j = 0; j < s.length;j++){
                String[] s1 = s[j].split("-");
                for (int k = 0; k < s1.length;k++){
                    temp.add(Integer.parseInt(s1[k]));
                }
                b.add(temp);
                temp = new ArrayList<>();
            }
            input.add(b);
            b = new ArrayList<>();
        }
        return input;
    }

    private boolean isCompartmentInCompartment(List<Integer> c1, List<Integer> c2){
        if((c1.get(0) >= c2.get(0) && c1.get(0) <= c2.get(1)&& c1.get(1) >= c2.get(0) && c1.get(1) <= c2.get(1))){
            return true;
        }
        return false;
    }
}
