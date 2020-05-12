package bst;

/**
 * @author swadian
 * @date 2020/04/18 17:05
 * @Version 1.0
 */
public class BSTSet<E extends Comparable<E>> implements Set {

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<E>();
    }

    @Override
    public void add(Object e) {
        bst.add((E) e);
    }

    @Override
    public void remove(Object e) {
        bst.remove((E) e);
    }

    @Override
    public boolean contains(Object e) {
        return bst.contains((E) e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
