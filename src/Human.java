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
        if(scan.hasNextInt()) {
            int take = scan.nextInt();
            if (take <= available && take > 0) {
                available = available - take;
                return available;
            } else {
                System.out.println("Invalid Number. Please enter an integer greater than 0 and less than " + available + ".");
                return 0;
            }
        }
        else {
            System.out.println("Invalid Number. Please enter an integer greater than 0 and less than " + available + ".");
            return 0;
        }
    }

}
