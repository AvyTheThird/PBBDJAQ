public class Shop extends Location {

    private String[] sales;

    public Shop(int row, int col, String[] sales) {
        super(row, col);
        this.sales = sales;
    }

    // Places the shop on the grid using 'S'
    public void place(Grid grid) {
        grid.setCell(row, col, 'S');
    }

    @Override
    public String toString() {
        String result = "Shop Items: ";
        for (String item : sales) {
            result += item + " ";
        }
        return result;
    }
}
