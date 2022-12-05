package aoc.day.days;

import aoc.day.Day;

import java.util.ArrayList;
import java.util.*;

public class Day5 extends Day {

    Stack<Character> stack1;
    Stack<Character> stack2;
    Stack<Character> stack3;
    Stack<Character> stack4;
    Stack<Character> stack5;
    Stack<Character> stack6;
    Stack<Character> stack7;
    Stack<Character> stack8;
    Stack<Character> stack9;

    List<Stack<Character>> stacks = new ArrayList<>();

    private void initStacks(){
        stacks = new ArrayList<>();

        stack1 = new Stack<>();
        stack2 = new Stack<>();
        stack3 = new Stack<>();
        stack4 = new Stack<>();
        stack5 = new Stack<>();
        stack6 = new Stack<>();
        stack7 = new Stack<>();
        stack8 = new Stack<>();
        stack9 = new Stack<>();
        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        stacks.add(stack4);
        stacks.add(stack5);
        stacks.add(stack6);
        stacks.add(stack7);
        stacks.add(stack8);
        stacks.add(stack9);

        List<String> input = getDayInput();
        for (int i = 7; i >= 0; i--) {
            int k = 0;
            for (int j= 1; j <= 33; j+=4) {
                char temp = input.get(i).charAt(j);
                if (temp != ' '){
                    stacks.get(k).add(temp);
                }
                k++;
            }
        }
    }

    @Override
    public Object part1() {
        initStacks();
        List<List<Integer>> input = parseInput();

        for (int i = 0; i < input.size();i++){
            for (int j = 0; j < input.get(i).get(0);j++){
                char temp = stacks.get(input.get(i).get(1)-1).pop();
                stacks.get(input.get(i).get(2)-1).push(temp);
            }

        }

        String result = "";
        for (int i = 0; i < stacks.size();i++){
            result += stacks.get(i).peek();
        }
        return result;
    }

    @Override
    public Object part2() {
        List<List<Integer>> input = parseInput();

        for (int i = 0; i < input.size();i++){
            List<Character> temp = new ArrayList<>();
            for (int j = 0; j < input.get(i).get(0);j++){
                temp.add(stacks.get(input.get(i).get(1)-1).pop());
            }
            Collections.reverse(temp);
            for (int j = 0; j < input.get(i).get(0);j++){
                stacks.get(input.get(i).get(2)-1).push(temp.get(j));
            }

        }

        String result = "";
        for (int i = 0; i < stacks.size();i++){
            result += stacks.get(i).peek();
        }
        return result;
    }

    private List<List<Integer>> parseInput(){
        initStacks();
        List<String> rawInput = this.getDayInput();

        List<List<Integer>> result = new ArrayList<>();
        //move 1 from 2 to 1
        for (int i = 10; i < rawInput.size();i++){
            String[] s = rawInput.get(i).split(" ");
            List<Integer> temp = new ArrayList<>();
            temp.add(Integer.parseInt(s[1]));
            temp.add(Integer.parseInt(s[3]));
            temp.add(Integer.parseInt(s[5]));
            result.add(temp);

        }
        return result;
    }

}
