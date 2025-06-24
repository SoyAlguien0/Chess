import java.util.ArrayList;
import java.util.List;

public class Board {
    Box[][] board = new Box[8][8];
    List<Piece> pieces = new ArrayList<Piece>();

    public void initPieces() {
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
    }

    public void initBoxes(){
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

    public void createBoard(){
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
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[j][i].piece != null){
                    System.out.print("\t"+board[j][i].piece.getName());
                }else{
                    //For the future
                    if(board[j][i].color == 0){
                        System.out.print("\t.");
                    }else {
                        System.out.print("\t.");
                    }
                }
            }
            System.out.println();
        }
    }
}
