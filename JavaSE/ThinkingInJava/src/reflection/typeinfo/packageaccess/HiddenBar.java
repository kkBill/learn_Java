package reflection.typeinfo.packageaccess;

import reflection.typeinfo.MyInterface;

//Bar只有包访问权限，包外无法访问
class Bar implements MyInterface {

    private final int  i = 233;
    private String hiddenField = "you can see me";


    public void f() {
        System.out.println("public Bar f()...");
    }

    public void g() {
        System.out.println("public Bar g()...");
    }

    //包访问权限
    void u() {
        System.out.println("package Bar u()...");
    }

    protected void v() {
        System.out.println("protected Bar v()...");
    }

    private void w() {
        System.out.println("private Bar w()...");
    }
}

public class HiddenBar {
    public static MyInterface makeBar() {
        return new Bar();
    }
}
