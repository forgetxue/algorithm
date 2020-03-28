package com.dhu.search.binarySearch;

import com.snow.queue.LinkQueue;

import java.util.*;

/**
 * @Description:基于有序数组的二分查找
 * @author: snow
 * @Date: 2020-03-15
 **/
public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values; //keys和values同序
    private int N;
    private int capacity;
    private static final double CAPACITYFACTOR = 0.75;

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        this.capacity = capacity;
    }

    public BinarySearchST() {
    }

    /**
     * 往字符表中添加元素
     * @param key
     * @param value
     */
    public void put(Key key,Value value){
        if(N >= capacity * CAPACITYFACTOR){
            resize();
        }
        int location = rank(key);
        //命中，修改
        if(location < N && key.compareTo(keys[location]) == 0){
            values[location] = value;
            return;
        }
        //为命中。插入
        for(int j=N;j>location;j--){
            keys[j] = keys[j -1];
            values[j] = values[j -1];
        }
        keys[location] = key;
        values[location] = value;
        N++;
    }

    /**
     * 查询元素
     * @param key
     * @return
     */
    public Value get(Key key){
        if(!isEmpty()){
            int location = rank(key);
            if(location < N && key.compareTo(keys[location]) == 0)
                return values[location];
        }
        return null;
    }

    /**
     * 删除元素
     * @param key
     */
    public void remove(Key key){
        if(!isEmpty()){
            int location = rank(key);
            if(location < N && key.compareTo(keys[location]) == 0){
                for(int i = location; i < N; i++){
                    keys[i] = keys[i+1];
                    values[i] = values[i + 1];
                }
                N--;
                if(N < capacity * CAPACITYFACTOR) resize();
            }
        }
    }

    public boolean isEmpty(){
        return N == 0;
    }

    /**
     * 调整内存
     */
    public void resize(){
            int newSize =(int) (N / CAPACITYFACTOR);
            keys = Arrays.copyOf(this.keys, newSize);
            values = Arrays.copyOf(values,newSize);
            capacity = newSize;
    }

    /**
     * 获取最大元素
     * @return
     */
    public Map<Key,Value> max(){
        HashMap<Key, Value> map = new HashMap<>();
        map.put(keys[N-1], values[N-1]);
        return map;
    }
    public Map<Key,Value> min(){
        HashMap<Key, Value> map = new HashMap<>();
        map.put(keys[0], values[0]);
        return map;
    }


    public boolean containsKey(Key key){
        int i = rank(key);
        if(key.compareTo(keys[i])==0)return true;
        return false;
    }

    /**
     * 刚好大于等于key的元素
     * @param key
     * @return
     */
    public Map<Key,Value> ceiling(Key key){
        int i = rank(key);
        HashMap<Key, Value> map = new HashMap<>();
        map.put(keys[i],values[i]);
        return map;
    }


    /**
     * 返回keys中比key小的元素的个数，也就是key在keys中的下标
     * 二分查找
     * @param key
     * @return
     */
    public int rank(Key key){
        int lo = 0,hi = N - 1;
        while(lo <= hi){
            int mid = lo + (hi -lo) / 2;
            if(key.compareTo(keys[mid]) < 0) hi = mid - 1;
            else if(key.compareTo(keys[mid]) > 0) lo = mid  + 1;
            else return mid;
        }
        return lo;
    }
}
