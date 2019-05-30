package reflection.typeinfo;

import reflection.typeinfo.packageaccess.HiddenBar;

import java.lang.reflect.Method;

public class HiddenImplementation {
    public static void main(String[] args) throws Exception {
        MyInterface a = HiddenBar.makeBar();
        a.f();
        //a.g(); //ERROR
        //a.u(); //ERROR
        //a.v(); //ERROR
        //a.w(); //ERROR
        System.out.println(a.getClass().getName());

        callHiddenMethod(a,"g");
        callHiddenMethod(a,"u");
        callHiddenMethod(a,"v");
        callHiddenMethod(a,"w");
    }

    /**
     * 通过反射，可以绕过访问权限的设置，可以调用[仅允许包访问权限的方法]、甚至是[private方法]，还可以是[private域]
     * 只要知道方法名，然后调用method的setAccessible()方法，就可正常访问了
     */
    static void callHiddenMethod(Object a, String methodName) throws Exception {
        Method method = a.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(a);
    }
}
