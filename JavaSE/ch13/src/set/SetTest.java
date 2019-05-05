package set;

import java.util.*;

/**
 * HashSet类是基于散列表实现的集合，对元素的存储时无序的
 * 有点像C++ STL里的unordered_set
 */

public class SetTest {
    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.add("faa");
        dict.add("bgb");
        dict.add("abc");
        dict.add("cba");
        dict.add("nba");

        if(dict.contains("abc")){
            System.out.println("abc\n");
        }else{
            System.out.println("sorry\n");
        }

        for(String word : dict)
            System.out.println(word);//输出是无序的
    }

}
