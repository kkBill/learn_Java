package reusing;

final class Foo {
    public int i;

    Foo(){}
    Foo(int i) {
        this.i = i;
    }

    public void func() {
        System.out.println("Foo: i: " + i);
    }
}

//ERROR
//class Bar extends Foo{
//
//}

public class FinalClass {
    public static void main(String[] args) {
        Foo foo = new Foo(233);
        foo.func();
        foo.i++;
        foo.func();
    }
}
