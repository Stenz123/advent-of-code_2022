package aoc.day.days;

import aoc.day.Day;


import java.util.*;

public class Day10 extends Day{

    @Override
    public Object part1() {
        List<Command> commands = parseInput();

        Number y = new Number(1);

        int solution = 0;
        int cycles = 1;
        int commandIndex = 0;
        do {
            Command command = commands.get(commandIndex);
            if (command.execute(y)!=null){
                commandIndex++;
            }

            cycles++;

            if (cycles ==220 || cycles == 180 || cycles == 140 || cycles == 100 || cycles == 60 || cycles == 20){
                solution += y.value * cycles;
            }
            if (cycles == 220){
                break;
            }
        }while (true);
        return solution;
    }



    private List<Command> parseInput(){
        List<String> rawInput = getDayInput();
        List<Command> commands = new ArrayList<>();
        for (int i = 0; i < rawInput.size(); i++) {
            if (rawInput.get(i).startsWith("noop")){
                commands.add(new Noop());
            }else if (rawInput.get(i).startsWith("addx")) {
                String[] parts = rawInput.get(i).split(" ");
                commands.add(new Addx(Integer.parseInt(parts[1])));
            }
        }
        return commands;
    }


    @Override
    public Object part2() {
        return -1;
    }
}

class Addx extends Command{
    public Addx(int x) {
        super(2,x);
    }
}
class Noop extends Command{
    public Noop() {
        super(1);
    }
}

class Command{
    private int time;
    private int commandValue;

    public Command(int time) {
        this.time = time;
    }

    public Command(int time, int value) {
        this.time = time;
        this.commandValue = value;
    }

    public Number execute(Number y) {
        time--;
        if (time <= 0){
            y.value += this.commandValue;
            return y;
        }
        return null;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getValue() {
        return commandValue;
    }

    public void setValue(int value) {
        this.commandValue = value;
    }
}
class Number{
    public int value;
    public Number(int value) {
        this.value = value;
    }
}