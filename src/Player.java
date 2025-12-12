import java.util.ArrayList;
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

    public Position choosePosition(ArrayList<Position> possibleMoves){
        System.out.print("Choose a movement: ");
        return possibleMoves.get(sc.nextInt()-1);
    }

    public int choosePlay(){
        System.out.print("Choose the play: ");
        return sc.nextInt();
    }
}
