package chess.pieces;

import chess.Position;

public class Queen extends Piece {// Inherits from the Piece class
    public Queen(String color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "wQ" : "bQ";
    }
}
