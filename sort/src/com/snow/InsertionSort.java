package com.snow;

import com.snow.SortUtil.SortUtil;

/**
 * @Description:
 * 插入排，从第二个元素起，每一个元素与他前面的元素逐个比较，如果小于他前面的元素则交换位置，并继续与前面的元素比较
 * 否则开始下一个元素的比较与之之前的元素比较，直到比较完最后一个元素
 * 至于是升序还是降序，取决于Less方法
 * @author: snow
 * @Date: 2020-01-10
 **/

public class InsertionSort {

    public static void sort(Comparable [] a){
        int N = a.length;
        for (int i = 1; i < N ; i++) {
            for(int j = i; j > 0 && SortUtil.less(a[j] ,a[j -1]); j--)
                SortUtil.exchange(a, j-1, j);
        }
    }
    public static void sort(Comparable[] a, int lo,int hi){
        for(int i = lo + 1;i <= hi; i++){
            for(int j = i; j > 0 &&SortUtil.less(a[j],a[j -1 ]);j--){
                SortUtil.exchange(a,j,j - 1);
            }
        }
    }
}
