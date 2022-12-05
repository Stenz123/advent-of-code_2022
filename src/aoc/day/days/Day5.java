package aoc.day.days;

import aoc.day.Day;

import java.util.ArrayList;
import java.util.*;

public class Day5 extends Day {

    Stack<Character> stack1 = new Stack<>();
    Stack<Character> stack2 = new Stack<>();
    Stack<Character> stack3 = new Stack<>();
    Stack<Character> stack4 = new Stack<>();
    Stack<Character> stack5 = new Stack<>();
    Stack<Character> stack6 = new Stack<>();
    Stack<Character> stack7 = new Stack<>();
    Stack<Character> stack8 = new Stack<>();
    Stack<Character> stack9 = new Stack<>();

    List<Stack<Character>> stacks = new ArrayList<>();
    public Day5(){

        stack1.push('B');
        stack1.push('V');
        stack1.push('S');
        stack1.push('N');
        stack1.push('T');
        stack1.push('C');
        stack1.push('H');
        stack1.push('Q');

        stack2.push('W');
        stack2.push('D');
        stack2.push('B');
        stack2.push('G');

        stack3.push('F');
        stack3.push('W');
        stack3.push('R');
        stack3.push('T');
        stack3.push('S');
        stack3.push('Q');
        stack3.push('B');

        stack4.push('L');
        stack4.push('G');
        stack4.push('W');
        stack4.push('S');
        stack4.push('Z');
        stack4.push('J');
        stack4.push('D');
        stack4.push('N');

        stack5.push('M');
        stack5.push('P');
        stack5.push('D');
        stack5.push('V');
        stack5.push('F');

        stack6.push('F');
        stack6.push('W');
        stack6.push('J');

        stack7.push('L');
        stack7.push('N');
        stack7.push('Q');
        stack7.push('B');
        stack7.push('J');
        stack7.push('V');

        stack8.push('G');
        stack8.push('T');
        stack8.push('R');
        stack8.push('C');
        stack8.push('J');
        stack8.push('Q');
        stack8.push('S');
        stack8.push('N');

        stack9.push('J');
        stack9.push('S');
        stack9.push('Q');
        stack9.push('C');
        stack9.push('W');
        stack9.push('D');
        stack9.push('M');

        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        stacks.add(stack4);
        stacks.add(stack5);
        stacks.add(stack6);
        stacks.add(stack7);
        stacks.add(stack8);
        stacks.add(stack9);

    }

    @Override
    public Object part1() {
        /*List<List<Integer>> input = parseInput();

        for (int i = 0; i < input.size();i++){
            for (int j = 0; j < input.get(i).get(0);j++){
                char temp = stacks.get(input.get(i).get(1)-1).pop();
                stacks.get(input.get(i).get(2)-1).push(temp);
            }

        }

        String result = "";
        for (int i = 0; i < stacks.size();i++){
            result += stacks.get(i).peek();
        }*/
        return -1;
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
        List<String> rawInput = this.getDayInput();

        List<List<Integer>> result = new ArrayList<>();
        //move 1 from 2 to 1
        for (int i = 0; i < rawInput.size();i++){
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
