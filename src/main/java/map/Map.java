package map;

/**
 * @author swadian
 * @date 2020/04/20 21:59
 * @Version 1.0
 */
public interface Map<K,V> {
    void add(K key,V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key,V newValue);
    int getSize();
    boolean isEmpty();
}
