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
                new Position(-1, 1*this.direction),
                new Position( 1, 1*this.direction)
        };
    }

    @Override
    public ArrayList<Position> getPossibleMoves(boolean checking){
        Position pos = this.position;
        ArrayList<Position> possibleMoves = new ArrayList<Position>();
        if (hasMoved &&
            super.validMove(pos.sumPosition(possibleDirs[0]))
        )
        {
            possibleMoves.add(pos.sumPosition(possibleDirs[0]));
            if(checking){
                return possibleMoves;
            }
        }
        else if (!hasMoved
                && super.validMove(pos.sumPosition(possibleDirs[1])))
        {
            possibleMoves.add(pos.sumPosition(possibleDirs[1]));
            if(checking){
                return possibleMoves;
            }
        }

        return  possibleMoves;
    }

    @Override
    public ArrayList<Position> getPossibleKills(boolean checking){
        Position pos = this.position;
        ArrayList<Position> possibleKills = new ArrayList<Position>();
        for (int i = 0; i < possibleKillDirs.length; i++){
            if (super.validMove(pos.sumPosition(possibleKillDirs[i]))) {
                possibleKills.add(pos.sumPosition(possibleKillDirs[i]));
                if(checking){
                    return possibleKills;
                }
            }
        }
        return  possibleKills;
    }
}
