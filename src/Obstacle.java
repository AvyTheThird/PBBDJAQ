public class Obstacle {

    private int row;
    private int col;

    public Obstacle(int row, int col) { //obstacle constructor
        this.row = row;
        this.col = col;
    }

    public void place(Grid location) { //places a "0" at row, col and creates a barrier
        location.setCell(row, col, 'O');
    }
}
