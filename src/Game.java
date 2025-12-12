import java.util.ArrayList;

public class Game {
    private int totalMovements;
    private Player[] players;
    Board game = new Board();

    public Game(int numPlayers) {
        players = new Player[numPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i);
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
                    ArrayList<Piece> availablePieces = getAvailablePieces(currentPlayer);
                    showAvailablePieces(availablePieces);
                    int playerInput = -1;
                    while (!validChose(new int[]{0, availablePieces.size()-1},playerInput)){
                        playerInput = currentPlayer.choosePiece(availablePieces);
                    }
                    Piece chosenPiece = availablePieces.get(playerInput);

                    //show possible moves from a specific piece
                    boolean checking = true;
                    ArrayList<Position> possibleMoves = chosenPiece.getPossibleMoves(!checking);
                    ArrayList<Position> possibleKills = chosenPiece.getPossibleKills(!checking);

                    Position chosenPosition = null;
                    if (!possibleMoves.isEmpty() && !possibleKills.isEmpty()) {
                        int chosenPlay = -1;
                        while (!validChose(new int[]{1, 2},chosenPlay)){
                            System.out.println("1. Kills\n" +
                                    "2. moves ");
                            chosenPlay = currentPlayer.choosePlay();
                        }
                        if (chosenPlay == 1){
                            playerInput = -1;
                            while (!validChose(new int[]{0, possibleMoves.size()-1},playerInput)){
                                showPossibleMoves(possibleKills);
                                playerInput = currentPlayer.choosePosition(possibleKills);
                            }
                            chosenPosition = possibleKills.get(playerInput);
                        }else if(chosenPlay == 2) {
                            playerInput = -1;
                            while (!validChose(new int[]{0, possibleMoves.size()-1},playerInput)){
                                showPossibleMoves(possibleMoves);
                                playerInput = currentPlayer.choosePosition(possibleMoves);
                            }
                            chosenPosition = possibleMoves.get(playerInput);
                        }
                    }
                    else if (!possibleMoves.isEmpty()){
                        playerInput = -1;
                        while (!validChose(new int[]{0, possibleMoves.size()-1},playerInput)){
                            showPossibleMoves(possibleMoves);
                            playerInput = currentPlayer.choosePosition(possibleMoves);
                        }
                        chosenPosition = possibleMoves.get(playerInput);
                    }
                    else if (!possibleKills.isEmpty()){
                        playerInput = -1;
                        while (!validChose(new int[]{0, possibleMoves.size()-1},playerInput)){
                            showPossibleMoves(possibleKills);
                            playerInput = currentPlayer.choosePosition(possibleKills);
                        }
                        chosenPosition = possibleKills.get(playerInput);
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

    public boolean validChose(int[] limits, int chose){
        return chose >= limits[0] && chose <= limits[1];
    }

    public ArrayList<Piece> getAvailablePieces(Player player){
        return game.getAvailablePieces(player.getColor());
    }
}
