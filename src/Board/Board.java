package Board;
import Consts.State;
import Pieces.*;
import java.util.ArrayList;
import Consts.Color;

public class Board {
    private final Box[][] board = new Box[8][8];
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private boolean debugMode;

    public Board(boolean debugMode){
        this.debugMode = debugMode;
    }

    private void initPieces() {
        // Initializing one by one for better debugging
        pieces.add(new Rook(new Position(0, 7), Color.WHITE));
        pieces.add(new Knight(new Position(1, 7), Color.WHITE));
        pieces.add(new Bishop(new Position(2, 7), Color.WHITE));
        pieces.add(new King(new Position(3, 7), Color.WHITE));
        pieces.add(new Queen(new Position(4, 7), Color.WHITE));
        pieces.add(new Bishop(new Position(5, 7), Color.WHITE));
        pieces.add(new Knight(new Position(6, 7), Color.WHITE));
        pieces.add(new Rook(new Position(7, 7), Color.WHITE));
        pieces.add(new Pawn(new Position(0, 6), Color.WHITE));
        pieces.add(new Pawn(new Position(1, 6), Color.WHITE));
        pieces.add(new Pawn(new Position(2, 6), Color.WHITE));
        pieces.add(new Pawn(new Position(3, 6), Color.WHITE));
        pieces.add(new Pawn(new Position(4, 6), Color.WHITE));
        pieces.add(new Pawn(new Position(5, 6), Color.WHITE));
        pieces.add(new Pawn(new Position(6, 6), Color.WHITE));
        pieces.add(new Pawn(new Position(7, 6), Color.WHITE));

        pieces.add(new Rook(new Position(0, 0), Color.BLACK));
        pieces.add(new Knight(new Position(1, 0), Color.BLACK));
        pieces.add(new Bishop(new Position(2, 0), Color.BLACK));
        pieces.add(new Queen(new Position(4, 0), Color.BLACK));
        pieces.add(new King(new Position(3, 0), Color.BLACK));
        pieces.add(new Bishop(new Position(5, 0), Color.BLACK));
        pieces.add(new Knight(new Position(6, 0), Color.BLACK));
        pieces.add(new Rook(new Position(7, 0), Color.BLACK));
        pieces.add(new Pawn(new Position(0, 1), Color.BLACK));
        pieces.add(new Pawn(new Position(1, 1), Color.BLACK));
        pieces.add(new Pawn(new Position(2, 1), Color.BLACK));
        pieces.add(new Pawn(new Position(3, 1), Color.BLACK));
        pieces.add(new Pawn(new Position(4, 1), Color.BLACK));
        pieces.add(new Pawn(new Position(5, 1), Color.BLACK));
        pieces.add(new Pawn(new Position(6, 1), Color.BLACK));
        pieces.add(new Pawn(new Position(7, 1), Color.BLACK));
    }

    public void initBoard(){
        initBoxes();
        initPieces();
        updateBoard(null, Color.BLACK); //white always starts
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

    public void setBoxes(Color enemyColor){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Box box = board[j][i];
                box.setCanBeOccupied(false);
                box.setState(State.FREE);
            }
        }
        for (Piece piece:pieces){
            if (!piece.isDead()){
                int x = piece.getPosition().getX();
                int y = piece.getPosition().getY();
                board[x][y].setState(State.OCCUPIED);
            }
        }

        ArrayList<Position> allEnemyMoves = getAllMovesFromColor(enemyColor);
        for (Position move:allEnemyMoves){
            int x = move.getX();
            int y = move.getY();
            board[x][y].setCanBeOccupied(true);
        }
    }

    public void updateBoard(ArrayList<Position> moves, Color enemyColor){
        setPieces();
        setBoxes(enemyColor);
        setDraws();
        drawTrail(moves);
        drawBoard();
    }

    public void setPieces(){
        //Assign every box it's piece
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                board[j][i].setPiece(null);
                board[j][i].setState(State.FREE);
            }
        }
        // j = x, i = y
        for(Piece piece : pieces){
            if (!piece.isDead()){
                int pieceX = piece.getPosition().getX();
                int pieceY = piece.getPosition().getY();
                board[pieceX][pieceY].setPiece(piece);
                board[pieceX][pieceY].setState(State.OCCUPIED);
            }
        }
    }

    public void setPiece(Piece piece, Position position){
        int newX = position.getX();
        int newY = position.getY();

        if (checkCastling(piece, newX)){
            setCastling(piece, newX);
        }

        Box box = board[newX][newY];

        if (box.isOccupied()){
            killPiece(box);
        }

        piece.setPosition(position);
        piece.setHasMoved(true);
        box.setPiece(piece);

        setPieces();
    }

    public void killPiece(Box box){
        box.getPiece().setDead(true);
    }

    public boolean checkCastling(Piece piece, int newX){
        if (piece instanceof King){
            int kingX = piece.getPosition().getX();
            return Math.abs(kingX - newX) > 1;
        }
        return false;
    }

    public void setCastling(Piece piece, int newX){
        for (Piece p: pieces){
            if (p.getColor() == piece.getColor() && p instanceof Rook){
                int kingX = piece.getPosition().getX();
                int y = piece.getPosition().getY();
                int rookX = p.getPosition().getX();
                if (newX<kingX && rookX<newX){
                    setPiece(p, new Position(newX+1, y));
                    break;
                }else if(newX>kingX && rookX>newX){
                    setPiece(p, new Position(newX-1, y));
                    break;
                }
            }
        }
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
                        if(box.isOccupied()){
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
                if(box.isOccupied()){
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
                if (!box.isOccupied()){
                    box.setDraw('0');
                }else{
                    box.setDraw('X');
                }
            }
        }
    }

    public ArrayList<Piece> getAvailablePieces(Color playerColor){
        boolean checking = true;
        ArrayList<Piece> AvailablePieces= new ArrayList<>();
        for(Piece piece : pieces){
            if (!piece.isDead() && piece.getColor() == playerColor && (!getMovesFromPiece(piece, checking).isEmpty() ||
                    !getKillsFromPiece(piece, checking).isEmpty())){
                AvailablePieces.add(piece);
            }
        }
        return AvailablePieces;
    }

    public int getAlivePieces(Color playerColor){
        int counter = 0;
        for(Piece piece : pieces){
            if (!piece.isDead() && piece.getColor() == playerColor){
                counter++;
            }
        }
        return counter;
    }

    public ArrayList<Position> getKillsFromPiece(Piece piece, boolean checking){
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

    public ArrayList<Position> getMovesFromPiece(Piece piece, boolean checking){
        ArrayList<ArrayList<Position>> allPossibleMoves = piece.getPossibleMoves(checking);
        ArrayList<Position> moves = new ArrayList<Position>();
        for (ArrayList<Position> possibleMoves: allPossibleMoves){
            moves.addAll(checkCollisions(possibleMoves, piece));
        }
        if (piece instanceof King){
            moves.addAll(getCastlingMoves(piece));
            moves = checkOccupiedBoxes(moves);
        }
        return moves;
    }

    public ArrayList<Position> getAllMovesFromColor(Color color){
        ArrayList<Position> allMoves = new ArrayList<Position>();
        for (Piece piece:pieces){
            if (!piece.isDead() && piece.getColor() == color){
                boolean checking = false;
                allMoves.addAll(getMovesFromPiece(piece, checking));
                allMoves.addAll(getKillsFromPiece(piece, checking));
            }
        }
        return allMoves;
    }

    public ArrayList<Position> checkCollisions(ArrayList<Position> possibleMoves, Piece piece){
        ArrayList<Position> moves = new ArrayList<Position>();
        for (Position position: possibleMoves){
            int x = position.getX();
            int y = position.getY();
            Box box = board[x][y];
            Piece pieceToCheck = box.getPiece();

            if(!box.isOccupied()) {
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

    public ArrayList<Position> checkCastlingMoves(Piece piece){
        ArrayList<Position> moves = new ArrayList<Position>();
        for (Piece p: pieces){
            if (!p.isDead() && !p.hasMoved() && p.getColor() == piece.getColor() && p instanceof Rook){
                int kingX = piece.getPosition().getX();
                int y = piece.getPosition().getY(); //it will be at the same level 100%
                int rookX = p.getPosition().getX();

                boolean queenSideCastling = rookX - kingX < 0;
                for (int i = 1; i < Math.abs(rookX-kingX); i++) {
                    int x = queenSideCastling ? kingX + Math.negateExact(i) : kingX+i;
                    if (board[x][y].isOccupied()){
                        break;
                    }
                    if (i == Math.abs(rookX-kingX)-1){
                        //end of the for
                        x = queenSideCastling ? kingX-2 : kingX+2;
                        moves.add(new Position(x, y));
                    }
                }
            }
        }
        return moves;
    }

    public ArrayList<Position> checkOccupiedBoxes(ArrayList<Position> kingMoves){
        ArrayList<Position> checkedMoves = new ArrayList<Position>();
        for (Position kingMove: kingMoves){
            int x = kingMove.getX();
            int y = kingMove.getY();
            if (!board[x][y].canBeOccupied()){
                checkedMoves.add(kingMove);
            }
        }
        return checkedMoves;
    }

    public boolean isKingInCheck(Color enemyColor){
        ArrayList<Position> enemyMoves = getAllMovesFromColor(enemyColor);
        for (Position enemyMove: enemyMoves){
            int x = enemyMove.getX();
            int y = enemyMove.getY();
            Box box = board[x][y];
            if (!board[x][y].isOccupied() && box.getPiece() instanceof King){
                return true;
            }
        }
        return false;
    }

    public boolean checkStalemate(Color playerColor) {
        return getAvailablePieces(playerColor).isEmpty();
    }
}
