package com.snow.stack;



import org.junit.jupiter.api.Test;
import sun.plugin.javascript.navig.Link;

import java.util.Iterator;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-03-22
 **/
public class MyTest {

    public void test(){

    }

    public static void main(String[] args) {
        LinkStack<Integer> sta = new LinkStack<>();
        sta.push(1);
        sta.push(2);
        sta.push(4);
        sta.push(5);
        sta.push(6);
        sta.push(7);
        sta.push(8);
        sta.push(9);
        while(!sta.isEmpty()){
            System.out.println(sta.pop());
        }


    }

}
