package chess.pieces;

import chess.Position;

public abstract class Piece {
    protected String color;// Black or White
    protected Position position;

    // Constructor
    public Piece(String color, Position position) {
        this.color = color;
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract String getSymbol();// Implemented by subclasses
}
