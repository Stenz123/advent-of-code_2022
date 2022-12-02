package aoc.day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {
    private int day;
    public Day() {
        String substring = this.getClass().getSimpleName().substring(3, this.getClass().getSimpleName().length());
        try {
            this.day = Integer.parseInt(String.valueOf(substring));
        }catch (NumberFormatException e){
            System.out.println("Invalid number after 'Day' in class name");
        }
    }
    public int getDay() {
        return day;
    }

    public abstract Solution solve();

    public List<String> getDayInput(){
        Path filePath = Paths.get(String.format("data/day%d.txt", this.day));
        try {
            return Files.readAllLines(filePath);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
