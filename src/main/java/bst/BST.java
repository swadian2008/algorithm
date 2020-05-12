package bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author swadi
 * @date 2020/4/14
 * @Version 1.0
 */
// 这里限制泛型E,是可以用来比较的元素
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 根节点
    private Node root;
    // 二叉树中存储的数据多少
    private int size;

    // 初始化二分搜索树
    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加新元素
    public void add(E e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素E,递归实现
    // 返回插入节点后的二分搜索树的跟
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    // 查看二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 看以node为根的二分搜索树中是否包含元素e,递归算法
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.equals(node.e)) {
            return true;
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return contains(node.left, e);
        }
    }

    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历从根节点处进行遍历
    public void preOrder(Node node) {
        // 递归终止条件
        if (node == null) {
            return;
        }
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 二分搜索树前序遍历非递归实现
    public void preOrderNR(){
        Stack<Node> stack = new Stack<Node>();
        // 根元素压入栈
        stack.push(root);
        while(!stack.isEmpty()){
            // 取出栈元素
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right != null){
                stack.push(cur.right);
            }
            // 后进先出
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    // 二分搜索树的层序遍历
    public void levelOrder() {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    // 二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        // 先访问左子树
        inOrder(node.left);
        System.out.print(node.e + " ");
        // 最后访问右子树
        inOrder(node.right);
    }

    // 后序遍历
    public void postOrder() {
        postOrder(root);
    }

    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }

    // 寻找二分搜索树中的最小元素
    public E minimum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty.");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    // 寻找二分搜索树中的最大元素
    public E maximum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty.");
        }
        return  maximum(root).e;
    }

    private Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    // 删除最小元素
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        // 递归到底的逻辑
        if (node.left == null) {
            // 保留右子树
            Node rightNode = node.right;
            // 删除最小值，此时的右子树成为新的节点
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 删除最大元素
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        // 递归到底的逻辑
        if (node.right == null) {
            // 保留左子树
            Node leftNode = node.left;
            // 除了左子树以外，其他都不要，也就是把元素删除了
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 删除任意元素
    public void remove(E e) {
        // 返回删除元素后的二叉树
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        // 二分搜索树为空
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) > 0) {
            // 大于根节点，从右子树删除
            node.right = remove(node.right, e);
            return node;
        } else if (e.compareTo(node.e) < 0) {
            // 小于根节点，从左子树删除
            node.left = remove(node.left, e);
            return node;
        } else { // 等于根节点
            // 如果左子树为空，直接用右子树来替换
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 如果右子树为空，直接用左子树替换
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点左右都不为空的情况
            // 先找到右子树中后继节点，然后在右子树中删除该节点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }
        sb.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, sb);
        generateBSTString(node.right, depth + 1, sb);
    }

    // 遍历深度
    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
//        bst.removeMin();
//        bst.postOrder();
        bst.remove(6);
        bst.postOrder();

    }
}
