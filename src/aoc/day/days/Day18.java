package aoc.day.days;

import aoc.day.Day;
import aoc.day.days.util.Koordinate;
import java.util.*;

public class Day18 extends Day {
    @Override
    public Object part1() {
        List<Koordinate> input = parseInput();

        int sideCount = 0;
        for (Koordinate cube : input) {
            sideCount+=6;

            List<Koordinate> neighbours = cube.getNeighbours();
            for (Koordinate neighbour : neighbours) {
                if (input.contains(neighbour)) {
                    sideCount--;
                }
            }
        }
        return sideCount;
    }

    private List<Koordinate> parseInput() {
        //2,2,2
        List<String> rawInput = getDayInput();
        List<Koordinate> result = new ArrayList<>();
        for (String s : rawInput) {
            String[] split = s.split(",");
            result.add(new Koordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }
        return result;
    }


    @Override
    public Object part2() {
        List<Koordinate> input = parseInput();

        List<Koordinate> flood = new ArrayList<>();
        flood.add(new Koordinate(0,0,0));
        for (int i = 0; i < 80; i++) {
            List<Koordinate> newFlood = new ArrayList<>();
            for (Koordinate cube : flood) {
                List<Koordinate> neighbours = cube.getNeighbours();
                for (Koordinate neighbour : neighbours) {
                    if (!flood.contains(neighbour) && !newFlood.contains(neighbour)) {
                        if (neighbour.x>=-1 && neighbour.y>=-1 && neighbour.z>=-1) {
                            if (!input.contains(neighbour)) {
                                if (neighbour.x>21 || neighbour.y>21 || neighbour.z>21) {
                                }else {
                                    newFlood.add(neighbour);
                                }
                            }
                        }
                    }
                }
            }
            flood.addAll(newFlood);
            System.out.println(i + " " + flood.size());
        }
        System.out.println(flood.size());
        int sideCount = 0;

        for (Koordinate cube : input) {

            List<Koordinate> neighbours = cube.getNeighbours();
            for (Koordinate neighbour : neighbours) {
                if (flood.contains(neighbour)) {
                    sideCount++;
                }
            }
        }
        return sideCount;
    }
}
