package com.snow.Bag;

import java.util.Iterator;

/**
 * @Description:背包是一种只进不出的数据结构，这里用链表实现
 * @author: snow
 * @Date: 2020-01-08
 **/
public class Bag<Item> implements Iterable<Item> {
    private int N;
    private Node head;

    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void add(Item item){
        Node oldHead = this.head;
        head = new Node();
        head.item = item;
        head.next = oldHead;
        N ++;

    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
               Item item = current.item;
               current = current.next;
               return item;
            }
        };
    }

    private class Node{
        Item item;
        Node next;
    }

}
