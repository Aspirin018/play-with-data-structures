public class LeetCodeOfLinkedList {
    /**
     * 测试实现方案
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 5, 6, 7};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        Solution solution = new Solution();
        ListNode resultListNode = solution.removeElements(head, 6);
        System.out.println(resultListNode);
    }

    /**
     * 方案一：不设置虚拟头节点的实现
     */
    static class Solution {
        //返回删除元素后的链表
        public ListNode removeElements(ListNode head, int val) {
            /**
             * 注意while 有可能连续前几个节点都是需要删除的
             */
            while (head != null && head.val == val) {
                ListNode removeNode = head;
                head = head.next;
                removeNode.next = null;
            }
            //如果head为null 则链表为null
            if (head == null) {
                return null;
            }

            /**
             * 删除链表元素 需要从头遍历 找到要删除的元素的其前一个节点
             */
            ListNode prev = head;
            while (prev.next != null) {
                //if语句执行完 不需要使prev后移，因为有可能下一次循环仍需要删除
                //这里要删的prev.next
                if (prev.next.val == val) {
                    ListNode removeNode = prev.next;
                    prev.next = removeNode.next;
                    removeNode.next = null;
                } else {
                    //这里是遍历到不需要删除的元素，继续向后遍历
                    prev = prev.next;
                }
            }
            return head;
        }
    }

    /**
     * 方案一简化：leetcode中执行完会自动回收内存空间，因此简化后可以不把被删除的元素置空
     */
    class SolutionSimplify {
        public ListNode removeElements(ListNode head, int val) {
            while (head != null && head.val == val) {
                head = head.next;
            }
            if (head == null) {
                return null;
            }
            ListNode prev = head;
            while (prev.next != null) {
                if (prev.next.val == val) {
                    prev.next = prev.next.next;
                } else {
                    prev = prev.next;
                }
            }
            return head;
        }
    }

    /**
     * 方案二：设置虚拟头节点
     */
    class Solution2 {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = head;
            ListNode prev = dummyHead;
            while (prev.next != null) {
                if (prev.next.val == val) {
                    ListNode removeNode = prev.next;
                    prev.next = removeNode.next;
                    removeNode.next = null;
                } else {
                    prev = prev.next;
                }
            }
            return dummyHead.next;
        }
    }

    /**
     * 方案二简化：不手动置空被删除元素
     */
    class Solution2Simplify {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = head;
            ListNode prev = dummyHead;
            while (prev.next != null) {
                if (prev.next.val == val) {
                    prev.next = prev.next.next;
                } else {
                    prev = prev.next;
                }
            }
            return dummyHead.next;
        }
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


