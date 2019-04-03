package bitSet;

import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        BitSet bits1 = new BitSet(8);
        BitSet bits2 = new BitSet(8);

        // set some bits
        for(int i = 0; i < 8; i++) {
            if((i % 2) == 0) bits1.set(i);//第i位上设置为true
            if((i % 3) != 0) bits2.set(i);
        }

        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1);//即 10101010
        System.out.println("\nInitial pattern in bits2: ");
        System.out.println(bits2);//即 01101101

        /** 两个二进制序列进行AND操作
         *  10101010
         *  01101101
         *= 00101000  -> 即{2, 4}
        */
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: ");
        System.out.println(bits2);//即{2, 4}
        System.out.println(bits2.get(2));//true

        // OR bits
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: ");
        System.out.println(bits2);

        // XOR bits
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: ");
        System.out.println(bits2);
    }
}
