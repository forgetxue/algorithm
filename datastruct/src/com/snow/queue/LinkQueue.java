package com.snow.queue;

import java.util.Iterator;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-01-07
 **/
public class LinkQueue<Item> implements Iterable<Item>{
    private int N;
    private Node head;
    private Node tail;

    private class Node{
        Item item;
        Node next;
    }

    public void enqueue(Item item){
        Node oldTail= this.tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        if(N == 0) head = tail;
        oldTail.next = tail;
        N++;
    }
    public boolean isEmpty(){
        return N == 0;
    }

    public Item dequeue(){
        if(!isEmpty()){
            Node oldHead = this.head;
            this.head = this.head.next;
            N --;
            return oldHead.item;
        }else{
            return null;
        }


    }
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node p = head;
            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public Item next() {
                Item item = p.item;
                p = p.next;
                return item;
            }
        };
    }
}
