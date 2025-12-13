package Pieces;

import Board.Position;

import java.util.ArrayList;

public class Rook extends Piece{

    Rook(Position position, int color){
        super(position,color);
    }

    @Override
    public ArrayList<Position> getPossibleMoves(boolean checking) {
        ArrayList<Position> possibleMoves = new ArrayList<Position>();
        for (int i = 0; i < 8; i++) {
            int x = this.position.getX();
            int y = this.position.getY();
            Position position = new Position(x, y+i);
            if (super.validMove(position)) {
                possibleMoves.add(position);
            }
            position = new Position(x, y-i);
            if (super.validMove(position)) {
                possibleMoves.add(position);
            }
            position = new Position(x+i, y);
            if (super.validMove(position)) {
                possibleMoves.add(position);
            }
            position = new Position(x-i, y);
            if (super.validMove(position)) {
                possibleMoves.add(position);
            }
        }
        return null;
    }
}
