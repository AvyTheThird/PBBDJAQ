public class Location {

        public int row;
        public int col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void place(Grid location) {
            location.setCell(row, col, 'L');
        }
}
