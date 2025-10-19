package chess.pieces;

import chess.Position;

public class Bishop extends Piece {// Inherits from the Piece class
    public Bishop(String color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "wB" : "bB";
    }
}
