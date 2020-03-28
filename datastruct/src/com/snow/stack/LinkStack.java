package com.snow.stack;

import java.util.Iterator;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-01-07
 **/
public class LinkStack<Item>  implements Iterable<Item>{
    private int N;
    private Node top;
    private class Node{
        Item item;
        Node next;
    }


    public void push(Item item){

        Node node = new Node();
        Node oldTop = top;
        node.item = item;
        top = node;
        top.next = oldTop;
        N++;
    }
    public boolean isEmpty(){
        return top == null;
    }
    public int size(){
        return N;
    }
    public Item pop(){
        if(!this.isEmpty()){
        Node top = this.top;
        this.top = this.top.next;
        N--;
        return top.item;
        }else return null;
    }

    public LinkStack() {
    }
    /*
    bug

     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node p = top;
            @Override
            /*
            hasNext会检测 p是否为Null;
             */
            public boolean hasNext() {
                return this.p != null;
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
