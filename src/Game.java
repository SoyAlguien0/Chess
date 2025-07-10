import java.util.ArrayList;
import java.util.List;

public class Game {
    private int totalMovements;
    private Player[] players;
    Board game = new Board();

    public Game(int numPlayers) {
        players = new Player[numPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i, game);
        }
        initGame();
    }

    public void initGame(){
        game.initBoard();
        loopGame();
    }

    public void loopGame(){
        boolean gameOver = false;
        while(!gameOver){
            if (players.length == 2){
                for (int i = 0; i < players.length; i++) {
                    System.out.println("Player "+(i+1)+" turn.");
                    ArrayList<Piece> availablePieces = players[i].getAvailablePieces();
                    showAvailablePieces(availablePieces);
                    Piece chosenPiece = players[i].choosePiece(availablePieces);
                    System.out.println(chosenPiece);
                }
            }
        }
    }

    public void showAvailablePieces(ArrayList<Piece> availablePieces){
        for (int i = 0; i < availablePieces.size(); i++) {
            Piece p = availablePieces.get(i);
            System.out.println((i+1)+". "+p);
        }
    }
}
