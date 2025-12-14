package Pieces;
import Board.Position;
import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(Position position, int color){
        super(position,color);
        this.name = super.getDrawName('♜');
    }

    @Override
    public ArrayList<ArrayList<Position>> getPossibleMoves(boolean checking) {
        ArrayList<Position> possibleMovesX = new ArrayList<Position>();
        ArrayList<Position> possibleMovesNX = new ArrayList<Position>();
        ArrayList<Position> possibleMovesY = new ArrayList<Position>();
        ArrayList<Position> possibleMovesNY = new ArrayList<Position>();
        ArrayList<ArrayList<Position>> possibleMoves = new ArrayList<ArrayList<Position>>();
        for (int i = 1; i < 8; i++) {
            int x = this.position.getX();
            int y = this.position.getY();
            Position position = new Position(x, y+i);
            if (super.validMove(position)) {
                possibleMovesY.add(position);
            }
            position = new Position(x, y-i);
            if (super.validMove(position)) {
                possibleMovesNY.add(position);
            }
            position = new Position(x+i, y);
            if (super.validMove(position)) {
                possibleMovesX.add(position);
            }
            position = new Position(x-i, y);
            if (super.validMove(position)) {
                possibleMovesNX.add(position);
            }
        }
        possibleMoves.add(possibleMovesX);
        possibleMoves.add(possibleMovesNX);
        possibleMoves.add(possibleMovesY);
        possibleMoves.add(possibleMovesNY);
        return possibleMoves;
    }
}
