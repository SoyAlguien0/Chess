package Player;

import Board.Position;
import Pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private int color;
    private Scanner sc = new Scanner(System.in);
    private int alivePieces = 16;

    //todo
    private int status = 1;

    public Player(int color, String name) {
        this.color = color;
        this.name = name;
    }

    public int choosePiece(ArrayList<Piece> availablePieces){
        System.out.print("Choose a piece: ");
        return sc.nextInt()-1;
    }

    public int choosePosition(ArrayList<Position> possibleMoves){
        System.out.print("Choose a movement: ");
        return sc.nextInt()-1;
    }

    public int choosePlay(){
        System.out.print("Choose the play: ");
        return sc.nextInt();
    }

    public int getColor() {
        return color;
    }

    public int getAlivePieces() {
        return alivePieces;
    }

    public void setAlivePieces(int alivePieces) {
        this.alivePieces = alivePieces;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
