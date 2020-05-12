package heap;

import algorithm.Array;

import java.util.Random;

/**
 * @author swadian
 * @date 2020/04/25 15:57
 * @Version 1.0
 */
public class MaxHeap<E extends Comparable<E>> {
    // 使用最大堆，所存储的元素都是可以用来比较的
    private Array<E> data;
    // 构造方法
    public MaxHeap(){
        data = new Array<E>();
    }
    public MaxHeap(int capacity){
        data = new Array<E>(capacity);
    }

    public MaxHeap(E[] arr) {
        data = new Array<E>(arr);
        for (int i = parent(data.getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    // 获取元素存储数量
    public int size(){
        return data.getSize();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    // 设计辅助函数，获取父节点、左孩子节点和右孩子节点的索引
    private int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }
    // 完全二叉树数组表示中,获取左孩子
    private int leftChild(int index) {
        return index * 2 + 1;
    }
    // 完全二叉树数组表示中，获取右孩子
    private int rightChild(int index){
        return index * 2 + 2;
    }
    // 向堆中添加元素
    public void add(E e){
        data.addLast(e);
        // 维护一下元素的性质
        siftUp(data.getSize()-1);
    }
    private void siftUp(int k){
        // 如果父节点比当前节点要小，交换两者的位置
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k,parent(k));
            // 重新赋值=当前索引=原父节点位置的索引，进行新一轮比较
            k = parent(k);
        }
    }
    // 获取堆中最大的元素
    public E findMax(){
        if(data.getSize() == 0){
            throw new IllegalArgumentException("heap is empty。");
        }
        return data.get(0);
    }
    // 取出堆中最大的元素
    public E extraMax() {
        E ret = findMax();
        // 交换位置
        data.swap(0, data.getSize() - 1);
        // 删除末尾的元素
        data.removeLast();
        siftDown(0);
        return ret;
    }
    private void siftDown(int k) {
        // 循环条件，获取左孩子的索引值，索引值没有越界，左孩子存在
        while(leftChild(k) < data.getSize()){
            // 左孩子节点的索引
            int j = leftChild(k);
            // 如果右孩子存在,且右孩子的值大于左孩子的值,那么返回右孩子的索引
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }
            // data[j] 时左孩子和右孩子中的最大值
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            // 交换两者之间的值
            data.swap(k, j);
            // 替换索引进行新的循环和对比
            k = j;
        }
    }
    // 取出最大的元素，并替换成e
    public E replace(E e) {
        E ret = extraMax();
        data.set(0, e);
        // 元素下沉操作
        siftDown(0);
        return ret;
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long start = System.nanoTime();
        if (isHeapify) {
            MaxHeap maxHeap = new MaxHeap(testData);
        }
        for (int i : testData) {
            MaxHeap maxHeap = new MaxHeap();
            maxHeap.add(i);
        }
        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {
        int n = 10;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(testData, false);
        double time2 = testHeap(testData, true);
        System.out.println("not heapify:" + time1);
        System.out.println("heapify:" + time2);
    }
}
