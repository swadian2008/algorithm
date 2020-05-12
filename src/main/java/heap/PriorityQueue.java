package heap;

import algorithm.Queue;

/**
 * @author swadian
 * @date 2020/04/26 18:50
 * @Version 1.0
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    // 使用最大堆实现优先队列
    private MaxHeap<E> maxHeap;
    public PriorityQueue(){
        maxHeap = new MaxHeap<E>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extraMax();
    }

    @Override
    public E getFornt() {
        return maxHeap.findMax();
    }

    public static void main(String[] args) {
        java.util.PriorityQueue queue = new java.util.PriorityQueue();
    }
}
