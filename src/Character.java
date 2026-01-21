public class Character {

    protected int row;
    protected int col;
    protected char symbol;


    public Character(int row, int col, char symbol) {       //character constructor
        this.row = row;
        this.col = col;
        this.symbol = symbol;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getSymbol() {
        return symbol;
    }
}
