public class Item {

    public int row;         //row for the item
    public int col;         //column for the item

    public Item(int row, int col) {             //constructor for the item
        this.row = row;
        this.col = col;
    }

    public void placeItem(Grid location) {
        location.setCell(row, col, 'I');
    }       //places the item on the grid
}
