package Pieces;

import Board.Position;

import java.util.ArrayList;

public class King extends Piece{

    public King(Position position, int color){
        super(position,color);
        this.name = super.getDrawName('♚');
        this.hasVariousTargets = true;
    }

    @Override
    public ArrayList<ArrayList<Position>> getPossibleMoves(boolean checking){
        int x = this.position.getX();
        int y = this.position.getY();
        ArrayList<Position> possibleMoves = new ArrayList<>();
        ArrayList<ArrayList<Position>> allPossibleMoves = new ArrayList<ArrayList<Position>>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Position position = new Position(x+i, y+j);
                if (super.validMove(position)){
                    possibleMoves.add(position);
                }
            }
        }
        allPossibleMoves.add(possibleMoves);
        return  allPossibleMoves;
    }
}
