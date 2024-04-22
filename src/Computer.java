import java.util.Random;

public class Computer extends Player{
    public Computer(String defaultName){
        this.setName(defaultName);
    }
    public int takeMarbles(int available, int aiType){
        int remainingPool = 0;
        switch (aiType){
            case 0:
                remainingPool = borisStrat(available);
                break;
            case 1:
                remainingPool = drGigglesStrat(available);
                break;
            case 2:
                aiType = new Random().nextInt(0,1);
                remainingPool = takeMarbles(available, aiType);
                break;
            case 3:
                remainingPool = marvinStrat(available);
                break;
            case 4:
                remainingPool = sidStrat(available);
                break;
            case 5:
                remainingPool = eniacStrat(available);
                break;
            case 6:
                if(available < 10){
                    aiType = 5;
                    remainingPool = takeMarbles(available, aiType);
                }
                else {
                    aiType = new Random().nextInt(0,5);
                    remainingPool = takeMarbles(available, aiType);
                }
                break;
            case 7:
                remainingPool = halStrat(available);
                break;
            case 8:
                if(available > 25){
                    remainingPool = halStrat(available);
                }
                else if(available < 25 && available > 10){
                    aiType = 6;
                    remainingPool = takeMarbles(available,aiType);
                }
                else{
                    remainingPool = eniacStrat(available);
                }
                break;
            case 9:
                if(available > 9){
                    aiType = new Random().nextInt(5,8);
                    remainingPool = takeMarbles(available, aiType);
                }
                else {
                    remainingPool = eniacStrat(available);
                }
                break;
        }
        return remainingPool;
    }

    private int borisStrat(int available){
        available--;
        return available;
    }
    private int drGigglesStrat(int available){
        available = takeHalfUnlessIsOne(available);
        return available;
    }
    private int marvinStrat(int available){
        if(available > 15){
            available--;
        }
        else if (available > 2){
            available = takeHalfUnlessIsOne(available);
        }
        else {
            available--;
        }
        return available;
    }

    private int sidStrat(int available){
        if(available > 4) {
            available--;
        }
        else{
            available = takeHalfUnlessIsOne(available);
        }
        return available;
    }
    private int eniacStrat(int available){
        if(available > 20){
            available = takeHalfUnlessIsOne(available) + 9;
        }
        else if(available >= 10){
            available = available -3;
        }
        else if (available == 2 || available == 3 || available == 5 || available == 6 || available == 7 || available == 8 || available == 9){
            available--;
        }
        else if(available == 4){
            available = takeHalfUnlessIsOne(available);
        }
        else {
            available--;
            System.out.println("I won with the Eniac strategy");
        }
        return available;
    }
    private int halStrat(int available){
        if(available > 10){
            available = new Random().nextInt(takeHalfUnlessIsOne(available), available - 1);
        }
        else {
            eniacStrat(available);
        }
        return available;
    }
    private int skyNetStrat(int available){
        return available--;
    }

    private int takeHalfUnlessIsOne(int available){
        if(available%2 == 1 && available != 1 && available != 3){
            available++;
            available = available/2;
        }
        else if (available > 1 && available != 3){
            available = available/2;
        }
        else{
            available--;
        }
        return available;
    }
}
