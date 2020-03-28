package com.dhu.search.BST;

/**
 * @Description:红黑二叉查找树
 * @author: snow
 * @Date: 2020-03-19
 **/
public class RedBlackBST<Key extends Comparable<Key>,Value> {

    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        Key key;
        Value value;
        boolean color;  //由父节点指向他的链接的颜色，空连接为黑
        int N;
        Node left,right;
        Node(Key key,Value value,int N, boolean color){
            this.key = key;
            this.value = value;
            this.color = color;
            this.N = N;
        }
    }

    private boolean isRed(Node n){
        if(n == null) return false;
        return n.color == RED;
    }
    public void put(Key key,Value value){
        root = put(root,key,value);
        root.color = BLACK;     //在红键向上传递的过程中，
    }

    /**
     * 递归的插入元素，按照二叉查找树插入的规矩插入，先让插入的节点为红，
     * 然后再通过每一层的递归让红键向上传递，直到遇见一个2-节点（没有红键的节点）或根节点。
     * 同过旋转何颜色转换向上传递红键。旋转的条件为
     * 左红右黑 右旋rotateRight
     * 左红左左红红 左旋rotateLeft
     * 左右皆红  flipColor父变红
     *
     *
     * 2-3-4树的插入算法
     * 将flipColor行移动到测空行之后，在多线程访问树的应用中2-3-4算法更优
     * @param h
     * @param key
     * @param value
     * @return
     */
    private Node put(Node h,Key key,Value value){
        if(h == null) return new Node(key,value,1,RED);
        if(isRed(h.left) && isRed(h.right))
            flipColors(h);
        int cmp = key.compareTo(h.key);
        if(cmp < 0) h.left = put(h.left,key,value);
        else if(cmp > 0) h.right = put(h.right,key,value);
        else h.value = value;

        if(isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * 把红右链接旋转成红左链接
     * @param h
     * @return
     */
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * 把左红键旋转为右红键
     * @param h
     * @return
     */
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * 左右键都是红键的节点左右键换成黑键
     * @param h
     */
    private void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    public void delMin(){

        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delMin(root);
        if(!isEmpty()) root.color = BLACK;
    }
    private Node delMin(Node h){
        if(h.left == null) return null;
        if(!isRed(h.left) && !isRed(h.left.left)) h=moveRedLeft(h);
        h.left = delMin(h.left);
        return balance(h);
    }

    /**
     * 删除最大
     */
    public void delMax(){
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delMax(root);
        if(!isEmpty()) root.color = BLACK;
    }
    private Node delMax(Node h){
        if(isRed(h.left))       //h.left=red右旋h使得h成为3-节点
            h = rotateRight(h);
        if(h.right == null)
            return null;
        if(!isRed(h.right) && !isRed(h.right.left)) //这种情况不能使h和h.right成为3-或4-节点，所以左变色旋转处理 使其变为3-或4-节点
            h = moveRedRight(h);
        h.right = delMax(h.right);
        return balance(h);
    }
    private Node moveRedRight(Node h){  flipColors(h);
        if(!isRed(h.left.left)) //旋转h节点，使得h成为一个4-节点，且h.right=red,h.right.right=red
            h = rotateRight(h);
        return h;
    }
    private Node moveRedLeft(Node h){
        //将h.left或者h.left的子节点之一变为红
        flipColors(h);
        if(isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * 删除节点后恢复平衡
     * @param h
     * @return
     */
    private Node balance(Node h){
        if (isRed(h.right)) h=rotateLeft(h);
        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public int size(){
        return size(root.left) + size(root.right) + 1;
    }
    public boolean isEmpty(){
        return root == null;
    }

    private int size(Node n){
        if(n == null) return 0;
        return n.N;
    }


}
