package map;

import bst.BST;
import bst.BSTSet;
import bst.LinkedListSet;
import bst.Set;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author swadian
 * @date 2020/04/22 22:07
 * @Version 1.0
 */
public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

    // 因为要存储键值对，所以不能完全复用之前的逻辑
    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node,K key,V value){
        if(node == null){
            size ++;
            return new Node(key,value);
        }
        if(key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }else{
            node.value = value;
        }
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if(node == null){
            throw new IllegalArgumentException(key +" is Empty !");
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
    private Node getNode(Node node,K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) == 0){
            return node;
        }else if(key.compareTo(node.key) < 0){
            return getNode(node.left,key);
        }else{
            return getNode(node.right,key);
        }
    }

    private Node minimum(Node node){
        if(node.left == null){
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
        if(node != null){
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
        BSTMap<String,Object> bstMap = new BSTMap<String,Object>();
        double time1 = testSet(bstMap, opCount);
        System.out.println("bstMap :" + time1 + " s");

        LinkedListMap<String,Object> linkedListMap = new LinkedListMap<String,Object>();
        double time2 = testSet(linkedListMap, opCount);
        System.out.println("linkedListMap :" + time2 + " s");
    }

    private static double testSet(Map<String,Object> map, int count) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            map.add("key"+random.nextInt(Integer.MAX_VALUE),"value"+random.nextInt(Integer.MAX_VALUE));
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
