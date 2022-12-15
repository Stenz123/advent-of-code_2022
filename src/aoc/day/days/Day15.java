package aoc.day.days;

import antlr.actions.python.CodeLexer;
import aoc.day.Day;
import aoc.day.days.day12.Board;
import aoc.day.days.day12.IntPair;
import aoc.day.days.day9.Koordinate;
import javassist.compiler.ast.Pair;

import java.util.*;

public class Day15 extends Day {
    @Override
    public Object part1() {
        HashMap<Koordinate,Koordinate> sensorBeaconMap = convertInput();
        PriorityQueue<KoordinatePair> visitedCompresed = new PriorityQueue<>();

        for (Koordinate koordinate : sensorBeaconMap.keySet()) {
            markSignalOfDetector(koordinate, sensorBeaconMap.get(koordinate), visitedCompresed);
            System.out.println("Done with " + koordinate);
        }

        int counter = 0;

        List<KoordinatePair> pairs = new LinkedList<>();
        for (KoordinatePair koordinate : visitedCompresed) {
            if (koordinate.getStart().getY() == 2000000) {
                System.out.println(koordinate);
                pairs.add(koordinate);
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for (KoordinatePair koordinate : pairs) {
            for (int i = koordinate.getEnd().getX(); i <= koordinate.getStart().getX(); i++) {
                set.add(i);
            }
            System.out.println("added " + koordinate);
            System.out.println(set.size());
        }
        counter+= set.size();


        HashSet<Integer> beaconsInLine = new HashSet<>();
        for (Koordinate koordinate : sensorBeaconMap.values()) {
            if (koordinate.getY() == 2000000){
                beaconsInLine.add(koordinate.getX());
            }
        }
        HashSet<Integer> sensorsInLine = new HashSet<>();
        for (Koordinate koordinate : sensorBeaconMap.keySet()) {
            if (koordinate.getY() == 2000000){
                sensorsInLine.add(koordinate.getX());
            }
        }
        counter-=beaconsInLine.size();
        counter-=sensorsInLine.size();

        return counter;
    }


    @Override
    public Object part2() {
        HashMap<Koordinate,Koordinate> sensorBeaconMap = convertInput();
        PriorityQueue<KoordinatePair> visitedCompresed = new PriorityQueue<>();
        for (Koordinate koordinate : sensorBeaconMap.keySet()) {
            markSignalOfDetector(koordinate, sensorBeaconMap.get(koordinate), visitedCompresed);
            System.out.println("Done with " + koordinate);
        }

        //return printMap(visitedCompresed,0,4000000);
        return printMap(visitedCompresed,2766583,2766583);
    }

    private void markSignalOfDetector(Koordinate detector, Koordinate beacon, PriorityQueue<KoordinatePair> visitedCompresed){
        int xDiff = Math.abs(detector.getX() - beacon.getX());
        int yDiff = Math.abs(detector.getY() - beacon.getY());

        Koordinate start = new Koordinate(detector.getX()+xDiff+yDiff,detector.getY());
        Koordinate end = new Koordinate(detector.getX() - (xDiff+yDiff),detector.getY());

        visitedCompresed.add(new KoordinatePair(new Koordinate(start.getX(),start.getY()),new Koordinate(end.getX(),end.getY())));

        int yDistance = 1;
        while (true){
            if (start.getX()== end.getX() && start.getY() == end.getY()){
                break;
            }
            start.setX(start.getX()-1);
            end.setX(end.getX()+1);
            start.setY(start.getY()-yDistance);
            end.setY(end.getY()-yDistance);


            visitedCompresed.add(new KoordinatePair(new Koordinate(start.getX(),start.getY()),new Koordinate(end.getX(),end.getY())));
        }

        start = new Koordinate(detector.getX()+xDiff+yDiff,detector.getY());
        end = new Koordinate(detector.getX() - (xDiff+yDiff),detector.getY());
        while (true){
            if (start.getX()== end.getX() && start.getY() == end.getY()){
                break;
            }
            start.setX(start.getX()-1);
            end.setX(end.getX()+1);
            start.setY(start.getY()+yDistance);
            end.setY(end.getY()+yDistance);

            visitedCompresed.add(new KoordinatePair(new Koordinate(start.getX(),start.getY()),new Koordinate(end.getX(),end.getY())));
        }
    }

    private HashMap<Koordinate,Koordinate> convertInput(){
        List<String> rawInput = getDayInput();
        HashMap<Koordinate,Koordinate> result = new HashMap<>();

        for (String line:rawInput) {
            //Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            String[] split = line.split(" ");
            //remove all ','
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].replace(",","");
                split[i] = split[i].replace(":","");
            }


            Koordinate sensor = new Koordinate(Integer.parseInt(split[2].split("=")[1]),Integer.parseInt(split[3].split("=")[1]));
            Koordinate beacon = new Koordinate(Integer.parseInt(split[8].split("=")[1]),Integer.parseInt(split[9].split("=")[1]));
            result.put(sensor,beacon);
        }
        return result;
    }

    private String printMap(PriorityQueue<KoordinatePair> visitedCompresed, int start, int end){
        while (visitedCompresed.poll().getStart().getY()<start){
            System.out.println(visitedCompresed.peek());
        }
        for (int i = start; i <= end;i++) {
            List<Integer> areas = new ArrayList<>();

            while(true){
                if (visitedCompresed.peek()==null){
                    break;
                }
                if (visitedCompresed.peek().getStart().getY() != i){
                    break;
                }

                KoordinatePair koordinate = visitedCompresed.poll();

                int startx = koordinate.getEnd().getX();
                int endx = koordinate.getStart().getX();
                if (startx<0)startx=0;
                if (endx<0)endx=0;
                if (startx>4000000)startx=4000000;
                if (endx>4000000)endx=4000000;
                areas.add(startx);
                areas.add(endx);

            }
            if (!checkRange(areas)){
                return "x:"+getFirstNotCovered(areas)+" y:"+i;
            }else {
                System.out.println("Checked " + i);
            }
        }

        return "";
    }
    public boolean checkRange(List<Integer> rangeList) {
        // Create a bit array to store which numbers are covered by the range
        BitSet covered = new BitSet(4000001);

        // Iterate through the list of ranges
        for (int i = 0; i < rangeList.size() - 1; i += 2) {
            // Get the start and end values of the current range
            int start = rangeList.get(i);
            int end = rangeList.get(i + 1);

            // Mark all numbers in the current range as covered
            covered.set(start, end + 1);
        }

        // Check if all numbers from 0 to 4000000 are covered
        return covered.nextClearBit(0) == 4000001;
    }
    public int getFirstNotCovered(List<Integer> rangeList) {
        // Create a bit array to store which numbers are covered by the range
        BitSet covered = new BitSet(4000001);

        // Iterate through the list of ranges
        for (int i = 0; i < rangeList.size() - 1; i += 2) {
            // Get the start and end values of the current range
            int start = rangeList.get(i);
            int end = rangeList.get(i + 1);

            // Mark all numbers in the current range as covered
            covered.set(start, end + 1);
        }

        // Return the index of the first not covered value
        return covered.nextClearBit(0);
    }


}
class KoordinatePair implements Comparable<KoordinatePair>{
    private Koordinate start;
    private Koordinate end;

    public KoordinatePair(Koordinate start, Koordinate end) {
        this.start = start;
        this.end = end;
    }

    public Koordinate getStart() {
        return start;
    }

    public void setStart(Koordinate start) {
        this.start = start;
    }

    public Koordinate getEnd() {
        return end;
    }

    public void setEnd(Koordinate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return start+" "+end;
    }

    @Override
    public int compareTo(KoordinatePair o) {
        return Integer.compare(this.start.getY(),o.getStart().getY());
    }
}
