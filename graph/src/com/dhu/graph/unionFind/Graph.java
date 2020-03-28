package com.dhu.graph.unionFind;

import java.util.Iterator;

/**
 * @Description:
 * 无向图
 * @author: snow
 * @Date: 2020-03-26
 **/
public class Graph {
    private  final int  vertexes;   //顶点数
    private int edges;  //变数
    private Bag<Integer>[] adj;   //邻接表

    public Graph(int ver){
        this.vertexes = ver;
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int i = 0; i < ver ; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    public int getEdges(){
        return edges;
    }

    public int getVertexes() {
        return vertexes;
    }
    public void addEdge(int p,int q){
        adj[p].put(q);
        adj[p].put(q);
        edges ++;
    }

    /**
     * 返回定点v的所有相邻定点。
     * @param v
     * @return
     */
    public Iterable <Integer> adj(int v){
        return adj[v];
    }
}
