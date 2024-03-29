public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }

        String bstStr = bst.toString();
        System.out.println(bstStr);

        bst.preOrder();
        bst.inOrder();
    }
}
