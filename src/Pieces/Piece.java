package Pieces;

import java.util.ArrayList;
import Board.Position;
import Consts.Color;

public abstract class Piece {
    protected Position position;
    protected Color color;
    protected char name;
    protected int direction ;
    protected boolean hasMoved = false;
    protected boolean hasVariousTargets = false;
    protected boolean isDead = false;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
        this.direction = this.color == Color.WHITE ? -1:1;
    }

    public boolean validMove(Position pos){
        int x = pos.getX();
        int y = pos.getY();
        return (x<8 && x>=0) && (y>=0 && y<8);
    }

    public abstract ArrayList<ArrayList<Position>> getPossibleMoves();

    public ArrayList<ArrayList<Position>> getPossibleKills(){
        return getPossibleMoves();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public char getName() {
        return name;
    }

    public char getDrawName(char name) {
        return (this.color == Color.WHITE ? name : (char)(name-6));
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public boolean hasVariousTargets() {
        return hasVariousTargets;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    @Override
    public String toString() {
        return "{" +
                "name: '" + name + '\'' +
                ", position: " + position +
                '}';
    }
}
