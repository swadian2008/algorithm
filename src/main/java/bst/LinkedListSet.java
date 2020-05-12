package bst;

import algorithm.ArrayQueue;
import algorithm.LoopQueue;
import algorithm.Queue;
import linklist.LinkedList;

import java.util.Random;

/**
 * @author swadian
 * @date 2020/04/18 17:28
 * @Version 1.0
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;
    public LinkedListSet(){
        list = new LinkedList<E>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            // O(1)级别的方法
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        int opCount = 3000;
        BSTSet<Integer> bstSet = new BSTSet<Integer>();
        double time1 = testSet(bstSet, opCount);
        System.out.println("bstSet :" + time1 + " s");

        LinkedListSet<Integer> linkedListSet = new LinkedListSet<Integer>();
        double time2 = testSet(linkedListSet, opCount);
        System.out.println("linkedListSet :" + time2 + " s");
    }

    private static double testSet(Set<Integer> set, int count) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            // 插入一个从0到int最大数之间的一个随机值,这里随机很重要
            set.add(random.nextInt(Integer.MAX_VALUE));
        }
        long endTime = System.nanoTime();
        // 以秒为单位
        return (endTime - startTime) / 1000000000.0;
    }
}
