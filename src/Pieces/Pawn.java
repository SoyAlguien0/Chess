package Pieces;

import Board.Position;
import java.util.ArrayList;
import Consts.Color;

public class Pawn extends Piece {
    final private Position[] possibleKillDirs;
    final private Position[] possibleDirs;

    public Pawn(Position position, Color color){
        super(position,color);
        this.name = super.getDrawName('♟');
        this.possibleDirs = new Position[]{
                new Position(0, 1*this.direction),
                new Position(0, 2*this.direction)
        };
        this.possibleKillDirs = new Position[]{
                new Position(-1, this.direction),
                new Position( 1, this.direction)
        };
    }

    @Override
    public ArrayList<ArrayList<Position>> getPossibleMoves(){
        Position pos = this.position;
        ArrayList<Position> possibleMoves = new ArrayList<>();
        ArrayList<ArrayList<Position>> allPossibleMoves = new ArrayList<ArrayList<Position>>();
        if (!hasMoved){
            for (Position possibleDir: possibleDirs){
                possibleMoves.add(pos.sumPosition(possibleDir));
            }
        }
        else if (hasMoved
                && super.validMove(pos.sumPosition(possibleDirs[0])))
        {
            possibleMoves.add(pos.sumPosition(possibleDirs[0]));
        }
        allPossibleMoves.add(possibleMoves);
        return  allPossibleMoves;
    }

    @Override
    public ArrayList<ArrayList<Position>> getPossibleKills(){
        Position pos = this.position;
        ArrayList<Position> possibleKills = new ArrayList<>();
        ArrayList<ArrayList<Position>> allPossibleKills = new ArrayList<ArrayList<Position>>();
        for (Position possibleKillDir : possibleKillDirs) {
            if (super.validMove(pos.sumPosition(possibleKillDir))) {
                possibleKills.add(pos.sumPosition(possibleKillDir));
            }
        }
        allPossibleKills.add(possibleKills);
        return  allPossibleKills;
    }
}
