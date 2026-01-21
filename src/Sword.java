public class Sword extends Item {


    private String itemName;

    public Sword(int row, int col, String itemName) {
        super(row, col);

        this.itemName = itemName;
    }

    public void place(Grid location) {
        location.setCell(row, col, 'W'); //Sword gets set to W on the Grid
    }

}
