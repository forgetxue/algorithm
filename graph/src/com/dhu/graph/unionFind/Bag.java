package com.dhu.graph.unionFind;

import java.util.Iterator;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-03-26
 **/
public class Bag<Item> implements Iterable {
    private Node first;
    private int N;

    public Bag(){
    }
    public void put(Item value){
        Node node = new Node(value);
        node.next = first;
        first = node;
    }


    @Override
    public Iterator iterator() {
        return new Iterator() {
            Node p =  first;
            @Override
            public boolean hasNext() {
                return first == null;
            }

            @Override
            public Item next() {
                Node re = p;
                p = p.next;
                return re.value;
            }
        };
    }


    private class Node{
        Item value;
        Node next;

        Node(Item value){
            this.value = value;
        }
    }

}
