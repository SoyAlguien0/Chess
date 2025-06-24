import java.util.List;

public abstract class Piece {
    protected Position position;
    protected int color; //0 = white, 1 = black
    protected String name;
    protected boolean movedX = false;
    protected boolean movedY = false;

    public Piece(Position position, int color) {
        this.position = position;
        this.color = color;
    }

    public abstract List<Position> getPossibleMoves(int x, int y);
    public List<Position> getPossibleKills(int x, int y){
        return getPossibleMoves(x, y);
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

    public String getName() {
        return name;
    }

    public String getDrawName(String name) {
        return (this.color == 0 ? "♙" : "♟");
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
