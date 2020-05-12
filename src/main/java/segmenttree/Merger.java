package segmenttree;

/**
 * @author swadian
 * @date 2020/04/28 19:34
 * @Version 1.0
 */
public interface Merger<E> {
    // 将两个值整合成一个值
    E merger(E a,E b);
}
