package com.snow.test;

import com.snow.HeapSort;
import com.snow.MergerSort;
import com.snow.QuickSort;
import com.snow.maxpq.IndexMinPQ;
import com.snow.maxpq.Maxpq;
import com.snow.maxpq.MyTransaction;
import com.snow.maxpq.SupportComparatorMaxPQ;
import org.junit.Test;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-03-09
 **/
public class MyTest {
    @Test
    public void test1(){
       Integer[] a = {0,5,3,4,7,2,8,5};
        HeapSort.sort(a);
        for (Integer aa : a) {
            System.out.println(aa);
        }
    }

    @Test
    public void testIndexPQ(){
        IndexMinPQ<Integer> IPQ = new IndexMinPQ<>(10);
        IPQ.insert(1,23);
        IPQ.insert(2,3);
        IPQ.insert(3,11);
        IPQ.insert(4,233);
        IPQ.insert(5,25);
        IPQ.insert(6,56);
        Integer min = IPQ.min();
        System.out.println(min);
        System.out.println(IPQ.delMin());
        System.out.println(IPQ.delMin());
    }
   @Test
    public void testPq(){
       SupportComparatorMaxPQ<MyTransaction> pq = new SupportComparatorMaxPQ<>(10);
       pq.setComparator(new MyTransaction.NameComparator());
       pq.insert(new MyTransaction("snow",100));
       pq.insert(new MyTransaction("forget",100));
       pq.insert(new MyTransaction("password",100));
       pq.insert(new MyTransaction("abc",100));
       pq.insert(new MyTransaction("666",100));
       System.out.println(pq.delMax().getName());

   }
}

