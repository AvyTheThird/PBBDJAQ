public class Grid {

    private char[][] grid;

    public Grid(int rows, int cols) {           //constructor for the grid
        grid = new char[rows][cols];

        // Fill with empty spaces
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = '.';
            }
        }
    }

    public char getCell(int row, int col) {     //the cell that the player is on based on their row and column
        return grid[row][col];
    }  //returns the row and col of player

    public void setCell(int row, int col, char value) {
        grid[row][col] = value;
    } //sets teh cell and row of player

    public int getRows() {
        return grid.length;
    }

    public int getCols() {
        return grid[0].length;
    }

    public void display() {     //displays the grid
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
