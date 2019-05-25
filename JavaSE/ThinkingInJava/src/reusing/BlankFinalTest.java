package reusing;

class BlankFinal{
    public final int i;

    BlankFinal(){
        i= 1;
    }

    BlankFinal(int i){
        this.i = i;
    }

}

public class BlankFinalTest {
    public static void main(String[] args) {
        //不同的BlankFinal对象，i的值不一样
        BlankFinal blankFinal1 = new BlankFinal();
        System.out.println(blankFinal1.i);
        BlankFinal blankFinal2 = new BlankFinal(233);
        System.out.println(blankFinal2.i);
    }
}
