import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved = false;
    public Pawn(Position position, int color){
        super(position,color);
        this.name = super.getDrawName("♙");
    }

    @Override
    public List<Position> getPossibleMoves(int x, int y){
        List<Position> possibleMoves = new ArrayList<Position>();
        return  possibleMoves;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
