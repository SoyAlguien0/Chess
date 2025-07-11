import java.util.ArrayList;
import java.util.List;

public class Board {
    Box[][] board = new Box[8][8];
    List<Piece> pieces = new ArrayList<Piece>();

    private void initPieces() {
        // Initializing one by one for better debugging
        // White pawns
        pieces.add(new Pawn(new Position(0, 1), 0));
        pieces.add(new Pawn(new Position(1, 1), 0));
        pieces.add(new Pawn(new Position(2, 1), 0));
        pieces.add(new Pawn(new Position(3, 1), 0));
        pieces.add(new Pawn(new Position(4, 1), 0));
        pieces.add(new Pawn(new Position(5, 1), 0));
        pieces.add(new Pawn(new Position(6, 1), 0));
        pieces.add(new Pawn(new Position(7, 1), 0));
        // Black pawns
        pieces.add(new Pawn(new Position(0, 6), 1));
        pieces.add(new Pawn(new Position(1, 6), 1));
        pieces.add(new Pawn(new Position(2, 6), 1));
        pieces.add(new Pawn(new Position(3, 6), 1));
        pieces.add(new Pawn(new Position(4, 6), 1));
        pieces.add(new Pawn(new Position(5, 6), 1));
        pieces.add(new Pawn(new Position(6, 6), 1));
        pieces.add(new Pawn(new Position(7, 6), 1));
    }

    public void initBoard(){
        initBoxes();
        initPieces();
        createBoard();
        drawBoard();
    }

    private void initBoxes(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i+j) % 2 == 0){
                    board[j][i] = new Box(null, 0);
                }else{
                    board[j][i] = new Box(null, 1);
                }
            }
        }
    }

    private void createBoard(){
        //Assign every box it's piece
        setPieces();
    }

    public void setPieces(){
        for(Piece p : pieces){
            // j = x, i = y
            int pieceX = p.getPosition().getX();
            int pieceY = p.getPosition().getY();
            board[pieceX][pieceY].setPiece(p);
        }
    }

    public void drawBoard(){
        final char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int drawBoardSize = board.length + 1; //board size + coordinates size
        for (int i = 0; i < drawBoardSize; i++){
            for (int j = 0; j < drawBoardSize; j++){
                if(j == 0 && i != 0){
                    System.out.print("\t"+(8-i+1));
                }else if (i == 0 && j != 0) {
                    System.out.print("\t"+(char)('a'+j-1));
                }else{
                    if (i != 0 && j != 0) {
                        if(board[j-1][i-1].piece != null){
                            System.out.print("\t"+board[j-1][i-1].piece.getName());
                        }else{
                            //For the future
                            if(board[j-1][i-1].color == 0){
                                System.out.print("\t.");
                            }else {
                                System.out.print("\t.");
                            }
                        }
                    }else System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public ArrayList<Piece> getAvailablePieces(int color){
        boolean checking = true;
        ArrayList<Piece> AvailablePieces= new ArrayList<>();
        for(Piece p : pieces){
            if (p.getColor() == color && !p.getPossibleMoves(checking).isEmpty()){
                AvailablePieces.add(p);
            }
        }
        return AvailablePieces;
    }
}
