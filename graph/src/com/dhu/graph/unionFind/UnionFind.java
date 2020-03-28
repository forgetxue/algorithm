package com.dhu.graph.unionFind;

/**
 * @Description: union-find算法
 * 分量就是指一个子图，
 * 用数组id[] 来存储触点所在的分量,整型触点对象的值作为id[]的索引，即id[i]表示i的分量
 * @author: snow
 * @Date: 2020-03-26
 **/
public class UnionFind {
    private int N;      //分量的数量
    private int [] id;  //分量的标识，触点的索引（这里以整数作为触电，）

    /**
     * 初始化，所有的触点都未相连，即所有的触点的分量都是自己
     * @param N
     */
    public UnionFind(int N){
        this.N = N;
        id = new int[N];
        for(int i = 0;i < N; N++){
            id[i] = i;
        }
    }

    /**
     * 返回分量的数量
     * @return
     */
    public int count(){
        return this.N;
    }

    /**
     * 触点p所在的分量
     * @param p
     * @return
     */
    public int find(int p){
        return id[p];
    }

    /**
     * 触点p和触点q是否连通，即他们是否在同一个分量之中
     * @param p
     * @param q
     * @return
     */
    public boolean isConnected(int p,int q){
        return id[p] == id[q];
    }

    /**
     * 把p连接到q
     * @param p
     * @param q
     */
    public void union(int p, int q){
        int pId = find(p);
        int qId = find(q);
        if(pId == qId) return;
        for(int i = 0;i < id.length;i++){
            if(id[i] == pId) id[i] = id[q];
        }
        N--;
    }

    /**
     * 对find方法的改进，此时的id[]中，同一分量中的触点不再是同一值，而是改成树型结构，id[i]的值是它的父节点的值，一个分量的根节点
     * id[j] == j;
     * quick和非quick不能同时使用
     * @param p
     * @return
     */
    public int quick_find(int p){
        while(id[p] != p) p = id[p];
        return p;
    }

    /**
     * 把p树连接q树
     * @param p
     * @param q
     */
    public void quick_union(int p, int q){
        int pRoot = quick_find(p);
        int qRoot = quick_find(q);
        if(qRoot == pRoot) return;
        id[pRoot] = qRoot;
        N--;
    }
}
