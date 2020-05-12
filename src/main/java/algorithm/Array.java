package algorithm;

/**
 * @author swadi
 * @date 2020/4/7
 * @Version 1.0
 */
public class Array<E> {
    // 基于java的数组进行二次封装，private类型不允许外部修改
    private E[] data;
    // 维护一个size,指定了数组中存放了多少元素
    private int size;

    // 构造函数，指定数组的大小
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    // 无参构造，默认创建长度大小为10的数组
    public Array() {
        this(10);
    }

    // 获取数组中元素个数
    public int getSize() {
        return size;
    }

    // 获取数组容量大小
    public int getCapacity() {
        return data.length;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向数组的末尾添加元素
    public void addLast(E e) {
        add(size, e);
    }

    // 向数组的头添加元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 在index位置插入一个新的元素e
    public void add(int index, E e) {
        // 判断index是否有效
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed . require index >= 0 || index <= size");
        }
        // 判断容量是否足够
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // 获取index位置的元素
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("get failed, index is illegal.");
        }
        return data[index];
    }

    public E getLast(){
        return get(size-1);
    }

    public E getFirst(){
        return get(0);
    }

    // 修改index位置的元素e
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("set failed, index is illegal.");
        }
        data[index] = e;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 查找数组中e元素得索引，如果不存在，就返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 从索引中删除index位置得元素,返回被删除得元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed,index is illegal.");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        // 数据类型改为泛型后，存储变为引用，置空便于垃圾回收
        data[size] = null;
        // 进行缩容操作
        if(size <= data.length/4){
            resize(data.length/2);
        }
        return ret;
    }

    // 从数组中删除第一个元素,返回删除元素
    public E removeFirst() {
        return remove(0);
    }

    // 从数组中删除最后一个元素，返回删除元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除元素e
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    // 交换两者之间的位置
    public void swap(int i , int j){
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("index is illegal.");
        }
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // 复写toString方法
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Array: size=" + size + ", capacity=" + data.length + "\n");
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // 方法测试
    public static void main(String[] args) {
        Array<Integer> array = new Array<Integer>(10);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);
        array.add(1, 100);
        System.out.println(array);
        array.addFirst(-1);
        System.out.println(array);
        array.remove(2);
        System.out.println(array);
        array.removeElement(4);
        System.out.println(array);
    }
}
