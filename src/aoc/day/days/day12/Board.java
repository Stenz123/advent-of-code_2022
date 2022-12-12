package aoc.day.days.day12;

import javassist.compiler.ast.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    private HashMap<IntPair, Character> map;

    private IntPair start;
    private IntPair goal;

    public Board(HashMap<IntPair, Character> map) {
        this.map = map;

        for (IntPair k : map.keySet()) {
            char c = map.get(k);
            if(c=='S'){
                start = k;
            }
            if (c == 'E') {
                goal = k;
            }
            if (start != null && goal != null) {
                return;
            }
        }
    }

    public HashMap<IntPair, Character> getMap() {
        return map;
    }

    public void setMap(HashMap<IntPair, Character> map) {
        this.map = map;
    }

    public char getFromCoordinates(int x, int y) {
        char c;
        try {
            c = map.get(new IntPair(x, y));
        } catch (Exception e) {
            return ' ';
        }
        return c;
    }

    public IntPair getStart() {
        return start;
    }

    public void setStart(IntPair start) {
        this.start = start;
    }

    public IntPair getGoal() {
        return goal;
    }

    public void setGoal(IntPair goal) {
        this.goal = goal;
    }
    public boolean canVisit(int x, int y, char c){
        char c1= getFromCoordinates(x, y);

        if(c1==' ')return false;
        if (c == 'z' && c1 == 'E')return true;
        if (c1 == 'S')return false;
        if (c=='S')return true;
        c++;
        return c1 <= c;

    }

    public int findShortestPath(){
        List<IntPair> visited = new ArrayList<>();
        List<IntPair> toVisit = new ArrayList<>();
        toVisit.add(start);
        int steps = 0;
        while(!toVisit.isEmpty()){
            List<IntPair> newToVisit = new ArrayList<>();
            for (IntPair pair : toVisit) {
                if(pair.equals(goal)){
                    return steps;
                }
                if(visited.contains(pair)){
                    continue;
                }
                visited.add(pair);
                char c = getFromCoordinates(pair.x, pair.y);
                if(canVisit(pair.x, pair.y+1, c)){
                    newToVisit.add(new IntPair(pair.x, pair.y+1));
                }
                if(canVisit(pair.x, pair.y-1, c)){
                    newToVisit.add(new IntPair(pair.x, pair.y-1));
                }
                if(canVisit(pair.x+1, pair.y, c)){
                    newToVisit.add(new IntPair(pair.x+1, pair.y));
                }
                if(canVisit(pair.x-1, pair.y, c)){
                    newToVisit.add(new IntPair(pair.x-1, pair.y));
                }
            }
            toVisit = newToVisit;
            steps++;
        }
        return steps;
    }

}


