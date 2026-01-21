import java.util.Scanner;
import java.util.Random;

public class ProjectRunner {

     static void main(String[] args) {

         String[] shopItems = {"health potion", "gigasword", "gigaarmor"}; //shop items

        Grid grid = new Grid(7, 10);//creates grid
        Player player = new Player();
        grid.setCell(2, 2, 'P'); //spawns player at 2,2

        Obstacle obstacle = new Obstacle(1, 1); //obstacle at 1,1
        obstacle.place(grid);

         Obstacle obstacle2 = new Obstacle(3, 3); //obstacle at 1,1
         obstacle2.place(grid);

         Boss boss = new Boss(6, 1);  //boss at 6,7
         boss.place(grid);

        Sword sharp = new Sword(6, 9, "Sword"); //sword at 6,9
        sharp.place(grid);
        Shop market = new Shop(4, 5, shopItems); //spawns shop at 4,5
         market.place(grid);

        Scanner scanner = new Scanner(System.in);

        grid.display();
        System.out.println();
        while (true) {
            System.out.print("Move (w/a/s/d or quit): ");
            String command = scanner.nextLine().toLowerCase(); //sets user input to lowercase

            if (command.equals("quit")) {
                break;
            }

            player.move(command, grid, market);
            grid.display();
        }



        scanner.close();
        // update grid

    }
}

