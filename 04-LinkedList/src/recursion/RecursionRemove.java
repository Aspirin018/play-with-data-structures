package recursion;

/**
 * 使用递归删除链表中的元素
 */
public class RecursionRemove {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode retNode = removeElements(head.next, val);
        if (head.val == val) {
            return retNode;
        } else {
            head.next = retNode;
            return head;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 5, 6, 7};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        RecursionRemove recursionRemove = new RecursionRemove();
        ListNode resultListNode = recursionRemove.removeElements(head, 6);
        System.out.println(resultListNode);
    }
}

/**
 * leetcode中的ListNode
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 为Leetcode的ListNode类新增构造函数
     * 传入数组，生成一个链表
     *
     * @param arr
     */
    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        this.val = arr[0];
        ListNode current = this;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
    }

    /**
     * 为Leetcode中的ListNode添加toString方法，打印出整个链表元素
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        ListNode current = this;
        while (current != null) {
            builder.append(current.val + " -> ");
            current = current.next;
        }
        builder.append("NULL");
        return builder.toString();
    }
}


