package aoc.day.days;

import aoc.day.Day;
import java.util.*;

public class Day11 extends Day {

    @Override
    public Object part1() {
    /*

        List<Item> items = new ArrayList<>();
        items.add(new Item(99));
        items.add(new Item(67));
        items.add(new Item(92));
        items.add(new Item(61));
        items.add(new Item(83));
        items.add(new Item(64));
        items.add(new Item(98));
        Operation op = new Operation(2,17);
        Monkey monkey0 = new Monkey(0,items,op, 3);

        items = new ArrayList<>();
        items.add(new Item(78));
        items.add(new Item(74));
        items.add(new Item(88));
        items.add(new Item(89));
        items.add(new Item(50));
        op = new Operation(2,11);
        Monkey monkey1 = new Monkey(1,items,op, 5);

        items = new ArrayList<>();
        items.add(new Item(98));
        items.add(new Item(91));
        op = new Operation(0,4);
        Monkey monkey2 = new Monkey(2,items,op, 2);

        //59, 72, 94, 91, 79, 88, 94, 51
        items = new ArrayList<>();
        items.add(new Item(59));
        items.add(new Item(72));
        items.add(new Item(94));
        items.add(new Item(91));
        items.add(new Item(79));
        items.add(new Item(88));
        items.add(new Item(94));
        items.add(new Item(51));
        op = new Operation(2,-1);
        Monkey monkey3 = new Monkey(3,items,op, 13);

        //Monkey 4:
        //  Starting items: 95, 72, 78
        //  Operation: new = old + 7
        //  Test: divisible by 11
        //    If true: throw to monkey 7
        //    If false: throw to monkey 6
        items = new ArrayList<>();
        items.add(new Item(95));
        items.add(new Item(72));
        items.add(new Item(78));
        op = new Operation(0,7);
        Monkey monkey4 = new Monkey(4,items,op, 11);

        //Monkey 5:
        //  Starting items: 76
        //  Operation: new = old + 8
        //  Test: divisible by 17
        //    If true: throw to monkey 0
        //    If false: throw to monkey 2
        items = new ArrayList<>();
        items.add(new Item(76));
        op = new Operation(0,8);
        Monkey monkey5 = new Monkey(5,items,op, 17);

        //Monkey 6:
        //  Starting items: 69, 60, 53, 89, 71, 88
        //  Operation: new = old + 5
        //  Test: divisible by 19
        //    If true: throw to monkey 7
        //    If false: throw to monkey 1
        items = new ArrayList<>();
        items.add(new Item(69));
        items.add(new Item(60));
        items.add(new Item(53));
        items.add(new Item(89));
        items.add(new Item(71));
        items.add(new Item(88));
        op = new Operation(0,5);
        Monkey monkey6 = new Monkey(6,items,op, 19);

        //Monkey 7:
        //  Starting items: 72, 54, 63, 80
        //  Operation: new = old + 3
        //  Test: divisible by 7
        //    If true: throw to monkey 1
        //    If false: throw to monkey 3
        items = new ArrayList<>();
        items.add(new Item(72));
        items.add(new Item(54));
        items.add(new Item(63));
        items.add(new Item(80));
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

        for (long i = 0; i < 20; i++) {
            monkey0.moveItems(i);
            monkey1.moveItems(i);
            monkey2.moveItems(i);
            monkey3.moveItems(i);
            monkey4.moveItems(i);
            monkey5.moveItems(i);
            monkey6.moveItems(i);
            monkey7.moveItems(i);
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
        items.add(new Item(79));
        items.add(new Item(98));
        Operation op = new Operation(2,19);
        Monkey monkey0 = new Monkey(0,items,op, 23);

        //Monkey 1:
        //  Starting items: 54, 65, 75, 74
        //  Operation: new = old + 6
        //  Test: divisible by 19
        //    If true: throw to monkey 2
        //    If false: throw to monkey 0
        items = new ArrayList<>();
        items.add(new Item(54));
        items.add(new Item(65));
        items.add(new Item(75));
        items.add(new Item(74));
        op = new Operation(0,6);
        Monkey monkey1 = new Monkey(1,items,op, 19);

        //Monkey 2:
        //  Starting items: 79, 60, 97
        //  Operation: new = old * old
        //  Test: divisible by 13
        //    If true: throw to monkey 1
        //    If false: throw to monkey 3
        items = new ArrayList<>();
        items.add(new Item(79));
        items.add(new Item(60));
        items.add(new Item(97));
        op = new Operation(2,-1);
        Monkey monkey2 = new Monkey(2,items,op, 13);

        //Monkey 3:
        //  Starting items: 74
        //  Operation: new = old + 3
        //  Test: divisible by 17
        //    If true: throw to monkey 0
        //    If false: throw to monkey 1
        items = new ArrayList<>();
        items.add(new Item(74));
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

        for (long i = 0; i < 20; i++) {
            monkey0.moveItems(i);
            monkey1.moveItems(i);
            monkey2.moveItems(i);
            monkey3.moveItems(i);
        }
        List<longeger> itemsInspected = new ArrayList<>();
        itemsInspected.add(monkey0.getItemsInspected());
        itemsInspected.add(monkey1.getItemsInspected());
        itemsInspected.add(monkey2.getItemsInspected());
        itemsInspected.add(monkey3.getItemsInspected());


        //find largest two numbers
        long largest = 0;
        long secondLargest = 0;
        for (int i = 0; i < itemsInspected.size(); i++) {
            if (itemsInspected.get(i) > largest) {
                secondLargest = largest;
                largest = itemsInspected.get(i);
            } else if (itemsInspected.get(i) > secondLargest) {
                secondLargest = itemsInspected.get(i);
            }
        }
        return largest * secondLargest;

     */
        return -1;
    }

    @Override
    public Object part2() {
    /*

        List<Item> items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(99)));
        items.add(new Item(new VeryLongNumber(67)));
        items.add(new Item(new VeryLongNumber(92)));
        items.add(new Item(new VeryLongNumber(61)));
        items.add(new Item(new VeryLongNumber(83)));
        items.add(new Item(new VeryLongNumber(64)));
        items.add(new Item(new VeryLongNumber(98)));
        Operation op = new Operation(2,17);
        Monkey monkey0 = new Monkey(0,items,op, 3);

        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(78)));
        items.add(new Item(new VeryLongNumber(74)));
        items.add(new Item(new VeryLongNumber(88)));
        items.add(new Item(new VeryLongNumber(89)));
        items.add(new Item(new VeryLongNumber(50)));
        op = new Operation(2,11);
        Monkey monkey1 = new Monkey(1,items,op, 5);

        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(98)));
        items.add(new Item(new VeryLongNumber(91)));
        op = new Operation(0,4);
        Monkey monkey2 = new Monkey(2,items,op, 2);

        //59, 72, 94, 91, 79, 88, 94, 51
        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(59)));
        items.add(new Item(new VeryLongNumber(72)));
        items.add(new Item(new VeryLongNumber(94)));
        items.add(new Item(new VeryLongNumber(91)));
        items.add(new Item(new VeryLongNumber(79)));
        items.add(new Item(new VeryLongNumber(88)));
        items.add(new Item(new VeryLongNumber(94)));
        items.add(new Item(new VeryLongNumber(51)));
        op = new Operation(2,-1);
        Monkey monkey3 = new Monkey(3,items,op, 13);

        //Monkey 4:
        //  Starting items: 95, 72, 78
        //  Operation: new = old + 7
        //  Test: divisible by 11
        //    If true: throw to monkey 7
        //    If false: throw to monkey 6
        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(95)));
        items.add(new Item(new VeryLongNumber(72)));
        items.add(new Item(new VeryLongNumber(78)));
        op = new Operation(0,7);
        Monkey monkey4 = new Monkey(4,items,op, 11);

        //Monkey 5:
        //  Starting items: 76
        //  Operation: new = old + 8
        //  Test: divisible by 17
        //    If true: throw to monkey 0
        //    If false: throw to monkey 2
        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(76)));
        op = new Operation(0,8);
        Monkey monkey5 = new Monkey(5,items,op, 17);

        //Monkey 6:
        //  Starting items: 69, 60, 53, 89, 71, 88
        //  Operation: new = old + 5
        //  Test: divisible by 19
        //    If true: throw to monkey 7
        //    If false: throw to monkey 1
        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(69)));
        items.add(new Item(new VeryLongNumber(60)));
        items.add(new Item(new VeryLongNumber(53)));
        items.add(new Item(new VeryLongNumber(89)));
        items.add(new Item(new VeryLongNumber(71)));
        items.add(new Item(new VeryLongNumber(88)));
        op = new Operation(0,5);
        Monkey monkey6 = new Monkey(6,items,op, 19);

        //Monkey 7:
        //  Starting items: 72, 54, 63, 80
        //  Operation: new = old + 3
        //  Test: divisible by 7
        //    If true: throw to monkey 1
        //    If false: throw to monkey 3
        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(72)));
        items.add(new Item(new VeryLongNumber(54)));
        items.add(new Item(new VeryLongNumber(63)));
        items.add(new Item(new VeryLongNumber(80)));
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

        */

        //Monkey 0:
        //Starting items: 79, 98
        //Operation: new = old * 19
        //Test: divisible by 23
        //If true: throw to monkey 2
        //If false: throw to monkey 3
        List<Item> items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(79)));
        items.add(new Item(new VeryLongNumber(98)));
        Operation op = new Operation(2,19);
        Monkey monkey0 = new Monkey(0,items,op, 23);

        //Monkey 1:
        //  Starting items: 54, 65, 75, 74
        //  Operation: new = old + 6
        //  Test: divisible by 19
        //    If true: throw to monkey 2
        //    If false: throw to monkey 0
        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(54)));
        items.add(new Item(new VeryLongNumber(65)));
        items.add(new Item(new VeryLongNumber(75)));
        items.add(new Item(new VeryLongNumber(74)));
        op = new Operation(0,6);
        Monkey monkey1 = new Monkey(1,items,op, 19);

        //Monkey 2:
        //  Starting items: 79, 60, 97
        //  Operation: new = old * old
        //  Test: divisible by 13
        //    If true: throw to monkey 1
        //    If false: throw to monkey 3
        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(79)));
        items.add(new Item(new VeryLongNumber(60)));
        items.add(new Item(new VeryLongNumber(97)));
        op = new Operation(2,-1);
        Monkey monkey2 = new Monkey(2,items,op, 13);

        //Monkey 3:
        //  Starting items: 74
        //  Operation: new = old + 3
        //  Test: divisible by 17
        //    If true: throw to monkey 0
        //    If false: throw to monkey 1
        items = new ArrayList<>();
        items.add(new Item(new VeryLongNumber(74)));
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
            item.setWorryLevel(operation.apply(item.getWorryLevel()));
            //item.setWorryLevel((long) Math.floor(item.getWorryLevel() / 3));

            item.setRound(round + 1);


            if (item.getWorryLevel().mod((int) divisionValue) == 0) {
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

    public VeryLongNumber apply(VeryLongNumber value){

        if (value2 == -1){
            return value.multiply(value);
        }

        return switch (operation) {
            case 0 -> value.add(value2);
            case 2 -> value.multiply(new VeryLongNumber(value2));
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }

}

class Item{
    private long round;
    private VeryLongNumber worryLevel;

    public Item(VeryLongNumber worryLevel) {
        this.round = 0;
        this.worryLevel = worryLevel;
    }

    public void setRound(long round) {
        this.round = round;
    }

    public VeryLongNumber getWorryLevel() {
        return worryLevel;
    }

    public void setWorryLevel(VeryLongNumber worryLevel) {
        this.worryLevel = worryLevel;
    }

    @Override
    public String toString() {
        return worryLevel.number;
    }
}

class VeryLongNumber{
    String number;

    public VeryLongNumber(int number) {
        this.number = String.valueOf(number);
    }

    public VeryLongNumber(String number) {
        this.number = number;
    }

    public VeryLongNumber add(VeryLongNumber number){
        String number1 = this.number;
        String number2 = number.number;
        if (number1.length() > number2.length()){
            number2 = addZeros(number2, number1.length() - number2.length());
        }else if (number2.length() > number1.length()){
            number1 = addZeros(number1, number2.length() - number1.length());
        }
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = number1.length() - 1; i >= 0; i--) {
            int sum = Integer.parseInt(String.valueOf(number1.charAt(i))) + Integer.parseInt(String.valueOf(number2.charAt(i))) + carry;
            carry = sum / 10;
            result.insert(0, (sum % 10));
        }
        if (carry > 0){
            result.insert(0, carry);
        }
        return new VeryLongNumber(result.toString());
    }
    public VeryLongNumber add(int number){
        return add(new VeryLongNumber(number));
    }

    public VeryLongNumber multiply(VeryLongNumber numb2)
    {
        String num1 = this.number;
        String num2 = numb2.number;
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 == 0 || len2 == 0)
            return new VeryLongNumber(0);

        // will keep the result number in vector
        // in reverse order
        int[] result = new int[len1 + len2];

        // Below two indexes are used to
        // find positions in result.
        int i_n1 = 0;
        int i_n2 = 0;

        // Go from right to left in num1
        for (int i = len1 - 1; i >= 0; i--)
        {
        int carry = 0;
        int n1 = num1.charAt(i) - '0';

        // To shift position to left after every
        // multipliccharAtion of a digit in num2
        i_n2 = 0;

        // Go from right to left in num2
        for (int j = len2 - 1; j >= 0; j--)
        {
        // Take current digit of second number
        int n2 = num2.charAt(j) - '0';

        // Multiply with current digit of first number
        // and add result to previously stored result
        // charAt current position.
        int sum = n1 * n2 + result[i_n1 + i_n2] + carry;

        // Carry for next itercharAtion
        carry = sum / 10;

        // Store result
        result[i_n1 + i_n2] = sum % 10;

        i_n2++;
        }

        // store carry in next cell
        if (carry > 0)
            result[i_n1 + i_n2] += carry;

        // To shift position to left after every
        // multipliccharAtion of a digit in num1.
        i_n1++;
        }

        // ignore '0's from the right
        int i = result.length - 1;
        while (i >= 0 && result[i] == 0)
            i--;

        // If all were '0's - means either both
        // or one of num1 or num2 were '0'
        if (i == -1)
            return new VeryLongNumber(0);

        // genercharAte the result String
        StringBuilder s = new StringBuilder();

        while (i >= 0)
            s.append(result[i--]);

        return new VeryLongNumber(s.toString());
    }

    public int mod(int a)
    {
        String num = this.number;
        // Initialize result
        int res = 0;

        // One by one process all digits of 'num'
        for (int i = 0; i < num.length(); i++)
            res = (res * 10 + (int)num.charAt(i) - '0') % a;

        return res;
    }

    private String addZeros(String number2, int i) {
        StringBuilder number2Builder = new StringBuilder(number2);
        for (int j = 0; j < i; j++) {
            number2Builder.insert(0, "0");
        }
        number2 = number2Builder.toString();
        return number2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeryLongNumber that = (VeryLongNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return number;
    }
}