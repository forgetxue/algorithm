package com.snow;

import com.snow.SortUtil.SortUtil;

/**
 * @Description:堆排序
 * @author: snow
 * @Date: 2020-03-13
 **/
public class HeapSort {

    /**
     *
     * @param a a[0]为空位置
     */
    public static void sort(Comparable[] a){
        int N = a.length - 1;
        //构造有序堆
        for(int k=N / 2; k >= 1;k--)
            sink(a,k,N);
        //堆排序
        while(N > 1){
            SortUtil.exchange(a,1,N--);
            sink(a,1,N);
        }
    }


    public static void sink(Comparable[]a,int k,int N){
        while(2*k <= N){
            int j = 2*k;
            if( j + 1 <= N && SortUtil.less(a[j],a[j + 1])) j++;
            if(!SortUtil.less(a[k],a[j]))break;
            SortUtil.exchange(a,k,j);
            k = j;
        }

    }
}
