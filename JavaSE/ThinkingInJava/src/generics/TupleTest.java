package generics;

/**
 * 利用泛型构造元组，可同时返回多种类型
 */
class TwoTuple<A, B> {
    public final A a;
    public final B b;

    public TwoTuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "[" + a + ", " + b + "]";
    }
}

/**
 * 通过继承，可以无限扩展元组中存放类型的个数
 */
class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    public final C c;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public String toString() {
        return "[" + a + ", " + b + ", " + c + "]";
    }
}

class Foo{
    @Override
    public String toString() {
        return "Class Foo";
    }
}


public class TupleTest {
    //同时返回String类型和int类型
    private static TwoTuple<String, Integer> f() {
        return new TwoTuple<>("hi", 24);
    }

    //同时返回String类型，类类型和double类型
    private static ThreeTuple<String, Foo, Double> g(){
        return new ThreeTuple<>("hello", new Foo(), 3.14);
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> tt = f();
        System.out.println(tt);
        //可以单独取出由元组返回的对象，但是不能更改，因为在元组中设置为了final域
        //tt.a = "new string"; //ERROR
        String str = tt.a;
        int b = tt.b;
        System.out.println(str + ", " + b);

        ThreeTuple<String, Foo, Double> tt3 = g();
        System.out.println(tt3);
    }
}
