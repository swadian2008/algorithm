package map;

import linklist.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author swadian
 * @date 2020/04/20 22:06
 * @Version 1.0
 */
public class LinkedListMap<K,V> implements Map<K,V>{

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key,null,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString(){
            return key.toString() + ":" +value.toString();
        }
    }

    // 虚拟头节点
    private Node dummyHead;
    private int size;

    public LinkedListMap(){
       dummyHead = new Node();
       size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            // 向链表头添加元素
            dummyHead.next = new Node(key,value,dummyHead.next);
            size++;
        }else{
            // 替换对应键的值
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node pre = dummyHead;
        while(pre.next != null){
            if(pre.next.key.equals(key)){
                break;
            }
            pre = pre.next;
        }
        if(pre.next != null){
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if(node == null){
            throw new IllegalArgumentException(key +"doesn't exits!");
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
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.key.equals(key)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
}

//    public static void main(String[] args) {
//        List<String> list = Arrays.asList("aa","aa","aa","bb","bb","cc","cc");
//        LinkedListMap<String,Integer> map = new LinkedListMap<String, Integer>();
//        for(String str : list){
//            if(map.contains(str)){
//                map.set(str,map.get(str)+1);
//            }else{
//                map.add(str,1);
//            }
//        }
//        System.out.println(map.getSize());
//        System.out.println("aa:"+map.get("aa"));
//        System.out.println("bb:"+map.get("bb"));
//        System.out.println("cc:"+map.get("cc"));
//    }
