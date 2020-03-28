package com.snow.maxpq;

/**
 * @Description:索引优先队列
 * @author: snow
 * @Date: 2020-03-13
 **/
public class IndexMinPQ<Key extends Comparable<Key>> {
    private int pq[];//存储元素的索引的优先队列
    private int qp[]; //存储pq的逆序，pq[i]=j,qp[j]=i
    private Key[] keys;//存储元素
    private int N;//pq中元素的个数

    public IndexMinPQ(int max){
        pq = new int[max+1];
        qp = new int[max + 1];
        for (int i = 0; i <=max ; i++) {
            qp[i] = -1;//-1表示该索引i在pq中不存在。
        }
        keys = (Key[])new Comparable[max];
    }

    /**
     * 向索引优先队列中插入元素
     * @param index
     * @param item
     */
    public void insert(int index,Key item){
        keys[index] = item;
        pq[++N] = index;
        qp[index] = N;
        swim(N);
    }

    /**
     * 返回最小元素
     * @return
     */
    public Key min (){
        return keys[pq[1]];
    }

    /**
     * 删除并返回最小元素，从优先队列中取出最小元素的索引pq[1],取出最小元素，
     * 交换pq[1]与pq[N],N减一，pq[1]下沉知道合适的位置，并让qp[min] = -1;
     * @return
     */
    public Key delMin(){
        Key min = keys[pq[1]];
        exch(1,N--);
        sink(1);
        qp[pq[N + 1]] = -1;
        keys[pq[N + 1]] = null;
        return min;
    }
    public boolean isEmpty(){
        return N == 0;
    }

    /**
     * 修改key 沉浮一下
     * @param index
     * @param key
     */
    public void change(int index,Key key){
        if (hasIndex(index)) {
            keys[index] = key;
            swim(qp[index]);
            sink(qp[index]);
        }
    }
    public boolean hasIndex(int index){
        return qp[index] == -1;
    }
    public Key delete(int index){
        if(!hasIndex(index))
            throw new RuntimeException("不存在该索引");
        else{
            exch(index,N--);
            swim(qp[index]);
            sink(qp[index]);
            Key del = keys[index];
            keys[index] = null;
            return del;
        }
    }

    /**
     * 最小元素的索引
     * @return
     */
    public int minIndex(){
        return pq[1];
    }

    private boolean less(int i,int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    /**
     * 交换优先队列pq中key索引的位置
     * @param i
     * @param j
     */
    private void exch(int i,int j){
        qp[pq[i]] = j;
        qp[pq[j]] = i;
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 元素上浮
     * @param k
     */
    private void swim(int k){
        while(k > 1 && less(k,k/2)){
            exch(k,k/2);
            k = k / 2;
        }
    }

    /**
     * 元素下沉
     * @param k
     */
    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if(less(j+1,j)) j++;
            if(less(k,j))break;
            exch(k,j);
            k=j;
        }
    }

}
