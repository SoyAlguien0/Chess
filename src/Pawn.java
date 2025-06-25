import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved = false;
    private int[][] possibleKillDirs =  {   {-1, -1},
                                            {1, -1},
                                        };

    public Pawn(Position position, int color){
        super(position,color);
        this.name = super.getDrawName("♙");
    }

    @Override
    public List<Position> getPossibleMoves(int x, int y){
        List<Position> possibleMoves = new ArrayList<Position>();
        if (hasMoved && super.validMove(x,(y+1)*this.direction)){
            possibleMoves.add(new Position(x, (y+1)*this.direction));
        }else if (!hasMoved && super.validMove(x,(y+2)*this.direction)) {
            possibleMoves.add(new Position(x, (y+2)*this.direction));
        }else{
            throw new IllegalArgumentException("Invalid move, x="+x+", y="+y);
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
