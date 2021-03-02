package recursion;

/**
 * 用打印输出的方式调试递归程序
 */
public class RecursionRemoveDebug {
    //加入栈深度参数depth
    public ListNode removeElements(ListNode head, int val, int depth) {
        String depthStr = generateDepthString(depth);
        System.out.print(depthStr);
        System.out.println("Remove " + val + " from " + head);
        if (head == null) {
            System.out.print(depthStr);
            System.out.println("Return : " + head);
            return null;
        }
        ListNode retNode = removeElements(head.next, val, depth + 1);
        System.out.print(depthStr);
        System.out.println("After remove " + val + " : " + retNode);
        if (head.val == val) {
            System.out.print(depthStr);
            System.out.println("Return : " + retNode);
            return retNode;
        } else {
            head.next = retNode;
            System.out.print(depthStr);
            System.out.println("Return : " + head);
            return head;
        }
    }

    //能清晰表示栈深度的字符串
    private String generateDepthString(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("--");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 5, 6, 7};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        RecursionRemoveDebug debug = new RecursionRemoveDebug();
        ListNode resultListNode = debug.removeElements(head, 6, 0);
        System.out.println(resultListNode);
    }
}
