package Player;

import Consts.Status;
import java.util.Scanner;
import Consts.Color;

public class Player {
    private String name;
    private Color color;
    private final Scanner sc = new Scanner(System.in);
    private int alivePieces = 16;
    private Status status = Status.ALIVE;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public boolean isDead(){
        return this.status == Status.DEAD;
    }

    public void setName(String name) {
        this.name = name;
    }
}