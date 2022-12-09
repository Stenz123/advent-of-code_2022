package aoc.day.days;

import java.util.*;

import aoc.day.Day;
import aoc.day.days.day9.Koordinate;
import aoc.day.days.day9.Node;

public class Day9 extends Day {
    public static HashSet<Koordinate> markedFields = new HashSet<>();

    @Override
    public Object part1() {
        Node head = new Node(0);

        List<String> rawInput = getDayInput();
        List<Character> dirctions = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        for (int i = 0; i < rawInput.size(); i++) {
            String[] parts = rawInput.get(i).split(" ");
            dirctions.add(parts[0].charAt(0));
            distance.add(Integer.parseInt(parts[1]));
        }
        for (int i = 0; i < dirctions.size(); i++) {
            head.move(distance.get(i),dirctions.get(i));
        }
        return markedFields.size();
    }


    @Override
    public Object part2() {
        return -1;
    }
}
