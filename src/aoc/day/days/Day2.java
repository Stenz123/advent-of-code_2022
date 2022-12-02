package aoc.day.days;

import aoc.day.Solution;
import aoc.day.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 extends Day {
    public Day2(){
        super();
    }

    @Override
    public Solution solve() {
        List<String> rawInput = this.getDayInput();
        List<List<String>> input = new ArrayList<>();
        int totalScore = 0;
        for (int i = 0; i < rawInput.size(); i++) {
            input.add(Arrays.asList(rawInput.get(i).split(" ")));
            totalScore += getGameScore(input.get(i));
        }
        return new Solution(getDay(),totalScore);
    }

    private int getGameScore(List<String> match){
        convertXYZtoABC(match);
        int score = 0;
        score+=((int)match.get(1).charAt(0))-64;
        score+=getGameOutcome(match);
        return score;

    }
    private int getGameOutcome(List<String> match){
        if(match.get(0).equals(match.get(1))){
            return 3;
        }else if(match.get(0).equals("A") && match.get(1).equals("B")){
            return 6;
        }else if(match.get(0).equals("A") && match.get(1).equals("C")){
            return 0;
        }else if(match.get(0).equals("B") && match.get(1).equals("A")){
            return 0;
        }else if(match.get(0).equals("B") && match.get(1).equals("C")) {
            return 6;
        }else if(match.get(0).equals("C") && match.get(1).equals("A")){
            return 6;
        }else if(match.get(0).equals("C") && match.get(1).equals("B")){
            return 0;
        }
        throw new RuntimeException("game outcome could not be converted");
    }
    private void convertXYZtoABC(List<String> match){
        switch (match.get(1)){
            case "X":
                match.set(1, "A");
                break;
            case "Y":
                match.set(1, "B");
                break;
            case "Z":
                match.set(1, "C");
                break;
        }
    }

}

