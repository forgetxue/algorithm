package com.snow.maxpq;

import java.util.Comparator;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-03-13
 **/
public class MyTransaction {
    private String name;
    private Integer value;
    public MyTransaction(String name,Integer value){
        this.name = name;
        this.value = value;
    }


    public static class NameComparator implements Comparator<MyTransaction>{

        @Override
        public int compare(MyTransaction o1, MyTransaction o2) {
            return o1.name.compareTo(o2.name);
        }
    }
    public static class ValueComparator implements Comparator<MyTransaction>{

        @Override
        public int compare(MyTransaction o1, MyTransaction o2) {
            return o1.value - o2.value;

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
