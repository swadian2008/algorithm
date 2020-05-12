package algorithm;

/**
 * @author swadi
 * @date 2020/4/9
 * @Version 1.0
 */
public interface Stack<E> {
    // 获取栈中数据量
    int getSize();
    // 判断栈是否为空
    boolean isEmpty();
    // 向栈顶推送元素
    void push(E e);
    // 从栈中取出元素
    E pop();
    // 查看栈顶元素
    E peek();
}
