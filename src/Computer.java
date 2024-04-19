import java.util.Random;

public class Computer extends Player{
    public Computer(String defaultName){
        this.setName(defaultName);
    }

    @Override
    public int takeMarbles(int available){
        int cTake = 0;
        if(available == 4 || available == 3){
            cTake = 1;
            System.out.println("Just take 1. I dare you.");
            return cTake;
        }
        else if(available == 2 || available == 1){
            cTake = available;
            System.out.println("I win!");
            return cTake;
        }
        else{
            cTake = new Random().nextInt(available);
            return cTake;
        }
    }
}
