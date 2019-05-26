package polymorphism;

import java.lang.management.MemoryUsage;
import java.util.Map;

class Grain{
    public String toString(){
        return "Grain";
    }
}

class Wheat extends Grain{
    public String toString(){
        return "Wheat";
    }
}

class Mill{
    Grain process(){
        return new Grain();
    }
}

class WheatMill extends Mill{
    Wheat process(){
        return new Wheat();
    }
}

public class CovariantReturn {
    public static void main(String[] args) {
        Mill mill = new Mill();
        Grain grain = mill.process();
        System.out.println(grain);

        mill = new WheatMill();
        grain = mill.process();
        System.out.println(grain);
    }
}
