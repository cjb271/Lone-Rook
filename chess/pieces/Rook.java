package chess.pieces;

import chess.Position;

public class Rook extends Piece {// Inherits from Piece class
    public Rook(String color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "wR" : "bR";
    }
}
