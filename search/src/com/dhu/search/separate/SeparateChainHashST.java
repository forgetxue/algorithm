package com.dhu.search.separate;

import com.snow.stack.LinkStack;

import java.util.Iterator;

/**
 * @Description:拉链法散列表
 * @author: snow
 * @Date: 2020-03-22
 **/
public class SeparateChainHashST<Key, Value> {
    private int N;  //键值对个数
    private int M;  //散列表大小
    private SequentialSearchST<Key,Value>[] st;
    public SeparateChainHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value> []) new SequentialSearchST[M];
        for(int i = 0;i < M; i ++){
            st[i] = new SequentialSearchST<Key,Value>();
        }
    }

    public SeparateChainHashST() {
        this(997);  //默认使用997条链表，相对于较大的字符表，这种实现比单纯的使用链表块1000倍
    }

    private int hash(Key key){
        return (key.hashCode() & 0xfffffff) % M;
    }
    public void put(Key key,Value value){
        int hash = hash(key);
        System.out.println(key + ".hahs = " + hash);
        st[hash].put(key,value);
        N ++;
        if(N > 8 * M) resize(2 * M); //保持链表长度在 2-8之间。
    }
    public Value get(Key key){
        return st[hash(key)].get(key);

    }
    public Iterable<Key> keys(){
        LinkStack<Key> keys = new LinkStack<>();

        for(int i = 0;i < M;i++){
            SequentialSearchST<Key, Value> st = this.st[i];
            for(SequentialSearchST.Node x =st.first;x != null;x = x.next){
                keys.push((Key) x.key);
            }
        }
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new Iterator<Key>() {

                    @Override
                    public boolean hasNext() {
                        return (!keys.isEmpty());

                    }
                    @Override
                    public Key next() {
                        return keys.pop();
                    }
                };
            }
        };
    }
    public void delete(Key key){
        st[hash(key)].delete(key);
        N --;
        if(N > 0 && N <= 2 * M)resize(M / 2);
    }


    /**
     * 线性查找表
     * @param <Key>
     * @param <Value>
     */
    private class SequentialSearchST<Key,Value>{
        private Node first;
        class Node{
            Key key;
            Value value;
            Node next;

            Node (Key key,Value value,Node next){
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
        void put(Key key,Value value){
            for(Node x = first;x != null;x = x.next){
                if(x.value.equals(key)){
                    x.value = value;
                    return;
                }
            }
            first = new Node(key,value,first);
        }
        void delete(Key key){
            for(Node x = first;x!= null && x.next != null;x = x.next){
                if(x.next.key.equals(key)){
                   x.next = x.next.next;
                }
            }
        }
        Value get(Key key){
            for(Node x = first;x != null;x = x.next){
                if(x.key.equals(key))
                    return x.value;
            }
            return null;
        }
        Iterator<Key> getIterator(){
            return new Iterator<Key>() {
                @Override
                public boolean hasNext() {
                    return first != null;
                }

                @Override
                public Key next() {
                    Node p = first;
                    first = p.next;
                    return p.key;
                }
            };
        }
    }
    private void resize(int cap){
        SeparateChainHashST<Key, Value> t = new SeparateChainHashST<>(cap);
        for(int i = 0; i < M; i++){
            for(SequentialSearchST.Node x =st[i].first;st[i] != null;x = x.next){
                t.put((Key)x.key,(Value)x.value);
            }
        }
        M = t.M;
        st = t.st;
    }
}
