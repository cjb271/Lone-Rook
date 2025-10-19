package chess.pieces;

import chess.Position;

public class Pawn extends Piece {// Inherits from Piece class
    public Pawn(String color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        return color.equals("White") ? "wp" : "bp";
    }
}
