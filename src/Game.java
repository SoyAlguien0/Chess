import java.util.ArrayList;

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
        gameLoop();
    }

    public void gameLoop(){
        boolean gameOver = false;
        while(!gameOver){
            if (players.length == 2){
                for (int i = 0; i < players.length; i++) {
                    Player currentPlayer = players[i];
                    System.out.println("Player "+(i+1)+" turn.");

                    //show available pieces
                    ArrayList<Piece> availablePieces = currentPlayer.getAvailablePieces();
                    showAvailablePieces(availablePieces);
                    Piece chosenPiece = currentPlayer.choosePiece(availablePieces);

                    //show possible moves from a specific piece
                    boolean checking = true;
                    ArrayList<Position> possibleMoves = chosenPiece.getPossibleMoves(!checking);
                    ArrayList<Position> possibleKills = chosenPiece.getPossibleKills(!checking);

                    Position chosenPosition = null;
                    if (!possibleMoves.isEmpty() && !possibleKills.isEmpty()) {
                        System.out.println("1. Kills\n" + //player
                                "2. moves ");
                        int chosenPlay = currentPlayer.choosePlay();
                        if (chosenPlay == 1){
                            showPossibleMoves(possibleKills);
                            chosenPosition = currentPlayer.choosePosition(possibleKills);
                        }else if(chosenPlay == 2) {
                            showPossibleMoves(possibleMoves);
                            chosenPosition = currentPlayer.choosePosition(possibleMoves);
                        }
                    }
                    else if (!possibleMoves.isEmpty()){
                        showPossibleMoves(possibleMoves);
                        chosenPosition = currentPlayer.choosePosition(possibleMoves);
                    }
                    else if (!possibleKills.isEmpty()){
                        showPossibleMoves(possibleKills);
                        chosenPosition = currentPlayer.choosePosition(possibleKills);
                    }

                    //set the piece to a chosen position
                    setPiece(chosenPiece, chosenPosition);
                }
            }
        }
    }

    public void showPossibleMoves(ArrayList<Position> possibleMoves){
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
        game.drawBoard();
    }
}
