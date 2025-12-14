package Pieces;

import Board.Position;

import java.util.ArrayList;

public class Knight extends Piece{
    final private Position[] possibleDirs;

    public Knight(Position position, int color){
        super(position,color);
        this.name = super.getDrawName('♞');
        this.hasVariousTargets = true;
        this.possibleDirs = new Position[]{
                new Position(-1, -2),
                new Position(1, -2),
                new Position(2, -1),
                new Position(2, 1),
                new Position(-1, 2),
                new Position(1, 2),
                new Position(-2, -1),
                new Position(-2, 1),
        };
    }

    @Override
    public ArrayList<ArrayList<Position>> getPossibleMoves(boolean checking){
        Position position = this.position;
        ArrayList<Position> possibleMoves = new ArrayList<>();
        ArrayList<ArrayList<Position>> allPossibleMoves = new ArrayList<ArrayList<Position>>();

        for (Position positionDir:this.possibleDirs){
            positionDir = position.sumPosition(positionDir);
            if (super.validMove(positionDir)){
                possibleMoves.add(positionDir);
            }
        }

        allPossibleMoves.add(possibleMoves);
        return  allPossibleMoves;
    }
}
