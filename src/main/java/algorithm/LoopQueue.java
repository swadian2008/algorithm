package algorithm;

import java.io.InputStream;
import java.util.Random;

/**
 * @author swadi
 * @date 2020/4/9
 * @Version 1.0
 */
public class LoopQueue<E> implements Queue {
    private E[] data;
    private int front, tail;
    // 其实也可以不维护size,只使用front,和tail实现,不过逻辑上会复杂点
    private int size;

    public LoopQueue(int capacity) {
        // 在循环数组中会有意识的浪费一个空间，所以对用户传进来的容量进行+1
        data = (E[]) new Object[capacity + 1];
        int front = 0;
        int tail = 0;
        int size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(java.lang.Object e) {
        // 队列满，需要进行扩容操作
        if ((tail + 1) % data.length == front) {
            resize(2 * getCapacity());
        }
        // tail指向队尾
        data[tail] = (E) e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            // 在循环队列中，元素的位置并不是和新数组中的i一一对应的，具有front个偏移量
            newData[i] = data[(front + 1) % data.length];
        }
        data = newData;
        // 重新的维护逻辑
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        // front 指向队首
        E ret = data[front];
        // 维护一下front 和 size
        front = (front + 1) % data.length;
        size--;
        // 为了节省空间进行缩容操作
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFornt() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty.");
        }
        // 返回队首元素
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: size: " + size + " capacity: " + getCapacity());
        sb.append(" front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            // 判断是不是数组中的最后一个元素
            if ((i + 1) % data.length != tail) {
                sb.append(" , ");
            }
        }
        sb.append("] tail");
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
