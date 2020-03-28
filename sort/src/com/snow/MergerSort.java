package com.snow;

import com.snow.SortUtil.SortUtil;

/**
 * @Description:归并排序
 * @author: snow
 * @Date: 2020-03-07
 **/
public class MergerSort {
    private static Comparable [] aux;

    /**
     * 将一个数组的左右两边合并（分别有序）
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge(Comparable [] a,int lo,int mid, int hi){
        for(int k = lo;k <= hi;k++){
            aux[k] = a[k];
        }
        int i = lo;
        int j = mid + 1;
        for(int k = lo; k <= hi; k++){
            if(i > mid)
                a[k] = aux[j++];
            else if(j > hi)
                a[k] = aux[i++];
            else if(SortUtil.less(aux[i],aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }
    }

    /**
     * 自顶向下所谓归并排序
     * @param a
     */

    public static void sortFormTop(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length - 1);
    }
    public static void sort(Comparable[] a,int lo,int hi){
        System.out.println("sort entered lo = " + lo + " hi = " + hi );
        if(lo == hi)
            return;
        int mid = lo + (hi - lo)/2;
        sort(a,lo,mid);
        sort(a,mid + 1,hi);
        merge(a,lo,mid,hi);

    }

    /**
     * 自底向上的归并排序，自底向上更适合链表。
     * @param a
     */
    public  static void  sortFromBottom(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for(int sz = 1; sz < N; sz = sz + sz){//sz 要归并的子数组的大小，最右端可能小于sz
            for(int lo = 0; lo < N - sz -1; lo = lo + sz + sz){
                merge(a,lo,lo + sz - 1,Math.min(lo + sz + sz ,N - sz));
            }
        }
    }


}
