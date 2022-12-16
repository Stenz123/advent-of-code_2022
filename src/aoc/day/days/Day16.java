package aoc.day.days;

import aoc.day.Day;
import aoc.day.days.day16.Valve;

import java.util.*;

public class Day16 extends Day {
    Set<Integer[]> possible = new HashSet<>();
    @Override
    public Object part1() {
        List<Valve> valves = parseInput();
        List<Integer> nonZeroValves = new LinkedList<>() {
        };
        for (int i = 0; i < valves.size(); i++) {
            Valve valve = valves.get(i);
            if (valve.getValue() != 0) {
                nonZeroValves.add(i);
            }
        }
        Collections.sort(nonZeroValves, (o1, o2) -> {
            int v1 = valves.get(o1).getValue();
            int v2 = valves.get(o2).getValue();
            return Integer.compare(v1, v2)*-1;
        });

        calculateDistance(valves.get(nonZeroValves.get(0)),valves.get(nonZeroValves.get(1)));

        permutations(new HashSet<>(nonZeroValves),new Stack<Integer>(), nonZeroValves.size());
        System.out.println();


        return -1;
    }

    @Override
    public Object part2() {
        return -1;
    }


    //TODO: open all pipes in order
    private int openAllPipes(List<Valve> order){return 0;}
    private int calculateDistance(Valve start, Valve end) {

    }

    private List<Valve> parseInput() {
        List<Valve> valves = new ArrayList<>();
        List<String> rawInput = getDayInput();

        for (String line: rawInput) {
            String[] split = line.split(" ");
            String name = split[1];
            int value = Integer.parseInt(split[4].substring(5, split[4].length() - 1));
            valves.add(new Valve(name, value));
        }

        for (int x = 0; x < rawInput.size(); x++) {
            String[] split = rawInput.get(x).split(" ");
            for (int i = 9; i < split.length; i++) {

                String name = null;
                if (i==split.length-1) {
                    name = split[i].substring(0, split[i].length());
                }else {
                    name = split[i].substring(0, split[i].length() - 1);
                }
                int index = -1;
                for (int j = 0; j < valves.size(); j++) {
                    if (valves.get(j).getName().equals(name)) {
                        index = j;
                        break;
                    }
                }
                if (index == -1) {
                    valves.get(x).addLeadsTo(valves.get(index));
                }
                valves.get(x).addLeadsTo(valves.get(index));
            }

        }
        return valves;

    }
    public void permutations(Set<Integer> items, Stack<Integer> permutation, int size) {

        /* permutation stack has become equal to size that we require */
        if(permutation.size() == size) {
            /* print the permutation */
            System.out.println(permutation);

        }

        /* items available for permutation */
        Integer[] availableItems = items.toArray(new Integer[0]);
        for(Integer i : availableItems) {
            /* add current item */
            permutation.push(i);

            /* remove item from available item set */
            items.remove(i);

            /* pass it on for next permutation */
            permutations(items, permutation, size);

            /* pop and put the removed item back */
            items.add(permutation.pop());
        }
    }

}
