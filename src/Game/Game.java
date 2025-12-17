package Game;
import Consts.Color;
import Pieces.*;
import Board.*;
import Player.*;
import java.util.ArrayList;

public class Game {
    private int totalMovements;
    private Player[] players;
    private final boolean debugMode = true;
    Board game = new Board(debugMode);

    public Game(int numPlayers) {
        players = new Player[numPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(Color.values()[i], ""+(i+1));
        }
        initGame();
    }

    public void initGame(){
        game.initBoard();
        gameLoop();
    }

    public void gameLoop(){
        boolean gameOver = false;
        while(!gameOver){
            if (players.length == 2){
                for (int i = 0; i < players.length; i++) {
                    Player currentPlayer = players[i];
                    int alivePieces = game.getAlivePieces(currentPlayer.getColor());
                    currentPlayer.setAlivePieces(alivePieces);
                    if (alivePieces <= 0){
                        currentPlayer.setStatus(0);
                        gameOver = true;
                        break;
                    }

                    System.out.println("Player "+currentPlayer.getName()+" turn.");

                    //show available pieces
                    ArrayList<Piece> availablePieces = getAvailablePieces(currentPlayer);
                    showAvailablePieces(availablePieces);
                    int playerInput = -1;
                    while (!validChose(new int[]{0, availablePieces.size()-1},playerInput)){
                        playerInput = currentPlayer.choosePiece(availablePieces);
                    }
                    Piece chosenPiece = availablePieces.get(playerInput);

                    //show possible moves from a specific piece
                    boolean checking = true;
                    ArrayList<Position> moves = game.getMoves(chosenPiece, !checking);
                    ArrayList<Position> kills = game.getKills(chosenPiece, !checking);

                    Position chosenPosition = null;
                    if (!moves.isEmpty() && !kills.isEmpty()) {
                        int chosenPlay = -1;
                        while (!validChose(new int[]{1, 2},chosenPlay)){
                            System.out.println("1. Kills\n" +
                                    "2. moves ");
                            chosenPlay = currentPlayer.choosePlay();
                        }
                        if (chosenPlay == 1){
                            playerInput = -1;
                            while (!validChose(new int[]{0, moves.size()-1},playerInput)){
                                showPossibleMoves(kills);
                                playerInput = currentPlayer.choosePosition(kills);
                            }
                            chosenPosition = kills.get(playerInput);
                        }else if(chosenPlay == 2) {
                            playerInput = -1;
                            while (!validChose(new int[]{0, moves.size()-1},playerInput)){
                                showPossibleMoves(moves);
                                playerInput = currentPlayer.choosePosition(moves);
                            }
                            chosenPosition = moves.get(playerInput);
                        }
                    }
                    else if (!moves.isEmpty()){
                        playerInput = -1;
                        while (!validChose(new int[]{0, moves.size()-1},playerInput)){
                            showPossibleMoves(moves);
                            playerInput = currentPlayer.choosePosition(moves);
                        }
                        chosenPosition = moves.get(playerInput);
                    }
                    else if (!kills.isEmpty()){
                        playerInput = -1;
                        while (!validChose(new int[]{0, moves.size()-1},playerInput)){
                            showPossibleMoves(kills);
                            playerInput = currentPlayer.choosePosition(kills);
                        }
                        chosenPosition = kills.get(playerInput);
                    }

                    //set the piece to a chosen position
                    setPiece(chosenPiece, chosenPosition);
                }
            }
        }
        Player winner = null;
        for (Player player:players){
            if (player.getStatus() == 1){
                winner = player;
            }
        }
        System.out.println("Player "+winner.getName()+" wins.");
    }

    public void showPossibleMoves(ArrayList<Position> possibleMoves){
        if (debugMode){
            game.updateBoard(possibleMoves);
        }else{
            game.updateBoard(null);
        }

        for (int i = 0; i < possibleMoves.size(); i++) {
            Position p = possibleMoves.get(i);
            System.out.println((i+1)+". "+p);
        }
    }

    public void showAvailablePieces(ArrayList<Piece> availablePieces){
        for (int i = 0; i < availablePieces.size(); i++) {
            Piece p = availablePieces.get(i);
            System.out.println((i+1)+". "+p);
        }
    }

    public void setPiece(Piece chosenPiece, Position chosenPosition){
        game.setPiece(chosenPiece ,chosenPosition);
        game.updateBoard(null);
    }

    public boolean validChose(int[] limits, int chose){
        return chose >= limits[0] && chose <= limits[1];
    }

    public ArrayList<Piece> getAvailablePieces(Player player){
        return game.getAvailablePieces(player.getColor());
    }
}
