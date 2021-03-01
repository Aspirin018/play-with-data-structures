import arraystackofmodule2.ArrayStack;
import arraystackofmodule2.Stack;

import java.util.Random;

/**
 * 测试比较ArrayStack和LinkedListStack的
 */
public class TestCompare {

    private static void test(Stack<Integer> stack, int count) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        System.out.println("Use nano : " + (endTime - startTime) / 1000000000.0);
    }

    public static void main(String[] args) {
        Stack<Integer> arrayStack = new ArrayStack<>();
        Stack<Integer> linkedListStack = new LinkedListStack<>();

        test(arrayStack, 100000);
        test(linkedListStack, 100000);

        test(arrayStack, 1000000);
        test(linkedListStack, 1000000);

        test(arrayStack, 10000000);
        test(linkedListStack, 10000000);
    }
}
