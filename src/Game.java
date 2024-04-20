import java.util.Random;
import java.util.Scanner;
//can initHuman and initcomputer return a human/computer?
public class Game {

    private Scanner scan = new Scanner(System.in);
    private Human player1 = new Human("Default Dude");
    private Computer player2 = new Computer("Default Bot");

    int aiType;
    private String[] smartNames = {"Eniac","Brainiac","HAL","Deep Thought","Sky Net"};
    private String[] dumbNames = {"Boris","Dr Giggles","Speak and Spell","Marvin","Sid"};
    private int heapOfMarbles = new Random().nextInt(10,100);
    private boolean doesHumanStart = false;
    private boolean gameOver = false;

    private int turnCounter = 0;


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
        System.out.println("Please enter an integer to select from the following smart names: \n(This will be your name, player 1)");
        for (int i = 0; i < smartNames.length; i++) {
            System.out.println(i + 1 + " " + smartNames[i]);
        }
        int selection = 0;
        while(selection == 0){
            switch (selection) {
                case 0:
                    try {
                        selection = Integer.parseInt(scan.nextLine());
                        if (selection > 5 || selection < 1) {
                            System.out.println("Invalid choice");
                        } else {
                            player1.setName(smartNames[selection - 1]);
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
            System.out.println(i + 1 + " " + dumbNames[i]);
        }
        selection = 0;
        while(selection == 0){
            switch (selection) {
                case 0:
                    try {
                        selection = Integer.parseInt(scan.nextLine());
                        if(selection == -1){
                            System.out.println("You must really like that name");
                            System.out.println("You chose " + player1.getName());
                            break;
                        }
                        else if (selection > 5 || selection < 1) {
                            System.out.println("Invalid choice");
                            selection = 0;
                            break;
                        } else {
                            player1.setName(dumbNames[selection - 1]);
                            System.out.println("You chose " + player1.getName());
                            break;
                        }
                    }
                    catch (Exception e){
                        System.out.println("Please *actually* enter an integer.");
                        selection = 0;
                        break;
                    }
                case -1:
                    break;
                default:
                    selection = 0;
            };
        }
        return player1;
    }
    private Computer initializeComputer(){
        System.out.println("Who would you like to play against?\nThis is your opponent... choose wisely");
        System.out.println("Enter an integer 1-10");
        String[] allNames = new String[10];
        int j = 9;
        for(int i = 0; i < 5; i ++){
            allNames[i] = dumbNames[i];
            allNames[j] = smartNames[j - 5];
            j--;
        }
        for(int i = 0; i < 10; i++){
            System.out.println(i+1 + ": " + allNames[i]);
        }
        int selection = 0;
        while(selection == 0){
            switch (selection) {
                case 0:
                    try {
                        selection = Integer.parseInt(scan.nextLine());
                        if (selection > 10 || selection < 1) {
                            System.out.println("Invalid choice");
                        } else {
                            player2.setName(allNames[selection - 1]);
                            aiType = selection - 1;
                            System.out.println("You chose " + player2.getName());
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
        return player2;
    }
    private void rules() { //Display the rules of the game
        System.out.println("This is a game of NIM");
        System.out.println("The rules are as follows:");
        System.out.println("You and your chosen AI will take turns removing marbles from a heap of size " + heapOfMarbles);
        System.out.println("You may take as few as 1 and as many as half of the remaining marbles");
        System.out.println("Always enter a whole integer (no decimals or characters. It just won't work)");
        System.out.println("The player who takes the last marble wins");
        doesHumanStart = flipCoin();
        if(doesHumanStart){
            System.out.println("\n\nLets begin! Human, (or "+ player1.getName() +  ") you go first... Please enter a number to remove some marbles");
        }
        else{
            System.out.println("\n\nLets begin! Computer, (or " + player2.getName() + "), you go first... Please enter a number to remove some marbles");
        }
    }
    private boolean flipCoin() { //randomly chose who goes first
        return new Random().nextBoolean();
    }
    private void plaGame(){
        while(!gameOver) {
            takeTurn();
        }
    }
    private void takeTurn() { //alternate based on the turn counter
        turnCounter++;
        System.out.println("\nRound " + turnCounter + " begins:\n" + heapOfMarbles + " remain\n");
        if(doesHumanStart){
            if(!gameOver) {
                takeHumanTurn();
            }
            if(!gameOver) {
                takeComputerTurn();
            }
        }
        else {
            if(!gameOver) {
                takeComputerTurn();
            }
            if(!gameOver) {
                takeHumanTurn();
            }
        }
        if(heapOfMarbles == 0){
            System.out.println("Game over! There are " + heapOfMarbles + " marbles left!");
            gameOver = true;
        }
        else if(heapOfMarbles < 0){
            System.out.println("Clearly something went wrong... there are " + heapOfMarbles + " marbles left?");
            gameOver = true;
        }
        else{
            System.out.println("\nRound " + turnCounter + " over:\n" + heapOfMarbles + " remain\n");
        }
    }
    private void takeHumanTurn(){
        if(!gameOver) {
            heapOfMarbles = heapOfMarbles - player1.takeMarbles(heapOfMarbles);
            System.out.println("There are " + heapOfMarbles + " after the Human's turn");
        }
        if (heapOfMarbles <= 0){
            System.out.println("Human ( "+ player1.getName() + " ) wins!");
            gameOver = true;
        }
    }
    private void takeComputerTurn(){
        if(!gameOver) {
            heapOfMarbles = player2.takeMarbles(heapOfMarbles, aiType);
            System.out.println("There are " + heapOfMarbles + " after the AI's turn");
        }
        if (heapOfMarbles <= 0){
            System.out.println("Computer ( "+ player2.getName() + " ) wins!");
            gameOver = true;
        }
    }


}
