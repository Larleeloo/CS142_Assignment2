import java.util.Random;
import java.util.Scanner;
//can initHuman and initcomputer return a human/computer?
public class Game {

    private Scanner scan = new Scanner(System.in);
    private Human player1 = new Human("Default Dude");
    private Computer player2 = new Computer("Default Bot");
    private String[] smartNames = {"Eniac","Brainiac","HAL","Deep Thought","Sky Net"};
    private String[] dumbNames = {"Boris","Dr Giggles","Speak and Spell","Marvin","Sid"};
    private boolean gameOver = false;

    private int heapOfMarbles = new Random().nextInt(0,Integer.MAX_VALUE);

    public Game(Scanner scanIn){
        scan = scanIn;
        initializeGame();
    }

    private void initializeGame(){ //run the other methods to start the game.
        player1 = initializeHuman();
        player2 = initializeComputer();
        rules();
        plaGame();
    }
    private Human initializeHuman(){
        String name;
        System.out.println("Please enter an integer to select from the following smart names: ");
        for (int i = 0; i < smartNames.length; i++) {
            System.out.println(i + " " + smartNames[i]);
        }
        int selection = 0;
        while(selection == 0){
            switch (selection) {
                case 0:
                    try {
                        selection = Integer.parseInt(scan.nextLine());
                        if (selection > 4 || selection < 0) {
                            System.out.println("Invalid choice");
                        } else {
                            player1.setName(smartNames[selection]);
                            System.out.println("You chose " + player1.getName());
                            break;
                        }
                    }
                    catch (Exception e){
                        System.out.println("Please *actually* enter an integer.");
                    }
                default:
                    selection = 0;
            };
        }
        System.out.println("Because you are human... You may prefer the following options. Enter -1 to keep your original name");
        for (int i = 0; i < dumbNames.length; i++) {
            System.out.println(i + " " + dumbNames[i]);
        }
        selection = 0;
        while(selection == 0){
            switch (selection) {
                case 0:
                    try {
                        selection = Integer.parseInt(scan.nextLine());
                        if (selection > 4 || selection < 0) {
                            if(selection != -1) {
                                System.out.println("Invalid choice");
                            }
                        } else {
                            player1.setName(dumbNames[selection]);
                            System.out.println("You chose " + player1.getName());
                            break;
                        }
                    }
                    catch (Exception e){
                        System.out.println("Please *actually* enter an integer.");
                    }
                case -1:
                    System.out.println("You must really like that name");
                    break;
                default:
                    selection = 0;
            };
        }
        return player1;
    }
    private Computer initializeComputer(){
        System.out.println("Who would you like to play against?");
        return player2;
    }
    private void rules() { //Display the rules of the game
        System.out.println("*Rules*");
    }
    private boolean flipCoin() { //randomly chose who goes first
        return new Random().nextBoolean();
    }
    private void plaGame(){
        boolean doesHumanStart = flipCoin();
        while(gameOver) {
            if (doesHumanStart) {
                takeTurn(doesHumanStart);
            } else {
                takeTurn(doesHumanStart);
            }
        }
    }
    private int takeTurn(boolean doesHumanStart) { //alternate based on the turn counter
        int turnCounter = 0;
        if(doesHumanStart){
            takeHumanTurn();
            takeComputerTurn(player2);
        }
        else {
            takeComputerTurn(player2);
            takeHumanTurn();
        }
        turnCounter++;
        return turnCounter;
    }
    private void takeHumanTurn(){
        heapOfMarbles = player1.takeMarbles(heapOfMarbles);
    }
    private void takeComputerTurn(Computer player2){
        heapOfMarbles = player2.takeMarbles(heapOfMarbles);
    }


}
