package aoc.day.days;

import aoc.day.Day;
import aoc.day.days.day9.Koordinate;

import java.util.*;

public class Day14 extends Day {

    HashSet<Koordinate> usedKoordinates;
    int highestY;

    public Day14() {
        super();
        usedKoordinates = new HashSet<>();
    }
    @Override
    public Object part1() {
        usedKoordinates = new HashSet<>();
        setStone(getDayInput());

        int count = 0;
        while (spawnSand(false)){
            count++;
        }
        System.out.println(count);
        return count;
    }

    @Override
    public Object part2() {
        usedKoordinates = new HashSet<>();
        setStone(getDayInput());
        highestY = highestY();
        int count = 0;
        while (spawnSand(true)){
            count++;
        }
        System.out.println(count);
        return count;
    }

    public void printField(List<Koordinate> stones) {
        int startX = 490;
        int startY = 0;
        int endX = 510;
        int endY = 10;
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                Koordinate koordinate = new Koordinate(x, y);
                if (usedKoordinates.contains(koordinate)) {
                    if (stones.contains(koordinate)) {
                        System.out.print("#");
                    }else{
                        System.out.print("█");
                    }
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
    public boolean spawnSand(boolean isPart2) {
        int x = 500;
        int y = 0;
        if (usedKoordinates.contains(new Koordinate(x, y))) {
            return false;
        }
        while (true){
            if (usedKoordinates.contains(new Koordinate(x, y+1))) {
                if (usedKoordinates.contains(new Koordinate(x-1,y+1))){
                    if(usedKoordinates.contains(new Koordinate(x+1,y+1))){
                        usedKoordinates.add(new Koordinate(x,y));
                        return true;
                    }else {
                        x++;
                        y++;
                    }
                }else {
                    x--;
                    y++;
                }
            }else {
                if (isPart2&& y+1==highestY+2){
                    usedKoordinates.add(new Koordinate(x,y));
                    return true;
                }
                y++;

                if (y > 500){
                    return false;
                }
            }
        }
    }


    public int highestY (){
        int highestY = 0;
        for (Koordinate koordinate : usedKoordinates) {
            if (koordinate.getY() > highestY) {
                highestY = koordinate.getY();
            }
        }
        return highestY;
    }
    public void setStone(List<String> rawInput){
        for (String line: rawInput){
            String[] split = line.split(" -> ");
            Koordinate current = new Koordinate(Integer.parseInt(split[0].split(",")[0]),Integer.parseInt(split[0].split(",")[1]));
            for (int i = 1; i < split.length; i++) {
                Koordinate me = new Koordinate(Integer.parseInt(split[i].split(",")[0]),Integer.parseInt(split[i].split(",")[1]));
                //difference
                int x = me.getX() - current.getX();
                int y = me.getY() - current.getY();
                if (x == 0){
                    if (y > 0){
                        for (int j = 0; j <= y; j++) {
                            usedKoordinates.add(new Koordinate(current.getX(),current.getY()+j));
                        }
                    }else{
                        for (int j = 0; j <= Math.abs(y); j++) {
                            usedKoordinates.add(new Koordinate(current.getX(),current.getY()-j));
                        }
                    }
                }else if (y == 0){
                    if (x > 0){
                        for (int j = 0; j <= x; j++) {
                            usedKoordinates.add(new Koordinate(current.getX()+j,current.getY()));
                        }
                    }else{
                        for (int j = 0; j <= Math.abs(x); j++) {
                            usedKoordinates.add(new Koordinate(current.getX()-j,current.getY()));
                        }
                    }

                }
                current = me;
            }
        }
    }
}
