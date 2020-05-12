package algorithm;

import linklist.LinkedList;

import java.util.Random;

/**
 * @author swadi
 * @date 2020/4/12
 * @Version 1.0
 */
public class LinkedListStack<E> implements Stack {

    private LinkedList<E> list;

    // 链表没有容量这个概念，因此不需要设置容量
    public LinkedListStack() {
        list = new LinkedList<E>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(Object e) {
        // 向链表头部添加元素是O(1)级别的，头部为栈顶
        list.addFirst((E) e);
    }

    @Override
    public Object pop() {
        return list.removeFirst();
    }

    @Override
    public Object peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: top  ");
        sb.append(list);
        return sb.toString();
    }

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<Integer>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("arrayStack :" + time1 + " s");
        // linkedListStack包含更多new操作
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("linkedListStack :" + time2 + " s");
    }

    private static double testStack(Stack<Integer> stack, int count) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            // 插入一个从0到int最大数之间的一个随机值
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        // 以秒为单位
        return (endTime - startTime) / 1000000000.0;
    }
}
