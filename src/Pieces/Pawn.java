package Pieces;
import Board.Position;
import java.util.ArrayList;

public class Pawn extends Piece {
    final private Position[] possibleKillDirs;
    final private Position[] possibleDirs;

    public Pawn(Position position, int color){
        super(position,color);
        this.name = super.getDrawName('♟');
        this.direction = this.color == 0 ? 1:-1;
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
    public ArrayList<ArrayList<Position>> getPossibleMoves(boolean checking){
        Position pos = this.position;
        ArrayList<Position> possibleMoves = new ArrayList<>();
        ArrayList<ArrayList<Position>> allPossibleMoves = new ArrayList<ArrayList<Position>>();
        if (!hasMoved){
            for (Position possibleDir: possibleDirs){
                possibleMoves.add(pos.sumPosition(possibleDir));
                if(checking){
                    allPossibleMoves.add(possibleMoves);
                    return allPossibleMoves;
                }
            }
        }
        else if (hasMoved
                && super.validMove(pos.sumPosition(possibleDirs[1])))
        {
            possibleMoves.add(pos.sumPosition(possibleDirs[1]));
            if(checking){
                allPossibleMoves.add(possibleMoves);
                return allPossibleMoves;
            }
        }
        allPossibleMoves.add(possibleMoves);
        return  allPossibleMoves;
    }

    @Override
    public ArrayList<ArrayList<Position>> getPossibleKills(boolean checking){
        Position pos = this.position;
        ArrayList<Position> possibleKills = new ArrayList<>();
        ArrayList<ArrayList<Position>> allPossibleKills = new ArrayList<ArrayList<Position>>();
        for (Position possibleKillDir : possibleKillDirs) {
            if (super.validMove(pos.sumPosition(possibleKillDir))) {
                possibleKills.add(pos.sumPosition(possibleKillDir));
                if (checking) {
                    allPossibleKills.add(possibleKills);
                    return allPossibleKills;
                }
            }
        }
        allPossibleKills.add(possibleKills);
        return  allPossibleKills;
    }
}
