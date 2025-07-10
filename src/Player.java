import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private int color;
    private Board game;
    Scanner sc = new Scanner(System.in);

    public Player(int color, Board game) {
        this.color = color;
        this.game = game;
    }

    public ArrayList<Piece> getAvailablePieces(){
        return game.getAvailablePieces(this.color);
    }

    public Piece choosePiece(ArrayList<Piece> availablePieces){
        System.out.print("Choose a piece: ");
        return availablePieces.get(sc.nextInt()-1);
    }
}
