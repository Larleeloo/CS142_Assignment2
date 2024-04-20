import java.util.Scanner;

public class Human extends Player{
    private Scanner scan = new Scanner(System.in);
    public Human(String defaultName){
        this.setName(defaultName);
    }
    public Human(String defaultName, Scanner scanIn){
        scan = scanIn;
        if(scan.hasNext()){
            setName(scan.next());
        }
        else{
            setName(defaultName);
        }
    }
    @Override
    public int takeMarbles(int available){
        System.out.println("Please enter an integer for how many marbles you wish to take...");
        int selection = 0;
        while(selection == 0){
            switch (selection) {
                case 0:
                    try {
                        selection = Integer.parseInt(scan.nextLine());
                        if(available == 1 && selection != available) {
                            System.out.println("There is only one marble left. Enter 1 to win the game.");
                        }
                        else if(selection == 1){
                            break;
                        }
                        else if (selection > Math.round(available/2) || selection < 0) {
                            System.out.println("Invalid choice");
                        }
                        else {
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
        return selection;
    }


}
