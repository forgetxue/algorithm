package com.snow;

import com.snow.SortUtil.SortUtil;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-01-10
 **/
public class SelectSort  {
    public static void sort(Comparable [] a){
        for (int i = 0; i <a.length ; i++) {
            int min = i;
            for (int j = i + 1; j < a.length ; j++) {
                if(SortUtil.less(j,i)) min = j;
            }
            SortUtil.exchange(a, i, min);
        }
    }
}
