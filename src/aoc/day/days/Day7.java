package aoc.day.days;

import java.util.*;
import aoc.day.Day;
import aoc.day.days.day7.Directory;
import aoc.day.days.day7.File;

public class Day7 extends Day {

    private List<File> files;
    private List<Directory> directories;
    private Directory root;

    @Override
    public Object part1() {
        List<String> rawInput = this.getDayInput();
        rawInput.remove(0);
        directories = new ArrayList<>();
        files = new ArrayList<>();
        root = new Directory("root", null);

        Directory currentDirectory = root;
        for (int i = 0; i < rawInput.size(); i++) {
            String line = rawInput.get(i);

            if (line.charAt(0)=='$'){
                if (line.contains("cd")){
                    if (line.contains("cd ..")){
                        currentDirectory=currentDirectory.getParent();
                    }else {
                        Directory newDirectory = new Directory(line.substring(5), currentDirectory);
                        directories.add(newDirectory);
                        currentDirectory = newDirectory;
                    }
                } else if (line.contains("ls")) {
                    try {
                        while (rawInput.get(i+1).charAt(0)!='$'){
                            i++;
                            String[] split = rawInput.get(i).split(" ");

                            try {
                                Integer.parseInt(split[0]);
                                files.add(new File(split[1], Integer.parseInt(split[0]), currentDirectory));
                            }catch (NumberFormatException e){

                            }
                        }
                    }catch (IndexOutOfBoundsException e){}
                }
            }
        }
        for (Directory dir: directories) {
            dir.setSize(FileWithParentDir(dir));
        }

        int result = 0;
        for (Directory dir : directories){
            if (dir.getSize()<100000){
                result+=dir.getSize();
            }
        }
        return result;
    }

    private int FileWithParentDir(Directory dir){
        int sum=0;
        for (File file:files) {
            if (file.getParent()==dir){
                sum+=file.getSize();
            }
        }
        for (Directory direct: directories) {
            if (direct.getParent()==dir){
                sum+=FileWithParentDir(direct);
            }
        }
        return sum;
    }

    @Override
    public Object part2() {
        root.setSize(FileWithParentDir(root));
        int currentSpace = 70000000 - root.getSize();
        int neededSpace = 30000000-currentSpace;

        Directory smallest = root;
        for (Directory dir: directories) {
            if (dir.getSize()>neededSpace){
                if (dir.getSize()<smallest.getSize()){
                    smallest=dir;
                }
            }
        }
        return smallest.getSize();
    }
}

