package algorithm;

import sun.java2d.SurfaceDataProxy;

/**
 * @author swadi
 * @date 2020/4/9
 * @Version 1.0
 */
public class ArrayStack<E> implements Stack{
    // 基于动态数组实现栈
    Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<E>(capacity);
    }

    public ArrayStack(){
        array = new Array<E>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void push(Object e) {
        array.addLast((E) e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public Object peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i <= array.getSize() - 1; i++) {
            sb.append(array.get(i));
            if (i != (array.getSize() - 1)) {
                sb.append(" , ");
            }
        }
        sb.append("] top");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
