package chess;

public class Position {
    public int row;
    public int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Converts chess notation like "E2" into usable position.
    public static Position fromChessNotation(String notation) {
        notation = notation.toUpperCase();
        int col = notation.charAt(0) - 'A';
        int row = 8 - Character.getNumericValue(notation.charAt(1));
        return new Position(row, col);
    }

    // Converts position back to readable chess notation.
    @Override
    public String toString() {
        return "" + (char) ('A' + col) + (8 - row);
    }
}
