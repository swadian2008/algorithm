package avltree;

import map.LinkedListMap;
import map.Map;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author swadian
 * @date 2020/04/22 22:07
 * @Version 1.0
 */
public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {

    // 因为要存储键值对，所以不能完全复用之前的逻辑
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;// 维护高度值

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    // 辅助函数，获取高度值
    private int getHeight(Node node) {
        if (node == null) {
            return 0;// 空树的高度
        }
        return node.height;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        // 计算高度-更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) { // 平衡因子大于1,平衡性被破坏
            // 向左倾斜-右旋转
            if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
                return rightRotate(node);// 形成新的根节点
            }
            // 向右倾斜-左旋转
            if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
                return leftRotate(node);// 形成新的根节点
            }
            // LR
            if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
                // 转换为LL的情况
                node.left = this.leftRotate(node.left);
                return rightRotate(node);// 形成新的根节点
            }
            // RL
            if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
                // 转换为RR的情况
                node.right = this.rightRotate(node.right);
                return leftRotate(node);// 形成新的根节点
            }
        }
        return node;
    }

    // 计算节点Node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        // 判断是不是具有顺序性
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        // 对二叉树进行中序遍历-中序遍历具有顺序性
        inOrder(node.left, keys);// 对左子树进行遍历
        keys.add(node.key);
        inOrder(node.right, keys);// 对右子树进行遍历
    }

    // 判断二叉树是不是一棵平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 左右子树高度差超过1
    private boolean isBalanced(Node node) {
        if (node == null) {// 空节点也是一棵平衡二叉树
            return true;
        }
        // 当前节点的判断
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        // 对左右子树的判断
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 右旋转操作-此时y节点已经破环平衡
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.left;// 暂存x的左子树
        // 进行右旋转
        x.right = y;
        y.left = T3;
        // 更新height值-先更新y再更新x
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 左旋转
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        // 向左旋转的过程
        x.left = y;
        y.right = T2;
        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " is Empty !");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 辅助函数
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
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

    // 删除Map中任意元素
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            // 删除元素
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        // 二分搜索树为空
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            // 大于根节点，从右子树删除
            node.right = remove(node.right, key);
            return node;
        } else if (key.compareTo(node.key) < 0) {
            // 小于根节点，从左子树删除
            node.left = remove(node.left, key);
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

    public static void main(String[] args) {
        int opCount = 20000;
        AVLTree<String, Object> bstMap = new AVLTree<String, Object>();
        double time1 = testSet(bstMap, opCount);
        System.out.println("bstMap :" + time1 + " s");

        LinkedListMap<String, Object> linkedListMap = new LinkedListMap<String, Object>();
        double time2 = testSet(linkedListMap, opCount);
        System.out.println("linkedListMap :" + time2 + " s");
    }

    private static double testSet(Map<String, Object> map, int count) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            map.add("key" + random.nextInt(Integer.MAX_VALUE), "value" + random.nextInt(Integer.MAX_VALUE));
        }
        long endTime = System.nanoTime();
        // 以秒为单位
        return (endTime - startTime) / 1000000000.0;
    }
}

    /*public static void main(String[] args) {
        List<String> list = Arrays.asList("aa","aa","aa","bb","bb","cc","cc");
        BSTMap<String,Integer> map = new BSTMap<String, Integer>();
        for(String str : list){
            if(map.contains(str)){
                map.set(str,map.get(str)+1);
            }else{
                map.add(str,1);
            }
        }
        System.out.println(map.getSize());
        System.out.println("aa:"+map.get("aa"));
        System.out.println("bb:"+map.get("bb"));
        System.out.println("cc:"+map.get("cc"));
    }*/
