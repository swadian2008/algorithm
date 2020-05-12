package linklist;

/**
 * @author swadi
 * @date 2020/4/10
 * @Version 1.0
 */
public class LinkedList<E> {
    // 内部使用私有内部类维护一个节点
    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
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

    private Node dummyHead;
    private int size;
    // 初始化链表
    public LinkedList(){
        // 创建虚拟头节点
        dummyHead = new Node(null,null);
        size = 0;
    }
    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }
    // 返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在链表的index（0-size）位置添加新的元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed,Illegal index");
        }
        // 从第一个头节点开始遍历,此时是虚拟头节点占据了第一个位置
        Node prev = dummyHead;
        // 寻找插入节点之前的前一个位置的节点
        for (int i = 0; i < index; i++) {
            // 不断覆盖下一个节点的连接，直到目标节点的前一个节点
            prev = prev.next;
        }
        // 创建节点/进行交换操作
        prev.next = new Node(e, prev.next);
        size++;
    }

    // 在链表头添加新的元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 在链表的末尾添加新的元素e
    public void addLast(E e) {
        add(size, e);
    }

    // 获取链表中index（0-size）位置的元素
    // 在链表中不是一个常用操作，练习用
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed , Illegal index");
        }
        // 还是使用虚拟头节点
        // 从当前位置的下一个节点的位置进行遍历，跟插入元素选择节点不同
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获取链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获取链表的最后一个元素
    public E get() {
        return get(size - 1);
    }

    // 修改链表中index（0-size）位置的元素e
    // 在链表中不是一个常用操作，练习用
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed , Illegal index");
        }
        // 还是使用虚拟头节点
        // 从当前位置的下一个节点的位置进行遍历，跟插入元素选择节点不同
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        // 当节点为空时，说明已经遍历到了链表的结尾
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 从链表中删除index（0-size）位置的元素e
    // 在链表中不是一个常用操作，练习用
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed , Illegal index");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 被删除的节点
        Node retNode = prev.next;
        prev.next = retNode.next;
        // 被删除的节点不再跟其他节点来连接，彻底跟链表脱离关系
        retNode.next = null;
        size --;
        return retNode.e;
    }

    // 从链表中删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除元素
    public void removeElement(E e){
        Node pre = dummyHead;
        while(pre.next != null){
            if(pre.next.e.equals(e)){
                break;
            }
            pre = pre.next;
        }
        if(pre.next != null){
            // 解除连之间的关系
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        Node cur = dummyHead;
//        while (cur != null) {
//            sb.append(cur + "->");
//            cur = cur.next;
//        }
        // 循环还可以这样写——>开始条件；结束条件；中间操作
        for(Node cur = dummyHead.next;cur != null;cur = cur.next){
            sb.append(cur + " -> ");
        }
        sb.append("NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i =0;i<5;i++){
            // 使用addFirst,因为它是O(1)级别的操作
            list.addFirst(i);
            System.out.println(list);
        }
        // 在索引为2的位置添加新的元素666
        list.add(2,666);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.removeLast();
        System.out.println(list);
    }
}
