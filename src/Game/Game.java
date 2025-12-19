package Game;
import Consts.Color;
import Consts.Status;
import Pieces.*;
import Board.*;
import Player.*;
import java.util.ArrayList;

public class Game {
    private Player[] players;
    private final boolean debugMode = true;
    Board game = new Board(debugMode);

    public Game(int numPlayers) {
        players = new Player[numPlayers];
        initGame();
    }

    public void initGame(){
        initPlayers();
        game.initBoard();
        gameLoop();
    }

    public void initPlayers(){
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(Color.values()[i], ""+(i+1));
        }
    }

    public void gameLoop(){
        boolean gameOver = false;
        while(!gameOver){
            if (players.length == 2){
                for (Player currentPlayer : players) {
                    setPlayerStatus(currentPlayer, currentPlayer.getColor());
                    if (currentPlayer.isDead()){
                        gameOver = true;
                        break;
                    }
                    Color enemyColor = getEnemyColor(currentPlayer);

                    System.out.println("Player " + currentPlayer.getName() + " turn.");

                    boolean isCheck = game.isKingInCheck(getEnemyColor(currentPlayer));
                    if (isCheck){
                        System.out.println("Player " + currentPlayer.getName() + ", your king is in check.");
                    }

                    ArrayList<Piece> availablePieces = getAvailablePieces(currentPlayer);
                    showAvailablePieces(availablePieces);
                    Piece chosenPiece = getChosenPiece(availablePieces, currentPlayer);

                    Position chosenPosition = getChosenPosition(chosenPiece, currentPlayer, enemyColor);
                    setPiece(chosenPiece, chosenPosition, enemyColor);
                }
            }
        }
        Player winner = getWinner();
        showWinner(winner);
    }

    public Position getPositionByInput(ArrayList<Position> moves, Player currentPlayer, Color enemyColor){
        int playerInput = -1;
        while (!validChose(new int[]{0, moves.size() - 1}, playerInput)) {
            showPossibleMoves(moves, enemyColor);
            playerInput = currentPlayer.choosePosition();
        }
        return moves.get(playerInput);
    }

    public Position getChosenPosition(Piece chosenPiece, Player currentPlayer, Color enemyColor){
        boolean checking = true;
        ArrayList<Position> moves = game.getMovesFromPiece(chosenPiece, !checking);
        ArrayList<Position> kills = game.getKillsFromPiece(chosenPiece, !checking);
        if (!moves.isEmpty() && !kills.isEmpty()) {
            int chosenPlay = -1;
            while (!validChose(new int[]{1, 2}, chosenPlay)) {
                System.out.println("1. Kills\n" +
                        "2. moves ");
                chosenPlay = currentPlayer.choosePlay();
            }
            if (chosenPlay == 1) {
                return getPositionByInput(kills, currentPlayer,enemyColor);

            } else if (chosenPlay == 2) {
                return getPositionByInput(moves, currentPlayer,enemyColor);
            }
        } else if (!moves.isEmpty()) {
            return getPositionByInput(moves, currentPlayer,enemyColor);
        } else if (!kills.isEmpty()) {
            return getPositionByInput(kills, currentPlayer,enemyColor);
        }
        return null;
    }

    public Piece getChosenPiece(ArrayList<Piece> availablePieces, Player currentPlayer){
        int playerInput = -1;
        while (!validChose(new int[]{0, availablePieces.size() - 1}, playerInput)) {
            playerInput = currentPlayer.choosePiece();
        }
        return availablePieces.get(playerInput);
    }

    public void showPossibleMoves(ArrayList<Position> possibleMoves, Color enemyColor){
        if (debugMode){
            game.updateBoard(possibleMoves, enemyColor);
        }else{
            game.updateBoard(null, enemyColor);
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

    public void setPlayerStatus(Player currentPlayer, Color playerColor){
        int alivePieces = game.getAlivePieces(currentPlayer.getColor());
        currentPlayer.setAlivePieces(alivePieces);
        boolean isStalemate = game.checkStalemate(playerColor);
        boolean isCheck = game.isKingInCheck(getEnemyColor(currentPlayer));
        boolean isCheckMate = isCheck && isStalemate;

        if (alivePieces <= 0 || isCheckMate || isStalemate) {
            currentPlayer.setStatus(Status.DEAD);
        }
    }

    public Player getWinner(){
        for (Player player:players){
            if (player.getStatus() == Status.ALIVE){
                return player;
            }
        }
        return null;
    }

    public void showWinner(Player winner){
        System.out.println("Player "+winner.getName()+" wins.");
    }

    public void setPiece(Piece chosenPiece, Position chosenPosition, Color enemyColor){
        game.setPiece(chosenPiece ,chosenPosition);
        game.updateBoard(null, enemyColor);
    }

    public boolean validChose(int[] limits, int chose){
        return chose >= limits[0] && chose <= limits[1];
    }

    public Color getEnemyColor(Player currentPlayer){
        return currentPlayer.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public ArrayList<Piece> getAvailablePieces(Player player){
        return game.getAvailablePieces(player.getColor());
    }
}