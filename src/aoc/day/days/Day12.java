package aoc.day.days;

import aoc.day.Day;
import aoc.day.days.day12.Board;
import aoc.day.days.day12.IntPair;

import java.util.*;

public class Day12 extends Day {


    @Override
    public Object part1() {
        Board board = convertInput();
        return board.findShortestPath();
    }

    @Override
    public Object part2() {
        return -1;
    }

    private Board convertInput(){
        List<String> rawInput = getDayInput();

        HashMap<IntPair, Character> map = new HashMap<>();

        int x = 0;
        int y = 0;
        for (int i = 0; i < rawInput.size(); i++) {
            String line = rawInput.get(i);
            for (int j = 0; j < line.length(); j++) {
                map.put(new IntPair(x, y), line.charAt(j));
                x++;
            }
            x = 0;
            y++;
        }
        return new Board(map);
    }
}