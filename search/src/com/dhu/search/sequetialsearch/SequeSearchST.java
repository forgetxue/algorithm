package com.dhu.search.sequetialsearch;

/**
 * @Description: 基于无序链表的顺序查找
 * @author: snow
 * @Date: 2020-03-14
 **/
public class SequeSearchST<Key, Value> {
    private Node head = new Node();
    private int N;//节点数


    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public Node(Node node) {
            this.val = node.val;
            this.key = key;
        }
        public Node(){}
    }

    public void put(Key key, Value val) {
        if (val == null) {
            remove(key);
            return;
        }
        for (Node n = head.next; n != null; n = n.next) {
            if (n.key.equals(key)) {
                n.val = val;            //击中，更新
                return;
            }
        }
        head.next = new Node(key, val, head.next);//头插法N++;
        N++;
    }

    public Value get(Key key) {
        for (Node n = head.next; n != null; n = n.next) {
            if (n.key.equals(key))
                return n.val;
        }
        return null;
    }

    /**
     * 延时性删除key===>value==null;
     *
     * @param key
     */
    public void delete(Key key) {
        for (Node n = head.next; n != null; n = n.next) {
            if (n.key.equals(key))
                n.val = null;
        }
    }

    public void remove(Key key) {
        for (Node n = head; n.next != null; n = n.next) {
            if (n.next.key.equals(key)) {
                n.next = n.next.next;
                N--;
                return;//这里return 是必要的
            }
        }
    }

}
