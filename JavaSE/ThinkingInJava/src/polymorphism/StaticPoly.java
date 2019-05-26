package polymorphism;

class StaticSuper{
    public static String staticGet(){
        return "Base staticGet";
    }

    public String dynamicGet(){
        return "Base dynamicGet";
    }
}

class StaticSub extends StaticSuper{
    public static String staticGet(){
        return "Derived staticGet";
    }

    public String dynamicGet(){
        return "Derived dynamicGet";
    }
}

public class StaticPoly {
    public static void main(String[] args) {
        StaticSuper staticSuper = new StaticSub();//
        System.out.println(staticSuper.dynamicGet());
        System.out.println(staticSuper.staticGet());
    }
}
