package com.snow.SortUtil;

/**
 * @Description: 排序工具类的一些操作
 * @author: snow
 * @Date: 2020-01-10
 **/
public class SortUtil {
    /*
  返回a是否小于b
   */
    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }
    /*
    交换数组中两个元素的位置
     */
    public static void exchange(Comparable [] a,int i,int j ){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] =temp;
    }
    /*
    打印数组中的元素
     */
    public static void show(Comparable[] a){
        for (Comparable comparable : a) {
            System.out.println(comparable);
        }
    }
    /*
    测试数组中的元素是否有序
     */
    public static boolean isSorted(Comparable [] a){
        for (int i = 1; i <a.length ; i++) {
            if(less(a[i -1],a[i]))
                return false;

        }return true;
    }
}
