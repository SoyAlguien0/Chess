import java.util.List;

public abstract class Pice {
    Position position;
    int color;
    boolean movedX = false;
    boolean movedY = false;

    public Pice(Position position, int color) {
        this.position = position;
        this.color = color;
    }

    public abstract List<Position> getPossibleMoves();
    public List<Position> getPossibleDeaths(){
        return getPossibleMoves();
    };


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isMovedX() {
        return movedX;
    }

    public void setMovedX(boolean movedX) {
        this.movedX = movedX;
    }

    public boolean isMovedY() {
        return movedY;
    }

    public void setMovedY(boolean movedY) {
        this.movedY = movedY;
    }
}
