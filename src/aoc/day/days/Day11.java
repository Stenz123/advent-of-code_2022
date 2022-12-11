package aoc.day.days;
import java.math.BigInteger;

//import Apint


import aoc.day.Day;
import java.util.*;

public class Day11 extends Day {

    @Override
    public Object part1() {
        return -1;
    }

    @Override
    public Object part2() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(new BigInteger("99")));
        items.add(new Item(new BigInteger("67")));
        items.add(new Item(new BigInteger("92")));
        items.add(new Item(new BigInteger("61")));
        items.add(new Item(new BigInteger("83")));
        items.add(new Item(new BigInteger("64")));
        items.add(new Item(new BigInteger("98")));
        Operation op = new Operation(2,17);
        Monkey monkey0 = new Monkey(0,items,op, 3);

        items = new ArrayList<>();
        items.add(new Item(new BigInteger("78")));
        items.add(new Item(new BigInteger("74")));
        items.add(new Item(new BigInteger("88")));
        items.add(new Item(new BigInteger("89")));
        items.add(new Item(new BigInteger("50")));
        op = new Operation(2,11);
        Monkey monkey1 = new Monkey(1,items,op, 5);

        items = new ArrayList<>();
        items.add(new Item(new BigInteger("98")));
        items.add(new Item(new BigInteger("91")));
        op = new Operation(0,4);
        Monkey monkey2 = new Monkey(2,items,op, 2);

        //59, 72, 94, 91, 79, 88, 94, 51
        items = new ArrayList<>();
        items.add(new Item(new BigInteger("59")));
        items.add(new Item(new BigInteger("72")));
        items.add(new Item(new BigInteger("94")));
        items.add(new Item(new BigInteger("91")));
        items.add(new Item(new BigInteger("79")));
        items.add(new Item(new BigInteger("88")));
        items.add(new Item(new BigInteger("94")));
        items.add(new Item(new BigInteger("51")));
        op = new Operation(2,-1);
        Monkey monkey3 = new Monkey(3,items,op, 13);

        items = new ArrayList<>();
        items.add(new Item(new BigInteger("95")));
        items.add(new Item(new BigInteger("72")));
        items.add(new Item(new BigInteger("78")));
        op = new Operation(0,7);
        Monkey monkey4 = new Monkey(4,items,op, 11);

        items = new ArrayList<>();
        items.add(new Item(new BigInteger("76")));
        op = new Operation(0,8);
        Monkey monkey5 = new Monkey(5,items,op, 17);

        items = new ArrayList<>();
        items.add(new Item(new BigInteger("69")));
        items.add(new Item(new BigInteger("60")));
        items.add(new Item(new BigInteger("53")));
        items.add(new Item(new BigInteger("89")));
        items.add(new Item(new BigInteger("71")));
        items.add(new Item(new BigInteger("88")));
        op = new Operation(0,5);
        Monkey monkey6 = new Monkey(6,items,op, 19);

        items = new ArrayList<>();
        items.add(new Item(new BigInteger("72")));
        items.add(new Item(new BigInteger("54")));
        items.add(new Item(new BigInteger("63")));
        items.add(new Item(new BigInteger("80")));
        op = new Operation(0,3);
        Monkey monkey7 = new Monkey(7,items,op, 7);

        monkey0.setPreviousMonkey(monkey4);
        monkey0.setNextMonkey(monkey2);
        monkey1.setPreviousMonkey(monkey3);
        monkey1.setNextMonkey(monkey5);
        monkey2.setPreviousMonkey(monkey6);
        monkey2.setNextMonkey(monkey4);
        monkey3.setPreviousMonkey(monkey0);
        monkey3.setNextMonkey(monkey5);
        monkey4.setPreviousMonkey(monkey7);
        monkey4.setNextMonkey(monkey6);
        monkey5.setPreviousMonkey(monkey0);
        monkey5.setNextMonkey(monkey2);
        monkey6.setPreviousMonkey(monkey7);
        monkey6.setNextMonkey(monkey1);
        monkey7.setPreviousMonkey(monkey1);
        monkey7.setNextMonkey(monkey3);

        for (long i = 0; i < 10000; i++) {
            monkey0.moveItems(i);
            monkey1.moveItems(i);
            monkey2.moveItems(i);
            monkey3.moveItems(i);
            monkey4.moveItems(i);
            monkey5.moveItems(i);
            monkey6.moveItems(i);
            monkey7.moveItems(i);
            System.out.println("Iteration " + i);
        }
        List<Long> itemsInspected = new ArrayList<>();
        itemsInspected.add(monkey0.getItemsInspected());
        itemsInspected.add(monkey1.getItemsInspected());
        itemsInspected.add(monkey2.getItemsInspected());
        itemsInspected.add(monkey3.getItemsInspected());
        itemsInspected.add(monkey4.getItemsInspected());
        itemsInspected.add(monkey5.getItemsInspected());
        itemsInspected.add(monkey6.getItemsInspected());
        itemsInspected.add(monkey7.getItemsInspected());

        //find to largest number of items inspected

        /*

        //Monkey 0:
        //Starting items: 79, 98
        //Operation: new = old * 19
        //Test: divisible by 23
        //If true: throw to monkey 2
        //If false: throw to monkey 3
        List<Item> items = new ArrayList<>();
        items.add(new Item(new BigInteger(String.valueOf(79))));
        items.add(new Item(new BigInteger(String.valueOf(98))));
        Operation op = new Operation(2,19);
        Monkey monkey0 = new Monkey(0,items,op, 23);

        //Monkey 1:
        //  Starting items: 54, 65, 75, 74
        //  Operation: new = old + 6
        //  Test: divisible by 19
        //    If true: throw to monkey 2
        //    If false: throw to monkey 0
        items = new ArrayList<>();
        items.add(new Item(new BigInteger("54")));
        items.add(new Item(new BigInteger("65")));
        items.add(new Item(new BigInteger("75")));
        items.add(new Item(new BigInteger("74")));
        op = new Operation(0,6);
        Monkey monkey1 = new Monkey(1,items,op, 19);

        //Monkey 2:
        //  Starting items: 79, 60, 97
        //  Operation: new = old * old
        //  Test: divisible by 13
        //    If true: throw to monkey 1
        //    If false: throw to monkey 3
        items = new ArrayList<>();
        items.add(new Item(new BigInteger("79")));
        items.add(new Item(new BigInteger("60")));
        items.add(new Item(new BigInteger("97")));
        op = new Operation(2,-1);
        Monkey monkey2 = new Monkey(2,items,op, 13);

        //Monkey 3:
        //  Starting items: 74
        //  Operation: new = old + 3
        //  Test: divisible by 17
        //    If true: throw to monkey 0
        //    If false: throw to monkey 1
        items = new ArrayList<>();
        items.add(new Item(new BigInteger("74")));
        op = new Operation(0,3);
        Monkey monkey3 = new Monkey(3,items,op, 17);

        monkey0.setPreviousMonkey(monkey2);
        monkey0.setNextMonkey(monkey3);
        monkey1.setPreviousMonkey(monkey2);
        monkey1.setNextMonkey(monkey0);
        monkey2.setPreviousMonkey(monkey1);
        monkey2.setNextMonkey(monkey3);
        monkey3.setPreviousMonkey(monkey0);
        monkey3.setNextMonkey(monkey1);

        for (long i = 0; i < 10000; i++) {
            monkey0.moveItems(i);
            monkey1.moveItems(i);
            monkey2.moveItems(i);
            monkey3.moveItems(i);
            System.out.println("i: " + i);
            if (i==999) {
                System.out.println();
            }
        }
        List<Long> itemsInspected = new ArrayList<>();
        itemsInspected.add(monkey0.getItemsInspected());
        itemsInspected.add(monkey1.getItemsInspected());
        itemsInspected.add(monkey2.getItemsInspected());
        itemsInspected.add(monkey3.getItemsInspected());
     */

        //find largest two numbers
        long largest = 0;
        long secondLargest = 0;
        for (Long aLong : itemsInspected) {
            if (aLong > largest) {
                secondLargest = largest;
                largest = aLong;
            } else if (aLong > secondLargest) {
                secondLargest = aLong;
            }
        }
        System.out.println("Largest: " + largest);
        System.out.println("Second largest: " + secondLargest);
        System.out.println("Product: " + largest * secondLargest);
        return largest * secondLargest;
    }
}

class Monkey{
    long id;
    private final List<Item> items;
    private final Operation operation;
    private final long divisionValue;

    private Monkey nextMonkey;

    private Monkey previousMonkey;

    private long itemCounter;
    public long getItemsInspected(){
        return itemCounter;
    }

    public Monkey(long id,List<Item> items, Operation operation, long divisionValue) {
        this.id = id;
        this.items = items;
        this.operation = operation;
        this.divisionValue = divisionValue;
        itemCounter = 0;
    }

    public void moveItems(long round){
        List<Item> itemsToMove = new ArrayList<>();
        for (Item value : items) {
            //if (items.get(i).getRound() == round){
            itemsToMove.add(value);
            itemCounter++;
            //}
        }
        for (Item item : itemsToMove) {
            item.setWorryLevel(operation.apply(item.getWorryLevel()).mod(BigInteger.valueOf(9699690)));
            //item.setWorryLevel((long) Math.floor(item.getWorryLevel() / 3));

            item.setRound(round + 1);


            if (item.getWorryLevel().mod(BigInteger.valueOf(divisionValue)).equals(BigInteger.ZERO)){
                previousMonkey.addItem(item);
            } else {
                nextMonkey.addItem(item);
            }
            items.remove(item);
        }
    }

    public void addItems(List<Item> items){
        this.items.addAll(items);
    }
    public void addItem(Item item){
        this.items.add(item);
    }

    public void setPreviousMonkey(Monkey previousMonkey) {
        this.previousMonkey = previousMonkey;
    }

    public void setNextMonkey(Monkey nextMonkey) {
        this.nextMonkey = nextMonkey;
    }

    @Override
    public String toString() {
        return ""+itemCounter;
    }
}

class Operation{
    private final int operation;
    private final int value2;

    public Operation(int operation, int value2) {
        this.operation = operation;
        this.value2 = value2;
    }

    public BigInteger apply(BigInteger value){

        if (value2 == -1){
            return value.multiply(value);
        }

        return switch (operation) {
            case 0 -> value.add(BigInteger.valueOf(value2));
            case 2 -> value.multiply(new BigInteger(String.valueOf(value2)));
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }

}

class Item{
    private long round;
    private BigInteger worryLevel;

    public Item(BigInteger worryLevel) {
        this.round = 0;
        this.worryLevel = worryLevel;
    }

    public void setRound(long round) {
        this.round = round;
    }

    public BigInteger getWorryLevel() {
        return worryLevel;
    }

    public void setWorryLevel(BigInteger worryLevel) {
        this.worryLevel = worryLevel;
    }
}
