package algorithm;

/**
 * @author swadi
 * @date 2020/4/9
 * @Version 1.0
 */
public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    // 放入元素
    void enqueue(E e);
    // 拿出元素
    E dequeue();
    // 查看队列元素
    E getFornt();
}
