package polymorphism;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

//Glyph:字体
class Glyph {
    void draw() {
        System.out.println("Glyph.draw()");
    }

    Glyph() {
        System.out.println("before draw()");
        draw();
        System.out.println("after draw()");
    }
}

class Other{
    Other(){
        System.out.println("Other...");
    }
}

class RoundGlyph extends Glyph{
    private int radius = 2;
    private Other other = new Other();

    void draw() {
        System.out.println("RoundGlyph.draw(), radius=" + radius);
    }

    RoundGlyph(int radius) {
        this.radius = radius;
        System.out.println("RoundGlyph constructor, radius=" + radius);
    }
}

public class PolyConstructor {
    public static void main(String[] args) {
        Glyph glyph = new RoundGlyph(5);
    }
}
