package aoc.day.days;

import aoc.day.Day;
import aoc.day.days.day9.Koordinate;

import java.util.List;
import java.util.*;

public class Day17 extends Day {
    List<Koordinate> finishedPieces = new ArrayList<>();

    @Override
    public Object part1() {
        finishedPieces.add(new Koordinate(0, -1));
        finishedPieces.add(new Koordinate(1, -1));
        finishedPieces.add(new Koordinate(2, -1));
        finishedPieces.add(new Koordinate(3, -1));
        finishedPieces.add(new Koordinate(4, -1));
        finishedPieces.add(new Koordinate(5, -1));
        finishedPieces.add(new Koordinate(6, -1));

        String commands = getDayInput().get(0);
        int commandCounter = 0;
        for (int i = 0; i < 2022; i++) {
            Piece currentPiece = spwanPiece(i);

            boolean fall = false;
            while (true ){
                //print(finishedPieces, currentPiece);
                if (fall){
                    if (canMoveDown(currentPiece)){
                        currentPiece.move(0,-1);
                    }else {
                        finishedPieces.addAll(currentPiece.parts);
                        break;
                    }
                    fall = false;
                }else {
                    char command = commands.charAt(commandCounter);
                    commandCounter++;
                    if (commandCounter== commands.length()){
                        commandCounter = 0;
                    }

                    if (command=='>'){
                        if (canMoveRight(currentPiece)){
                            currentPiece.move(1,0);
                        }
                    } else if (command=='<') {
                        if (canMoveLeft(currentPiece)){
                            currentPiece.move(-1,0);
                        }
                    }
                    fall = true;
                }
            }
        }
        return getHighestY(finishedPieces)+1;
    }

    @Override
    public Object part2() {
        return null;
    }

    public void print(List<Koordinate> finishedPieces, Piece currentPiece){
        List<Koordinate> currentPieceKoord = currentPiece.parts;

        for (int y = 30; y >= -1; y--) {
            for (int x = 0; x < 7; x++) {
                Koordinate koordinate = new Koordinate(x,y);
                if (finishedPieces.contains(koordinate)){
                    System.out.print("#");
                }else if (currentPieceKoord.contains(koordinate)){
                    System.out.print("O");
                }else {
                    System.out.print(".");
                }
            }
            System.out.println(" "+y);
        }
        System.out.println();
    }

    public boolean canMoveRight(Piece piece){
        List<Koordinate> koordinates = piece.getRightSide();
        for (Koordinate koordinate : koordinates) {
            if(finishedPieces.contains(new Koordinate(koordinate.getX()+1,koordinate.getY()))){
                return false;
            }
            if (koordinate.getX() >= 6){
                return false;
            }
        }
        return true;
    }
    public boolean canMoveLeft(Piece piece){
        List<Koordinate> koordinates = piece.getLeftSide();
        for (Koordinate koordinate : koordinates) {
            if(finishedPieces.contains(new Koordinate(koordinate.getX()-1,koordinate.getY()))){
                return false;
            }
            if (koordinate.getX() < 1){
                return false;
            }
        }
        return true;
    }
    public boolean canMoveDown(Piece piece){
        List<Koordinate> koordinates = piece.getBottomSide();
        for (Koordinate koordinate : koordinates) {
            if(finishedPieces.contains(new Koordinate(koordinate.getX(),koordinate.getY()-1))){
                return false;
            }
            if (koordinate.getY() < 0){
                return false;
            }
        }
        return true;
    }


    private int getHighestY(List<Koordinate> pieces) {
        int highest = -1;
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getY() > highest) {
                highest = pieces.get(i).getY();
            }
        }
        return highest;
    }

    private Piece spwanPiece(int iteration){
        iteration=iteration%5;
        int highestY = getHighestY(finishedPieces);
        Piece piece = new Piece();
        switch (iteration){
            case 0:
                piece.add(new Koordinate(2,highestY+4));    // @@@@
                piece.add(new Koordinate(3,highestY+4));
                piece.add(new Koordinate(4,highestY+4));
                piece.add(new Koordinate(5,highestY+4));
                break;
            case 1:
                piece.add(new Koordinate(2,highestY+5));    //  @
                piece.add(new Koordinate(3,highestY+5));    // @@@
                piece.add(new Koordinate(4,highestY+5));    //  @
                piece.add(new Koordinate(3,highestY+4));
                piece.add(new Koordinate(3,highestY+6));
                break;
            case 2:
                piece.add(new Koordinate(2,highestY+4));    //    @
                piece.add(new Koordinate(3,highestY+4));    //    @
                piece.add(new Koordinate(4,highestY+4));    //  @@@
                piece.add(new Koordinate(4,highestY+5));
                piece.add(new Koordinate(4,highestY+6));
                break;
            case 3:
                piece.add(new Koordinate(2,highestY+4));    //  @
                piece.add(new Koordinate(2,highestY+5));    //  @
                piece.add(new Koordinate(2,highestY+6));    //  @
                piece.add(new Koordinate(2,highestY+7));    //  @
                break;
            case 4:
                piece.add(new Koordinate(2,highestY+4));    //  @@
                piece.add(new Koordinate(2,highestY+5));    //  @@
                piece.add(new Koordinate(3,highestY+4));
                piece.add(new Koordinate(3,highestY+5));
                break;
        }
        return piece;
    }
}

class Piece{
    List<Koordinate> parts;

    public Piece() {
        parts = new ArrayList<>();
    }
    public void add(Koordinate koordinate){
        parts.add(koordinate);
    }

    public List<Koordinate> getRightSide(){
        List<Koordinate> rightSide = new ArrayList<>();
        for (Koordinate part : parts) {
            if (!parts.contains(new Koordinate(part.getX()+1,part.getY()))){
                rightSide.add(part);
            }
        }
        return rightSide;
    }
    public List<Koordinate> getLeftSide(){
        List<Koordinate> leftSide = new ArrayList<>();
        for (Koordinate part : parts) {
            if (!parts.contains(new Koordinate(part.getX()-1,part.getY()))){
                leftSide.add(part);
            }
        }
        return leftSide;
    }
    public List<Koordinate> getTopSide(){
        List<Koordinate> topSide = new ArrayList<>();
        for (Koordinate part : parts) {
            if (!parts.contains(new Koordinate(part.getX(),part.getY()+1))){
                topSide.add(part);
            }
        }
        return topSide;
    }
    public List<Koordinate> getBottomSide(){
        List<Koordinate> bottomSide = new ArrayList<>();
        for (Koordinate part : parts) {
            if (!parts.contains(new Koordinate(part.getX(),part.getY()-1))){
                bottomSide.add(part);
            }
        }
        return bottomSide;
    }

    public void move(int x, int y){
        for (Koordinate part : parts) {
            part.setX(part.getX()+x);
            part.setY(part.getY()+y);
        }
    }
}