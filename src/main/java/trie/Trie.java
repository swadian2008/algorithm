package trie;

import java.util.TreeMap;

/**
 * @author swadian
 * @date 2020/05/06 19:29
 * @Version 1.0
 */
public class Trie {
    private class Node{
        // 是否是单词
        public boolean isWord;
        // 演示中只考虑Character对象,不使用泛型
        public TreeMap<Character,Node> next;
        public Node(boolean isWord){
            this.isWord = isWord;
            new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }
    public Node root;
    // 存储了多少个元素
    public int size;
    public Trie(){
        root = new Node();
        size = 0;
    }
    // 获取trie中存储的单词数量
    public int getSize(){
        return size;
    }
    // 向Trie中添加一个新的单词word
    public void add(String word) {
        Node cur = root;
        // 使用非递归的方式实现
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }
    // 查询单词word是否在Trie中
    public boolean contains(String word){
        Node cur = root;
        // 非递归写法
        for(int i =0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        // 不能直接return true;
        return cur.isWord;
    }
    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
