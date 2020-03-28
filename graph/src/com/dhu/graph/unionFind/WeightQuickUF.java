package com.dhu.graph.unionFind;

/**
 * @Description:
 * 加权quick-union-find
 *
 * 对union-find的算法的改进，如果p，q属于同一分量，不必把id[i] = p的所有分量改成q,而是改用一种树状的结构，每个触点的id值只是父节点
 * 而id[j] == j 的触点就是根节点，在union时把较小的树连接到较大的树（加权）。
 *
 * @author: snow
 * @Date: 2020-03-26
 **/
public class WeightQuickUF {
    private int[] id;
    private int count;
    private int [] size;    //每课树的节点数

    public WeightQuickUF(int N){
        this.count = N;
        id = new int[N];
        size = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 分量数，即森林中树的数
     * @return
     */
    public int count(){
        return count;
    }

    /**
     * 找到p的根节点
     * @param p
     * @return
     */
    public int find(int p){
        while(id[p] != p) p = id[p];
        return p;
    }
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * 较小树连接到较大树
     * @param p
     * @param q
     */
    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(qRoot == pRoot) return;
        if(size[qRoot] < size[pRoot])
            id[qRoot] = pRoot;
        else
            id[pRoot] = qRoot;
    }


}
