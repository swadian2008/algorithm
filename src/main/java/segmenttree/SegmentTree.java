package segmenttree;

/**
 * @author swadian
 * @date 2020/04/28 18:55
 * @Version 1.0
 */
public class SegmentTree<E> {
    // 使用数组来存储线段树
    private E[] data;
    private E[] tree;
    // 定义一个融合器
    private Merger<E> merger;
    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for(int i =0;i<arr.length;i++){
            data[i] = arr[i];
        }
        // 存储线段树，需要开辟的存储空间的大小为4n
        tree = (E[]) new Object[4 * arr.length];
        // 创建线段树
        bulidSegmentTree(0, 0, data.length - 1);
    }
    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void bulidSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        // 获取左节点索引
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 计算中间位置
        int mid = l + (r - l) / 2;
        bulidSegmentTree(leftTreeIndex, l, mid);
        bulidSegmentTree(rightTreeIndex, mid + 1, r);
        // 综合两个线段树相应的信息来写业务逻辑，求和，最大，最小等-融合器可以自定义
        tree[treeIndex] = merger.merger(tree[leftTreeIndex],tree[rightTreeIndex]);
    }
    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }
    // 返回【queryL,queryR】之间的值
    public E query(int queryL,int queryR){
        if (queryL < 0 || queryL >= data.length
                || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("index is illegal.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }
    // 在以treeIndex为根的线段树中【l,r】的范围里，搜索区间为【queryL,queryR】的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 递归到底的情况
        if(queryL == l && queryR == r){
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        // 获取左节点和右节点索引
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid + 1) {// 从右子树找
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {// 从左子树找
            return query(leftTreeIndex, l, mid, queryL, queryR);
        } else { // 居中
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merger(leftResult, rightResult);
        }
    }

    // 将index位置得值，更新为e
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal.");
        }
        set(0, 0, data.length - 1, index, e);
    }

    // 在一段区间里更新对应索引得值
    private void set(int treeIndex, int l, int r, int index, E e) {
        // 更新到底的情况
        if(l == r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        // 获取左节点和右节点索引
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1) { // 从右子树查找
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            set(leftTreeIndex, l, mid, index, e);
        }
        // 更改节点会影响到其他相应节点的值，需要重新merger一下
        tree[treeIndex] = merger.merger(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums,(a,b)->a+b);
//        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0,2));
    }
}


