package Pieces;

import Board.Position;

import java.util.ArrayList;
import Consts.Color;

public class Bishop extends Piece{
    public Bishop(Position position, Color color){
        super(position,color);
        this.name = super.getDrawName('♝');
    }

    @Override
    public ArrayList<ArrayList<Position>> getPossibleMoves() {
        ArrayList<Position> possibleMovesXY = new ArrayList<Position>();
        ArrayList<Position> possibleMovesNXNY = new ArrayList<Position>();
        ArrayList<Position> possibleMovesXNY = new ArrayList<Position>();
        ArrayList<Position> possibleMovesNXY = new ArrayList<Position>();
        ArrayList<ArrayList<Position>> possibleMoves = new ArrayList<ArrayList<Position>>();
        for (int i = 1; i < 8; i++) {
            int x = this.position.getX();
            int y = this.position.getY();
            Position position = new Position(x+i, y+i);
            if (super.validMove(position)) {
                possibleMovesXY.add(position);
            }
            position = new Position(x-i, y-i);
            if (super.validMove(position)) {
                possibleMovesNXNY.add(position);
            }
            position = new Position(x+i, y-i);
            if (super.validMove(position)) {
                possibleMovesXNY.add(position);
            }
            position = new Position(x-i, y+i);
            if (super.validMove(position)) {
                possibleMovesNXY.add(position);
            }
        }
        possibleMoves.add(possibleMovesXY);
        possibleMoves.add(possibleMovesNXNY);
        possibleMoves.add(possibleMovesXNY);
        possibleMoves.add(possibleMovesNXY);
        return possibleMoves;
    }
}
