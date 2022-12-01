package aoc.day.days;

import aoc.day.*;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Day1 extends Day {
    public Day1(){
        super();
    }

    @Override
    public Solution solve() {
        List<String> rawInput = this.getDayInput();

        List<List<Integer>> elves = new ArrayList<>();

        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < rawInput.size(); i++) {
            if (rawInput.get(i).equals("")) {
                elves.add(items);
                items = new ArrayList<>();
            } else {
                items.add(Integer.parseInt(rawInput.get(i)));
            }
        }

        List<Integer> sums = new ArrayList<>();

        for (int i = 0; i < elves.size(); i++) {
            int sum = 0;
            for (int item : elves.get(i)) {
                sum += item;
            }
            sums.add(sum);
        }
        Collections.sort(sums, Collections.reverseOrder());

        return new Solution(this.getDay(),sums.get(0),sums.get(0)+sums.get(1)+sums.get(2));
    }
}
