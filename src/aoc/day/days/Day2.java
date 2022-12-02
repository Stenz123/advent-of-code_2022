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
        List<String> rawInput2 = new ArrayList<>(rawInput);
        return new Solution(getDay(),part1(rawInput),part2(rawInput2) );
    }

    private int part1(List<String> rawInput){
        List<List<String>> input = new ArrayList<>();
        int totalScore = 0;
        for (int i = 0; i < rawInput.size(); i++) {
            input.add(Arrays.asList(rawInput.get(i).split(" ")));
            totalScore += getGameScore(input.get(i));
        }
        return totalScore;
    }

    private int part2(List<String> rawInput){
        List<List<String>> input = new ArrayList<>();
        int totalScore = 0;
        for (int i = 0; i < rawInput.size(); i++) {
            input.add(Arrays.asList(rawInput.get(i).split(" ")));
            totalScore += gamePart2(input.get(i));
        }
        return totalScore;
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
    private int gamePart2(List<String> match){
        switch (match.get(1)){
            case "X":
                switch (match.get(0)){
                    case "A":
                        match.set(1,"C");
                        break;
                    case "B":
                        match.set(1,"A");
                        break;
                    case "C":
                        match.set(1,"B");
                        break;
                }
                break;
            case "Y":
                match.set(1,match.get(0));
                break;
            case "Z":
                switch (match.get(0)){
                    case "A":
                        match.set(1,"B");
                        break;
                    case "B":
                        match.set(1,"C");
                        break;
                    case "C":
                        match.set(1,"A");
                        break;
                }
                break;
            default:
                throw new RuntimeException();
        }
        return getGameScore(match);
    }

}

