import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private int color;
    Scanner sc = new Scanner(System.in);

    public Player(int color) {
        this.color = color;
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

    public void setColor(int color) {
        this.color = color;
    }
}
