public class Boss {
    public int row;
    public int col;

    public Boss(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void place(Grid location) {
        location.setCell(row, col, 'B');
    }



}
