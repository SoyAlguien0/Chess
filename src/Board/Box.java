package Board;

import Pieces.Piece;
import Consts.State;

public class Box {
    private Piece piece;
    private int color;
    private char draw;
    private State state = State.FREE;
    private boolean canBeOccupied = false;

    public Box(Piece piece, int color){
        this.piece = piece;
        this.color = color;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public char getDraw() {
        return draw;
    }

    public void setDraw(char draw) {
        this.draw = draw;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean canBeOccupied() {
        return canBeOccupied;
    }

    public void setCanBeOccupied(boolean canBeOccupied) {
        this.canBeOccupied = canBeOccupied;
    }

    public boolean isOccupied(){
        return state == State.OCCUPIED;
    }
}
