package algorithm;


import linklist.LinkedList;

import java.util.Random;

/**
 * @author swadi
 * @date 2020/4/12
 * @Version 1.0
 */
public class LinkedListQueue<E> implements Queue{
    // 内部使用私有内部类维护一个节点
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }
    private Node head,tail;
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(Object e) {
        if(tail == null){
            tail = new Node((E) e);
            head = tail;
        }else{
            tail.next = new Node((E) e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if(head == null){
            // 删除的元素时链表中唯一的元素，需要维护下tail
            tail = null;
        }
        size --;
        return retNode.e;
    }

    @Override
    public E getFornt() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front");
        Node cur = head;
        while(cur != null){
            sb.append(cur + "->");
            cur = cur.next;
        }
        sb.append("NULL tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        int opCount = 100000;
        LoopQueue<Integer> loopQueue = new LoopQueue<Integer>();
        double time1 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue :" + time1 + " s");

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
        double time2 = testQueue(arrayQueue, opCount);
        System.out.println("arrayQueue :" + time2 + " s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("linkedListQueue :" + time3 + " s");
    }

    private static double testQueue(Queue<Integer> queue, int count) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            // 插入一个从0到int最大数之间的一个随机值
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        // 以秒为单位
        return (endTime - startTime) / 1000000000.0;
    }

}
