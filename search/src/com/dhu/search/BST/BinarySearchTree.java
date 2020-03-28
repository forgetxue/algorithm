package com.dhu.search.BST;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:二叉查找数
 * @author: snow
 * @Date: 2020-03-15
 **/
public class BinarySearchTree<Key extends Comparable<Key>,Value> {
    private Node root;

    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int N;          //一以此节点为根的子数的节点数
        public Node(Key key,Value val,int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    /**
     * 递归的获取节点总数
     * @param node
     * @return
     */
    private int size(Node node){
        if(node == null) return 0;
        return node.N;
    }
    public int size(){
        return size(root);
    }

    /**
     * 递归的插入
     * @param key
     * @param val
     */
    public void put(Key key,Value val){
        root = put(root,key,val);
    }
    private Node put(Node n,Key key,Value value){
        if(n == null)
            return new Node(key,value,1);
        int cmp = key.compareTo(n.key);
        if(cmp < 0)
            n.left=put(n.left,key,value);
        else if(cmp > 0)
            n.right=put(n.right,key,value);
        else
            n.val = value;
        n.N = size(n.left) + size(n.right) + 1;
        System.out.println("return n");
        return n;
    }

    /**
     * 获取key对应的value
     * @param key
     * @return
     */
    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node n,Key key){
        if(n == null)
            return null;
        int cmp = key.compareTo(n.key);
        if(cmp < 0)
            return get(n.left,key);
        else if(cmp > 0)
            return get(n.right,key);
        else
            return n.val;
    }

    /**
     * 非递归插入，已近验证比递归执行速度快
     * @param key
     * @param value
     */
    public void putNotRecursive(Key key,Value value){
        if(root == null) {
            root = new Node(key,value,1);
            return;
        }
        Node n = root;
        Node f = root;//记录父节点
        while(n != null){
            f = n;
            int cmp = key.compareTo(n.key);
            if(cmp < 0)
                n = n.left;
            else if (cmp > 0)
                n = n.right;
            else {
                n.val = value;
                return;
            }
            f.N++;
        }
        Node node = new Node(key,value,1);
        int cmp = key.compareTo(f.key);
        if(cmp < 0)
            f.left = node;
        else
            f.right = node;

    }

    /**
     * 非递归的查询
     * @param key
     * @return
     */
    public Value getNotRecursive(Key key){
        Node n = root;
        while(n != null){
            int cmp = key.compareTo(n.key);
            if(cmp < 0)
                n = n.left;
            else if(cmp > 0)
                n = n.right;
            else
                return n.val;
        }
        return null;
    }


    /**
     * 返回比小于等于key的最大元素
     * @param key
     * @return
     */
    public Response floor(Key key){
        Node node = floor(root, key);
        if(node != null)
            return new Response(node.key,node.val);
        return null;
    }

    /**
     * 递归查询
     * @param n
     * @param key
     * @return
     */
    private Node floor(Node n, Key key){
        if(n == null) return null;
        int cmp = key.compareTo(n.key);
        if(cmp < 0) return floor(n.left,key);     //如果key小于根节点，就在左子树种查找
        if(cmp == 0) return n;
        //cmp > 0,key大于子树的根节点
        Node temp = floor(n.right,key);//key大于根节点，比key小的元素或许存在或许不存在，如果不存在，结果就是根节点
        if(temp == null) return n;
        return temp;
    }
    /**
     * 递归的查找第一个大于等于key的元素
     * @param key
     * @return
     */
    public Response ceiling(Key key){
        Node ceiling = ceiling(root, key);
        if(ceiling != null)
            return new Response(ceiling.key,ceiling.val);
        return null;
    }

    private Node ceiling(Node n,Key key){
        if(n == null) return null;
        int cmp = key.compareTo(n.key);
        if(cmp > 0) return ceiling(n.right,key);       //如果key大于root，如果存在只会在右子树中。
        if(cmp == 0) return n;
        Node temp = ceiling(n.left,key); //在左子树种查找，如果不存在比key大的结果就是根节点
        if(temp != null) return temp;
        return n;
    }

    /**
     * 返回key在字符表中的排名，也就是比key小的节点数目。
     * @param key
     * @return
     */
    public int rank(Key key){
        return rank(root,key);
    }

    /**
     * 要得到比key小的节点数目，就是要先找到第一个比key大的节点
     * @param node
     * @param key
     * @return
     */
    private int rank(Node node, Key key){
        if(node == null) return 0;      //已经递归到到叶节点以下，还没有比key小的节点，结果为0
        int cmp = key.compareTo(node.key);
        if(cmp < 0) return rank(node.left,key);    //如果key小于当前递归子树的根节点，
        // 那么第一个人大于key的节点很可能会在左子字数中，所以在左子树中查找
        else if(cmp > 0) return 1 + size(node.left) + rank(node.right,key);//如果key大于当前root,第一个大于root的节点在
        // 当前root的右子树中，比该节点小的节点数就是当前root节点左子树中的数量 + 1（root)  + 递归rank(right)的结果
        else return size(node.left); //如果key==当前root小于key的数量就是当前子树根节点的size(left)
    }

    /**
     *
     * 返回排名为k的元素
     * @param k
     * @return
     */
    public Response select(int k){
        return select(root,k);
    }

    /**
     * 返回以temp为根节点的子树中，排名为k的key-value,
     * 在递归执行时，如果temp == null就返回null意思是一直找到null还未找到满足条件的节点，一般这种情况不会发生，除非k <= 0
     * 排名k也就是字符表中小于目标节点的节点数目。因此我们要找的就是某一次递归中它的做子树节点的数目 tr.left.N== k(每次执行递归可能不同）
     * 如果k小于tr.left.N,说明小于tr节点的数目，要比我们要找的节点的节点数大，说明目标节点在tr的左子树中，所以这是递归左子树。
     *
     * 当k大于tr.left.N,说明小于tr的节点，在tr的左子树中，不可能满足等于K个，所以这是要向比tr的的节点们中寻找，这是tr.left中已经有
     * tr.left.N个节点，所以只需要在右字数中找到比他小的节点数为k-tr.left.N-1个的节点，即target.left.N = k-tr.left.N -1
     *
     * 如果tr.left.N == k,那么刚好目标节点就是tr
     * @param tempRoot
     * @param k
     * @return
     */
    private Response select(Node tempRoot, int k){
        //返回以temp为根节点的子树中排名为k的节点
        if(tempRoot == null) return null;
        int n = size(tempRoot.left);
        if(k < n) return select(tempRoot.left,k);
        else if(k > n) return select(tempRoot.right,k - n -1);
        else return new Response(tempRoot.key,tempRoot.val);
    }

    /**
     * 删除最小元素
     * @return
     */
    public Node delMin(){
        return  delMin(root);
    }

    /**删除最小节点，就是删除一直遍历Left直到tr.left==null,tr就是最小的元素。
     * 删除的具体操作为：当前tr是最小元素，删除那就要断开tr的父节点和tr的链接，然后用tr的右兄弟节点来代替tr,这就需要在遍历的时候
     * 记录tr父节点。同样可以利用递归来实现。利用递归来遍历，上一次遍历的tr的就是当前tr的父节点，可以利用这一点实现这一操作。
     * 显然使用递归要比循环优雅得多。但效率会稍稍差一点。我们利用在每次遍历中的tr.left = delMin(tr.left)来实现递归，再加上递归函数的返回节点
     * 为Node ，如果为tr.left == null,那么当前tr是最小节点我们返回tr.right，这样就把tr.right连接到tr的父节点，
     * 如果当前节点还不是最小节点，就返回它本身，这样tr.father.left还是tr.
     *
     * 最后，因为要删除节点，所以要更新每一个节点的N。
     *但是注意，返回的结果并不是删除了的那个结果，而是删除后的根节点根节点。
     *
     * @param tr 当前遍历字数的临时根节点
     * @return
     */
    private Node delMin(Node tr){
        if(tr.left == null) return tr.right;
        tr.left = delMin(tr.left);
        tr.N = size(tr.left) + size(tr.right) + 1;
        return tr;
    }

    /**
     * 删除最大的节点
     * @return
     */
    public Node delMax(){
        return delMax(root);
    }
    private Node delMax(Node tr){
        if(tr == null) return tr.left;
        tr.right = delMax(tr.right);
        tr.N = size(tr.left) + size(tr.right);
        return tr;

    }

    /**
     * 删除键为key的节点，并返回新的树（根节点）
     * @param key
     * @return
     */
    public  Response remove(Key key){
        Node remove = remove(root, key);
        return new Response(remove.key,remove.val);
    }

    /**
     * 要删除二叉查找树中的指定节点，就会多出来两棵子树，这两棵子树不能被删除，所以就要找到一个节点来顶替要被删除的节点
     * 用这个替补节点来连接被删除节点下的子树。这里的实现用子树中的合适的节点来顶替该节点。问题的关键就是找到这个合适的节点，并对子树做出相应
     * 的调整。
     * 根据二叉查找树的规则，左子树中的元素都小于根节点，右子树中的节点都大于根节点，（现在讨论的是以被删除节点为根节点的二叉查找树
     * 先不说如何遍历找到这个树，且说替换的操作。）我们要找的替补节点就是该树（以删除元素为根节点）右子树中最小的元素，
     * 创建一个临时变量t让他指向当前的tr,
     * 让tr=min(tr.right),实现右子树中最小元素代替了要删除的节点
     * 然后tr.right = delMin(tr.right)让新的tr连接右子树的根节点，并从右子树的叶子节点中删除新的tr
     * 然后在把原来tr的left连接到新tr的left,因为原来tr的节点依然都小于新的tr
     *
     * 再来看tr与父节点的链接，因为是递归的遍历，所以上次递归的tr就是这次递归的父节点，所以，当执行完当前递归并返回改动过的tr
     * 之后，在上次层里实现了连接
     *
     * @param tr
     * @param key
     * @return
     */
    private Node remove(Node tr,Key key){
        if(tr == null) return null;
        int cmp = key.compareTo(tr.key);
        if (cmp < 0) tr.left = remove(tr.left,key);
        else if (cmp > 0) tr.right =  remove(tr.right,key);
        else{
            Node t = tr;
            //如果做子树为空，右子树本来是有序的不收被删除元素而打破有序，所以让右子树直接挂到被删除节点的父节点
            if(t.left == null) return t.right;
            if(t.right == null) return  t.left;
            //左右子树都存在，左右字数的有序收根节点tr的影响
            tr = min(t.right);
            tr.right = delMin(t.right);     //注意，这里的delMin返回的并不是被删除了的节点，而是根节点。
            tr.left = t.left;
        }
        tr.N = size(tr.left) + size(tr.right) + 1;
        return tr;
    }
    public List<Key> keys(Key lo,Key hi){
        return keys(root,lo,hi);
    }
    private List<Key> keys(Node tr,Key lo,Key hi){
        ArrayList<Key> keys = new ArrayList<>();
        keys(keys,lo,hi,tr);
        return keys;
    }

    /**
     * 借助中序遍历，先遍历左子树再遍历根节点，然后再遍历右子树，再遍历左子树的时候加上限定条件lo < tr，如果不满足就没有必要遍历左子树
     * 遍历右子树的时候加上限定条件：tr < hi,如果不满足这个条件，就没有必要遍历右子树。
     * @param keys
     * @param lo
     * @param hi
     * @param tr
     */
    private void keys(List<Key> keys,Key lo,Key hi,Node tr){
        if(tr == null) return;
        int cmplo = lo.compareTo(tr.key);
        int cmphi = hi.compareTo(tr.key);
        if(cmplo < 0) keys(keys,lo,hi,tr.left); //如果lo < tr.key 遍历tr的左子树
        if(cmplo <= 0 && cmphi >= 0) keys.add(tr.key);
        if(cmphi > 0) keys(keys,lo,hi,tr.right); // 如果 hi > tr.key遍历tr的右子树

    }

    /**
     * 返回最小值
     * @param tr
     * @return
     */
    private Node min(Node tr){
        if(tr.left == null) return tr;
        return min(tr.left);
    }
    public Response min(){

        Node n = root;
        while(n != null){
            if(n.left == null)return new Response(n.key,n.val);
            n = n.left;
        }
        return null;
    }

    /**
     * 非递归的返回最大值
     * @return
     */
    public Response max(){
        Node n = root;
        while(n != null){
            if(n.right == null){
                return new Response(n.key,n.val);
            }
            n= n.right;
        }
        return null;
    }
    private Node max(Node n){
        if(n.right == null) return n;
        return max(n.right);
    }


    /**
     * 返回值辅助类
     */
    public class Response{
        Key key;
        Value value;
        public Response(Key key,Value value){
            this.value = value;
            this.key = key;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

}
