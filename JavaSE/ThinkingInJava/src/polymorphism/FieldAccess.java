package polymorphism;

class Base{
    public int field = 0;
    public int getField(){
        return field;
    }
}

class Sub extends Base{
    public int field = 233;
    public int getField() {
        return field;
    }
    public int getBaseField(){
        return super.field;
    }
}

public class FieldAccess {
    public static void main(String[] args) {
        Base base = new Sub();//upcasting
        System.out.println("base.field:"+base.field+" base.getField():"+base.getField());

        Sub sub = new Sub();
        System.out.println("sub.field:"+sub.field+" sub.getField():"+sub.getField()
                            +" sub.getBaseField():"+sub.getBaseField());
    }
}
