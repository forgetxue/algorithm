package com.dhu.search.separate;


/**
 * @Description:散列表的线性探法实现
 * @author: snow
 * @Date: 2020-03-24
 **/
public class LinerProbingHashST<Key,Value> {
    private int N;  //元素的个数
    private int M = 16;  // 散列表的大小
    private Key[] keys;
    private Value[] values;
    public LinerProbingHashST(){
        keys = (Key[]) new Object[M];
        values = (Value[]) new java.lang.Object[M];
    }
    private int hash(Key key){
        return (key.hashCode() & 0xfffffff) & M;
    }
    public LinerProbingHashST(int cap){
        this.M = cap;
        keys = (Key[]) new Object[cap];
        values = (Value[]) new Object[cap];
    }

    /**
     * 从hash得到的开始索引遍历，直到遇见空的位置
     * @param key
     * @param value
     */
    public void put(Key key,Value value){
        if (N >= M / 2) resize(2 * M); //总保持数组的一半以下的占用率
        int i;
        for(i = hash(key); keys[i] != null; i = (i + 1) % M){
            if(keys[i].equals(key))
                values[i] = value;
        }
        keys[i] = key;
        values[i] = value;
        N ++;
    }
    public Value get(Key key){
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M){
            if(key.equals(keys[i])) return values[i];
        }
        return null;
    }

    /**
     * 由于删除一个元素后，数组中间会出现一个空位，在get等方法中判空为条件时会出现错误，所以空位之后的元素要重新put
     * @param key
     */
    public void delete(Key key){
        if(!containsKey(key)) return;
        int i = hash(key);
        while(!key.equals(keys[i]))
            i = (i + 1) % M;
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        //重装
        while(keys[i] != null){
            Key reloadKey = keys[i];
            Value reloadVal = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(reloadKey,reloadVal);
            i = (i + 1) % M;
        }
        if(N > 0 && N < M / 2)resize(M /2);
    }
    private boolean containsKey(Key key){
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M){
            if(key.equals(keys[i])) return true;
        }
        return false;
    }

    /**
     * 用一个新的对象来扩容，而非数组。
     * @param capacity
     */
    private void resize(int capacity){
        LinerProbingHashST<Key,Value> st = new LinerProbingHashST<>(capacity);
        for(int i = 0; i < M; i++){
            st.put(keys[i],values[i]);
        }
        keys = st.keys;
        values = st.values;
        M = st.M;
    }
}
