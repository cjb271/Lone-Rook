package chess.pieces;

import chess.Position;

public class Knight extends Piece {// Inherits from the Piece class
    public Knight(String color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "wN" : "bN";
    }
}
