import java.util.Scanner;
import java.util.Random;

public class Player extends Character {

    //creates variables for the player
    private int health;
    private int damageDealing;
    private int money;
    private double experience;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random RNG = new Random();

    public Player() {
        super(2, 2, 'P');
        this.health = 20 + (int) Math.pow(1.1, experience);
        this.damageDealing = 1 + (int) Math.pow(1.01, experience);
        this.money = 0;
    }

    // Added Shop market as a parameter so the player can print it
    public void move(String command, Grid grid, Shop market) {

        int newRow = row;
        int newCol = col;

        switch (command) { //used the help of GPT for this part to implement input
            case "w": newRow--; break;
            case "s": newRow++; break;
            case "a": newCol--; break;
            case "d": newCol++; break;
            case "h":
                System.out.println("You have: " + money + " gold coins");
                System.out.println("Your health is" + " " + health);
                System.out.println("Your damage is" + " " + damageDealing);
                return;
            default:
                System.out.println("Invalid input, use WASD. Press H to see stats");//lets user know imput is invalid
                return;
        }

        // Bounds check
        if (newRow < 0 || newRow >= grid.getRows() || newCol < 0 || newCol >= grid.getCols()) {
            System.out.println("You hit the edge of the map.");
            return;
        }

        char cell = grid.getCell(newRow, newCol);

        if (cell == 'O') {
            System.out.println("There is an obstacle blocking the way.");//lets user know the path is blocked
            return;
        }

        //lets player shop
        if (cell == 'S') {
            System.out.println("You have entered the shop.\nWelcome!");
            System.out.println(market);
            System.out.println("What would you like to buy? Your options are:");
            System.out.println("GigaSword +10damage, 30 coins. Press 1");
            System.out.println("HealthPotion +10hp, 10 coins. Press 2");
            System.out.println("GigaArmor +40hp, 40 coins. Press 3");
            String query = scanner.nextLine();
            if (query.equals("1") && money >= 30) {
                System.out.println("You got a GigaSword!");
                damageDealing += 10;
                money -= 30;
                System.out.println("You now deal " + damageDealing + " damage");

            } else if (query.equals("3") && money >= 40) {
                System.out.println("You got GigaArmor!");
                health += 30;
                money -= 40;
                System.out.println("Your health is now: " + health);

            } else if (query.equals("2") && money >= 10) {
                System.out.println("You got HealthPotion!");
                health += 10;
                money -= 10;
                System.out.println("Your health is now: " + health);
            } else if (query.equals("3") && money <= 40) {
                System.out.println("you're broke, get out of my shop!");
                newRow--;

            } else if (query.equals("1") && money <= 30) {
                System.out.println("you're broke, get out of my shop!");
                newRow--;

            } else if (query.equals("2") && money <= 10) {
                System.out.println("you're broke, get out of my shop!");
                newRow--;

            }


            return;
        }

        //lets player pick up sword
        if (cell == 'W') {
            System.out.println("You got a sword!\nStrength: 5");
            damageDealing += 5;
            grid.setCell(newRow, newCol, '.');
        }

        if (cell == 'B') {
            bossCombat();
        }

        //adds a random chance to encounter an enemy when moving
        if (cell == '.' && RNG.nextInt(10) == 0) { // 10% chance
            System.out.println("You encountered an enemy!");
            combat();
        }

        // Move the player
        grid.setCell(row, col, '.');
        row = newRow;
        col = newCol;
        grid.setCell(row, col, symbol);
    }

    public void combat() {

        //declares enemy variables randomly
        int eHealth = RNG.nextInt(6) + 5;   // Enemy health 5-10
        int eDamage = RNG.nextInt(4) + 1;   // Enemy damage 1-4
        int eMoney = RNG.nextInt(9) + 1;   // Enemy money dropped 1-10
        int eExperience = RNG.nextInt(9) + 1;

        //lets player know comabt started
        System.out.println("Combat begins!");
        System.out.println("Enemy Health: " + eHealth);
        System.out.println("Your Damage: " + damageDealing);

        while (eHealth > 0 && health > 0) {

            // Enemy turn
            if (RNG.nextInt(2) == 0) {
                health -= eDamage;
                System.out.println("Enemy attacks for " + eDamage);
            } else {
                System.out.println("Enemy hesitates.");
            }

            // Player turn
            System.out.println("Type 't' to attack:");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("t")) {
                eHealth -= damageDealing;
                System.out.println("You deal " + damageDealing + " damage.");
            }

            System.out.println("Your Health: " + health);//displayes player health
            System.out.println("Enemy Health: " + eHealth);//dislayes enemy health
            System.out.println("-------------------");
        }

        if (health <= 0) {
            System.out.println("You were defeated!");//let player know theyve died
        } else {
            System.out.println("Enemy defeated! Press H  to check your money.");//lets player know how to check money
            money += eMoney;
            experience += eExperience;
        }
    }

    public void bossCombat() {

        //a boss? EGADS!!!
        int bHealth = 100;   // Boss health
        int bDamage = 6;   // Boss damage
        int bMoney = 100;   // Boss money dropped

        System.out.println("Your final reckoning begins...");
        System.out.println("???? Health: " + bHealth);
        System.out.println("Your Damage: " + damageDealing);

        while (bHealth > 0 && health > 0) {

            // Enemy turn
            if (RNG.nextInt(2) == 0) { //if attacks
                health -= bDamage;
                System.out.println("???? turns to the screen and looks beyond." + "\n" + "You lose " + bDamage + "HP");

                if (RNG.nextInt(2) == 0){  //boss special move 1
                    System.out.println("???? finds fortitude in this hollow shell of code...");
                    bHealth += 2;
                } else { //boss special 2
                    System.out.println("???? looks beyond, drawing a new blade from a plane beyond your own...");
                    bDamage += 1;
                }
            } else {
                System.out.println("???? hesitates. His foul presence is enough to siphon your will to live...");
                health -= 1;
            }

            // Player turn
            System.out.println("???? is strong. Do you choose to live, or to die?" + "\n" + "Press 't' to live:");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("t")) {
                bHealth -= damageDealing;
                System.out.println("You deal " + damageDealing + " damage.");
            }

            //shows
            System.out.println("Your Health: " + health);
            System.out.println("????? Health: " + bHealth);
            System.out.println("---/-/---/----/--/-");//good greif hes cracked the screen!
        }

        if (health <= 0) {
            System.out.println("Death");//lets player know they've been defeated
        } else {
            System.out.println("The world shines brighter. Make a difference.");//lets player check money
            money += bMoney;
            experience += 100;
        }//gee wilikers
    }


}
