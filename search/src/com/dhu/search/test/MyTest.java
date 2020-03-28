package com.dhu.search.test;

import com.dhu.search.BST.BinarySearchTree;
import com.dhu.search.BST.RedBlackBST;
import com.dhu.search.binarySearch.BinarySearchST;
import com.dhu.search.separate.SeparateChainHashST;
import com.dhu.search.sequetialsearch.SequeSearchST;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-03-14
 **/
public class MyTest {

    @Test
    public void test1(){
        SequeSearchST<Integer, String> st = new SequeSearchST<>();
        st.put(1,"first");
        st.put(2,"second");
        st.remove(1);
        st.remove(2);

    }
    @Test
    public void testCopy(){
        int [] a = {1,2,3};
        a = Arrays.copyOf(a, 6);
        for (int anInt : a) {

            System.out.println(anInt);
        }
    }
    @Test
    public void testBinarySearch(){
        BinarySearchST<String, Integer> birsearch = new BinarySearchST<>(6);
        birsearch.put("first",11);
        birsearch.put("second",23);
        birsearch.put("abc",1);
        birsearch.put("however",91);
        birsearch.put("nopain",11);
        birsearch.put("loveless",61);
        birsearch.put("dd",16);
        birsearch.put("gg",15);
        System.out.println(birsearch.get("gg"));
        birsearch.remove("loveless");

    }

    @Test
    public void testBST(){
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        long recursiveStart = System.currentTimeMillis();
        bst.put("S",1);
        bst.put("E",2);
        bst.put("A",2);
        bst.put("C", 3);
        bst.put("X",4);
        bst.put("R",5);
        bst.put("H",6);
        bst.put("M",7);
//        bst.putNotRecursive("H",100);
//        bst.putNotRecursive("G",100);
//        bst.putNotRecursive("C",100);
//        bst.putNotRecursive("A",100);
//        bst.putNotRecursive("B",100);
//        bst.putNotRecursive("I",100);
//        bst.putNotRecursive("K",100);
//        bst.putNotRecursive("Z",100);
//        bst.putNotRecursive("F",100);
//        List<String> keys = bst.keys("D", "Y");
//        for (String key : keys) {
//            System.out.println(key);
//        }
    }

    @Test
    public void testBRRST(){
        RedBlackBST<String, Integer> rb = new RedBlackBST<>();
        rb.put("S",0);
        rb.put("E",1);
        rb.put("A",2);
        rb.put("R",3);
        rb.put("C",4);
        rb.put("H",5);
        rb.put("X",5);
        rb.put("M",5);
        rb.put("P",5);
        rb.put("L",5);
        rb.delMax();
    }
    @Test
    public void testHash() {
//        SeparateChainHashST<String, Integer> hs = new SeparateChainHashST<>(10);
//        hs.put("first", 1);
//        hs.put("second", 2);
//        hs.put("third", 3);
//        hs.put("four", 3);
//        hs.put("five", 3);
//        hs.put("six", 3);
//        hs.put("seven", 3);
//        hs.put("eight", 3);
//        Iterable<String> keys = hs.keys();
//        keys.forEach(key -> {
//            System.out.println(key);
//        });
        Transaction t1 = new Transaction(2020, 3, 23);
        Transaction t2 = new Transaction(2020,3,23);
        System.out.println(t1.hashCode());
        System.out.println(t2.hashCode());
        System.out.println(t1.equals(t2));


    }
    class Transaction{
        int year;
        int month;
        int day;

        public Transaction(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }

}
