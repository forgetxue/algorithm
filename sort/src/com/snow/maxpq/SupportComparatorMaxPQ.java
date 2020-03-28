package com.snow.maxpq;

import java.util.Comparator;
import java.util.Objects;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-03-13
 **/
public class SupportComparatorMaxPQ<Key> {
    private Key[] pq;
    private int N;
    private Comparator<Key> c;

    public SupportComparatorMaxPQ(int maxN){
        pq = (Key[]) new Object[maxN + 1];
    }

    public SupportComparatorMaxPQ() {

    }


    public void insert(Key key){
        if(N < pq.length -1){
            pq[++N] = key;
            swim(N);
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public Key delMax(){
        if(N > 0){
            exch(1,N);
            sink(1);
            Key max = pq[N--];
            pq[N+1] = null;
            return max;
        }else{
            throw new IndexOutOfBoundsException();
        }
    }
    public Key max(){
        return pq[1];
    }

    //pq[i]是否小于pq[j]
    private boolean less(int i,int j){
        return c.compare(pq[i],pq[j]) < 0;
    }
    private void exch(int i,int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    private void swim(int k){
        while(k > 1 && less(k/2,k)){
            exch(k,k/2);
            k = k / 2;
        }
    }

    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if(j < N && less(j , j + 1)) j++;
            if(!less(k,j)) break;
            exch(k,j);
            k = j;
        }
    }
    public void setComparator(Comparator<Key> c){
        this.c = c;
    }
}
