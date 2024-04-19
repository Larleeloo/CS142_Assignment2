import java.util.Random;

public abstract class Player implements IPlayer{
    private String name;
    public Player(){
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int takeMarbles(int available){
        return new Random().nextInt(available);
    }
}
