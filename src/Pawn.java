import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved = false;
    final private Position[] possibleKillDirs = {
            new Position(-1, -1),
            new Position( 1, -1)
    };

    public Pawn(Position position, int color){
        super(position,color);
        this.name = super.getDrawName("♙");
        this.direction = this.color == 0 ? 1:-1;
        this.possibleDirs = new Position[]{
                new Position(0, 1*this.direction),
                new Position(0, 2*this.direction)
        };
    }

    @Override
    public List<Position> getPossibleMoves(boolean checking){
        Position pos = this.position;
        List<Position> possibleMoves = new ArrayList<Position>();
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
            this.hasMoved = true;
            if(checking){
                return possibleMoves;
            }
        }

        return  possibleMoves;
    }

    @Override
    public List<Position> getPossibleKills(boolean checking){
        Position pos = this.position;
        List<Position> possibleMoves = new ArrayList<Position>();
        for (int i = 0; i < possibleKillDirs.length; i++){
            if (hasMoved &&
                    super.validMove(pos.sumPosition(possibleDirs[i]))
            ) {
                possibleMoves.add(pos.sumPosition(possibleDirs[i]));
                if(checking){
                    return possibleMoves;
                }
            }
        }
        return  possibleMoves;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
