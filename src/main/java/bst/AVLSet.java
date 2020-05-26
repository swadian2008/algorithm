package bst;

import avltree.AVLTree;

/**
 * @author swadian
 * @date 2020/05/25 20:19
 * @Version 1.0
 */
public class AVLSet<E extends Comparable<E>> implements Set<E> {
    // 在集合中我们不关注value,只关注key,用到value时,设置为null
    private AVLTree<E, Object> avl;

    public AVLSet() {
        avl = new AVLTree<>();
    }

    @Override
    public void add(E e) {
        avl.add(e, null);// 集合不是键值对，因此值直接传空
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
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
