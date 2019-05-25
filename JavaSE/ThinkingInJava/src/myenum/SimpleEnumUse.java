package myenum;

public class SimpleEnumUse {
    public static void main(String[] args) {
        Size mySize = Size.MEDIUM;
        System.out.println(mySize);//

        /**
         * Size的静态values()方法，获得由enum常量组成的数组
         * 可以直接打印出enum的实例名字，也可以通过ordinal()方法获得enum常量对应的数值
         */
        for(Size size : Size.values()){
            System.out.println(size+": "+size.ordinal());
        }
    }
}
