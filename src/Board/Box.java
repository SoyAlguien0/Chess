package Board;

import Pieces.Piece;

public class Box {
    private Piece piece;
    private int color;
    private char draw;

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
}
