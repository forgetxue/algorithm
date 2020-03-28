package com.snow.stack;

import java.util.Iterator;

public class MyResizingArrayStack<Item> implements Iterable<Item> {
    private int N;  //数组中的元素个数
    private Item [] a = (Item[]) new Object[1];
    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public  void push(Item item){
        if(N == a.length) resize(a.length *2);
        a[N++] = item;

    }
    public Item pop(){
        Item top = a[--N];
        a[N] = null;
        if(N > 0 && N == a.length/4) resize(a.length/2);
        return top;
    }
    private void  resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N ; i++) {
            temp[i] = a[i];//防止对象游离
        }
        a = temp;
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;  //遍历不能用N来遍历否则相当于出栈

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
        @Override
        public void remove(){}
    }


    @Override
    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }
}
