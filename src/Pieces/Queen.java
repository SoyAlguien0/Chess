package Pieces;

import Board.Position;
import Consts.Color;

import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(Position position, Color color){
        super(position,color);
        this.name = super.getDrawName('♛');
    }

    @Override
    public ArrayList<ArrayList<Position>> getPossibleMoves() {
        ArrayList<Position> possibleMovesX = new ArrayList<Position>();
        ArrayList<Position> possibleMovesNX = new ArrayList<Position>();
        ArrayList<Position> possibleMovesY = new ArrayList<Position>();
        ArrayList<Position> possibleMovesNY = new ArrayList<Position>();
        ArrayList<Position> possibleMovesXY = new ArrayList<Position>();
        ArrayList<Position> possibleMovesNXNY = new ArrayList<Position>();
        ArrayList<Position> possibleMovesXNY = new ArrayList<Position>();
        ArrayList<Position> possibleMovesNXY = new ArrayList<Position>();
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
            position = new Position(x+i, y+i);
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
        possibleMoves.add(possibleMovesX);
        possibleMoves.add(possibleMovesNX);
        possibleMoves.add(possibleMovesY);
        possibleMoves.add(possibleMovesNY);
        possibleMoves.add(possibleMovesXY);
        possibleMoves.add(possibleMovesNXNY);
        possibleMoves.add(possibleMovesXNY);
        possibleMoves.add(possibleMovesNXY);
        return possibleMoves;
    }
}
