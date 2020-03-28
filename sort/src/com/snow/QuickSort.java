package com.snow;

import com.snow.SortUtil.SortUtil;
import javafx.collections.transformation.SortedList;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-03-10
 **/
public class QuickSort {
    public static void quickSort(Comparable[] a){
        quickSort(a,0,a.length - 1);
    }

    public static void quickSort(Comparable[] a,int lo,int hi){
        if(hi <= lo){
            System.out.println(" hi = " + hi + " lo = " + lo +" hi <= lo return");
            return;
        }
        if(hi < lo + 5){
            //在数组长度较小时，出于递归的原因，插入排序要比快速排序高效，一般为5-15
            InsertionSort.sort(a,lo,hi);
            return;
        }
        int p = partition(a,lo,hi); //切分点
        quickSort(a,lo,p - 1);//递归的排序左边
        quickSort(a,p + 1, hi);//递归的排序右半边

    }

    /**
     * 找出分切数组的点
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int partition(Comparable[] a, int lo, int hi){
        int i = lo,j = hi + 1;
        Comparable v = a[lo];
        while(true){
            while(SortUtil.less(a[++i] , v));
            while(SortUtil.less(v,a[--j]));

            if(i >= j) break;
            if(a[i].compareTo(a[j]) != 0)
                SortUtil.exchange(a,i,j);

        }
        SortUtil.exchange(a,lo,j);
        System.out.println("j = " + j);
        return j;
    }

    /**
     * 三向切分的快速排序，对于有大量重复元素的数组，效率比递归要效率高
     * @param a
     * @param lo
     * @param hi
     */
    public static void quick3way(Comparable[] a, int lo, int hi){
        int lt = lo,i = lo + 1,gt = hi;
        Comparable v = a[lo];
        while(i <= gt){
            int cmp = v.compareTo(a[i]);
            if(cmp < 0){
                SortUtil.exchange(a,i,gt--);
            }else if(cmp > 0){
                SortUtil.exchange(a,lt++,i++);
            }
            else
                i++;
        }//现在已经满足：a[lo,gt] < v = a[i,gt]<a[gt + 1,hi]
        //左右两边分别递归排序
        quickSort(a,lo,lt);
        quickSort(a,gt + 1,hi);
    }

}
