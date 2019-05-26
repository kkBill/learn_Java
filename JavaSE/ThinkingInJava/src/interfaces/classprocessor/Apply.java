package interfaces.classprocessor;

import java.util.Arrays;

class Processor{
    public String name(){
        return this.getClass().getSimpleName();
    }

    Object process(Object input){
        return input;
    }
}

class Upcase extends Processor{
    String process(Object input){
        return ((String) input).toUpperCase();
    }
}

class Downcase extends Processor{
    String process(Object input){
        return ((String) input).toLowerCase();
    }
}

class Splitter extends Processor{
    String process(Object input){
        return Arrays.toString(((String)input).split(" "));//遇见空格就分割，返回一个String的数组
    }
}

public class Apply {
    public static void process(Processor processor, Object object){
        System.out.println("Using Processor "+processor.name());
        System.out.println(processor.process(object));
    }

    public static String s = "Hello World Hello ZJU";

    public static void main(String[] args) {
        process(new Upcase(), s);
        process(new Downcase(), s);
        process(new Splitter(), s);
    }
}
