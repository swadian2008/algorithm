package linklist;

/**
 * @author swadi
 * @date 2020/4/13
 * @Version 1.0
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x){
        val = x;
    }

    // 链表节点的构造函数
    // 使用arr作为参数，创建一个链表，当前的ListNode为链表头节点
    public ListNode(int[] arr){
        if(arr == null && arr.length == 0){
            throw new IllegalArgumentException("arr cannot be empty.");
        }
        // 头节点
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            // 不断循环赋值
            cur = cur.next;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            sb.append(cur.val + "->");
            // 循环移动
            cur = cur.next;
        }
        // 表示已经到达了链表末尾
        sb.append("NULL");
        return sb.toString();
    }
}
