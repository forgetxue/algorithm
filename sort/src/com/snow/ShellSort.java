package com.snow;

import com.snow.SortUtil.SortUtil;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-01-12
 **/
public class ShellSort {
    /*
    希尔排序法，自然排序
     */

    public void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while(h < N/3)
            h = 3*h + 1;
        while(h>=1){
            for (int i = h; i < N  ; i++) {
                //每相隔h的元素插入排序
               for (int j = i; j >= h && SortUtil.less(a[j],a[j-h]);j-=h){
                   SortUtil.exchange(a, j, j-h);
               }
            }
            h = h/3;
        }
    }
}
