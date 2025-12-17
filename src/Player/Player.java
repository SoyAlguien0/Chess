package Player;

import Board.Position;
import Pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;
import Consts.Color;

public class Player {
    private String name;
    private Color color;
    private final Scanner sc = new Scanner(System.in);
    private int alivePieces = 16;

    //todo
    private int status = 1;

    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public int choosePiece(){
        System.out.print("Choose a piece: ");
        return sc.nextInt()-1;
    }

    public int choosePosition(){
        System.out.print("Choose a movement: ");
        return sc.nextInt()-1;
    }

    public int choosePlay(){
        System.out.print("Choose the play: ");
        return sc.nextInt();
    }

    public Color getColor() {
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
