package map;

import avltree.AVLTree;

/**
 * @author swadian
 * @date 2020/05/25 20:10
 * @Version 1.0
 */
public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {
    // 引入avlTree
    private AVLTree<K, V> avl;

    public AVLMap() {
        avl = new AVLTree<>();
    }

    @Override
    public void add(K key, V value) {
        avl.add(key, value);
    }

    @Override
    public V remove(K key) {
        return avl.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return avl.contains(key);
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void set(K key, V newValue) {

    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
