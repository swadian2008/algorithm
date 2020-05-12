package linklist;

/**
 * @author swadi
 * @date 2020/4/13
 * @Version 1.0
 */
public class Solution {

    public ListNode removeElements(ListNode head, int val) {
        // 从头节点开始判断起
        if(head == null){
            return null;
        }
        // 除头节点的外的，更短的链表
        head.next = removeElements(head.next,val);
        return head.val == val ? head.next : head;
    }
}

   /* public static void main(String[] args) {
        int[] arr = {1,2,3,6,3,4,5,6};
        ListNode list = new ListNode(arr);
        System.out.println(list);
        Solution solution = new Solution();
        ListNode listNode = solution.removeElements(list, 6);
        System.out.println(listNode);
    }*/
