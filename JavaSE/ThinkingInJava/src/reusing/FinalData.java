package reusing;

import java.util.Random;

class Value{
    int i;
    public Value(int i){
        this.i = i;
    }
}

class Bar{
    public final int i = 1;
    public final Value value = new Value(1);
    public final int randInt = new Random().nextInt(100);
}

public class FinalData {
    public static void main(String[] args) {
        Bar bar = new Bar();
        //bar.i++; //ERROR
        //bar.value = new Value(233); //ERROR
        bar.value.i++; //it's ok
        System.out.println(bar.randInt);
    }
}
