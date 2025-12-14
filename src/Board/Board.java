package Board;
import Pieces.*;
import java.util.ArrayList;

public class Board {
    private final Box[][] board = new Box[8][8];
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private boolean debugMode;

    public Board(boolean debugMode){
        this.debugMode = debugMode;
    }

    private void initPieces() {
        // Initializing one by one for better debugging
        // White
        pieces.add(new Rook(new Position(0, 0), 0));
        pieces.add(new Knight(new Position(1, 5), 0));
        pieces.add(new Bishop(new Position(2, 0), 0));
        pieces.add(new Queen(new Position(3, 0), 0));
        pieces.add(new King(new Position(4, 0), 0));
        pieces.add(new Bishop(new Position(5, 0), 0));
        pieces.add(new Knight(new Position(6, 0), 0));
        pieces.add(new Rook(new Position(7, 0), 0));
        pieces.add(new Pawn(new Position(0, 1), 0));
        pieces.add(new Pawn(new Position(1, 1), 0));
        pieces.add(new Pawn(new Position(2, 1), 0));
        pieces.add(new Pawn(new Position(3, 1), 0));
        pieces.add(new Pawn(new Position(4, 1), 0));
        pieces.add(new Pawn(new Position(5, 1), 0));
        pieces.add(new Pawn(new Position(6, 1), 0));
        pieces.add(new Pawn(new Position(7, 1), 0));

        // Black
        pieces.add(new Rook(new Position(0, 7), 1));
        pieces.add(new Knight(new Position(1, 7), 1));
        pieces.add(new Bishop(new Position(2, 7), 1));
        pieces.add(new Queen(new Position(3, 7), 1));
        pieces.add(new King(new Position(4, 7), 1));
        pieces.add(new Bishop(new Position(5, 7), 1));
        pieces.add(new Knight(new Position(6, 7), 1));
        pieces.add(new Rook(new Position(7, 7), 1));
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
        updateBoard(null);
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

    public void updateBoard(ArrayList<Position> moves){
        setPieces();
        setDraws();
        drawTrail(moves);
        drawBoard();
    }

    public void setPieces(){
        //Assign every box it's piece
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                board[j][i].setPiece(null);
            }
        }
        // j = x, i = y
        for(Piece p : pieces){
            if (!p.isDead()){
                int pieceX = p.getPosition().getX();
                int pieceY = p.getPosition().getY();
                board[pieceX][pieceY].setPiece(p);
            }
        }
    }

    public void setPiece(Piece piece, Position position){
        int newX = position.getX();
        int newY = position.getY();

        if (board[newX][newY].getPiece() != null){
            board[newX][newY].getPiece().setDead(true);
        }

        piece.setPosition(position);
        board[newX][newY].setPiece(piece);
        piece.setHasMoved(true);

        setPieces();
    }

    public void drawBoard(){
        int drawBoardSize = board.length + 1; //board + coordinates
        for (int i = 0; i < drawBoardSize; i++){
            for (int j = 0; j < drawBoardSize; j++){
                if(j == 0 && i != 0){
                    System.out.print("\t"+(8-i+1));
                }else if (i == 0 && j != 0) {
                    System.out.print("\t"+(char)('a'+j-1));
                }else{
                    if (i != 0 && j != 0) {
                        Box box = board[j-1][i-1];
                        if(box.getPiece() != null){
                            System.out.print("\t"+box.getDraw());
                        }else{
                            //For the future
                            if(box.getColor() == 0){
                                System.out.print("\t"+box.getDraw());
                            }else {
                                System.out.print("\t"+box.getDraw());
                            }
                        }
                    }else System.out.print("\t"); //0, 0
                }
            }
            System.out.println();
        }
    }

    private void setDraws(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Box box = board[j][i];
                if(box.getPiece() != null){
                    box.setDraw(box.getPiece().getName());
                }else{
                    //For the future
                    if(box.getColor() == 0){
                        box.setDraw('.');
                    }else {
                        box.setDraw('.');
                    }
                }
            }
        }
    }

    public void drawTrail(ArrayList<Position> moves){
        if (moves != null){
            for (Position move:moves){
                int x = move.getX();
                int y = move.getY();
                Box box = board[x][y];
                if (box.getPiece() == null){
                    box.setDraw('0');
                }else{
                    box.setDraw('X');
                }
            };
        }
    }

    public ArrayList<Piece> getAvailablePieces(int playerColor){
        boolean checking = true;
        ArrayList<Piece> AvailablePieces= new ArrayList<>();
        for(Piece p : pieces){
            if (!p.isDead() && p.getColor() == playerColor && (!getMoves(p, checking).isEmpty() || !getKills(p, checking).isEmpty())){
                AvailablePieces.add(p);
            }
        }
        return AvailablePieces;
    }

    public ArrayList<Position> getKills(Piece piece, boolean checking){
        ArrayList<ArrayList<Position>> allPossibleKills = piece.getPossibleKills(checking);
        ArrayList<Position> kills = new ArrayList<Position>();
        for (ArrayList<Position> possibleKills: allPossibleKills){
            possibleKills = checkCollisions(possibleKills, piece);
            for (Position position: possibleKills){
                int x = position.getX();
                int y = position.getY();
                Piece pieceToCheck = board[x][y].getPiece();
                if(pieceToCheck != null && piece.getColor() != pieceToCheck.getColor()){
                    kills.add(position);
                }
            }
        }
        return kills;
    }

    public ArrayList<Position> getMoves(Piece piece, boolean checking){
        ArrayList<ArrayList<Position>> allPossibleMoves = piece.getPossibleMoves(checking);
        ArrayList<Position> moves = new ArrayList<Position>();
        for (ArrayList<Position> possibleMoves: allPossibleMoves){
            possibleMoves = checkCollisions(possibleMoves, piece);
            for (Position position: possibleMoves){
                int x = position.getX();
                int y = position.getY();
                Piece pieceToCheck = board[x][y].getPiece();
                if(pieceToCheck == null){
                    moves.add(position);
                }
            }
        }
        return moves;
    }

    public ArrayList<Position> checkCollisions(ArrayList<Position> possibleMoves, Piece piece){
        ArrayList<Position> moves = new ArrayList<Position>();
        for (Position position: possibleMoves){
            int x = position.getX();
            int y = position.getY();
            Piece pieceToCheck = board[x][y].getPiece();

//            int xx = piece.getPosition().getX();
//            int yy = piece.getPosition().getY();
//
//            if(!piece.canPenetrate()){
//                if (x != xx){
//                    for (int i = 1; i < Math.abs(x-xx); i++) {
//                        if (board[x+i][y].getPiece() != null){
//                            return moves;
//                        }
//                    }
//                } else if (y != yy) {
//                    for (int i = 1; i < Math.abs(y-yy); i++) {
//                        if (board[x][y+i].getPiece() != null){
//                            return moves;
//                        }
//                    }
//                }
//            }

            if(pieceToCheck == null) {
                moves.add(position);
            } else if (!pieceToCheck.equals(piece)) {
                if (pieceToCheck.getColor() != piece.getColor()){
                    moves.add(position);
                }
                if (!piece.hasVariousTargets()){
                    return moves;
                }
            }
        }
        return moves;
    }
}
