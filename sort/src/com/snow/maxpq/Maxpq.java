package com.snow.maxpq;

/**
 * @Description:优先队列
 * @author: snow
 * @Date: 2020-03-12
 **/
public class Maxpq<Key extends Comparable<Key>> {
    private Key[] pq; //基于堆的完全二叉树
    int N = 0; //计数器，存储于pq[1,N]

    public Key[] getPq() {
        return pq;
    }

    public Maxpq(int max ){
        pq = (Key[]) new Comparable[max + 1];
    }
    public Maxpq(Comparable [] a){
        N = a.length;
        pq = (Key[]) new Comparable[ N + 1];
        for(int i = 0;i< a.length;i++){
            pq[i+1] = (Key)a[i];
        }

    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }

    /**
     * 增加元素
     * @param key
     */
    public void insert(Key key){
        pq[++N] = key;
        sink(N);
    }

    /**
     * 删除最大元素，将最大元素与最后一个元素交换位置，再让新的顶端元素下沉
     * @return
     */
    public Key delMax(){
        Key max = pq[1];
        exch(1,N);
        pq[N--] = null;
        sink(1);
        return max;

    }

    /**
     * 返回最大元素
     * @return
     */
    public Key max(){
        return pq[1];
    }

    private void exch(int i,int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    //pq[i] 是否小于 pq[j]
    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;

    }

    /**
     * 元素上浮
     * @param i
     */
    private void swim(int i) {
        while (i > 1 && less(i / 2, i)) {
            exch(i, i / 2);
            i = 1 / 2;
        }
    }

    /**
     * 元素下沉
     * @param i
     */
    private void sink(int i){
        while(2*i <= N){
            int j = 2*i;
            if(j < N && less(j,j+1)) j++;
            if(!less(i,j))break;
            System.out.println("exch i,j");
            exch(i,j);
            i = j;

        }
    }

}
