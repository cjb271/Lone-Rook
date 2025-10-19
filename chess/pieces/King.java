package chess.pieces;

import chess.Position;

public class King extends Piece {// Inherits from Piece class
    public King(String color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "wK" : "bK";
    }
}
