package com.snow.SortUtil;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-01-10
 **/
public class TestElement implements Comparable{
    private int key;
    private String name;
    /*
    如果小于o 返回 1；
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof TestElement){
            TestElement ob = (TestElement) o;
            if(this.key < ob.key) return 1;
            else if(this.key == ob. key) return 0;
            return -1;
        }else
             throw new RuntimeException("Arg o is not instance of TestElement");
    }
}
