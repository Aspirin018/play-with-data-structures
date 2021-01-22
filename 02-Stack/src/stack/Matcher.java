package stack;

import java.util.Stack;

public class Matcher {

    public static boolean matcher(String str) {
        if (str.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character top = stack.pop();
                if (')' == c && '(' != top) {
                    return false;
                }
                if (']' == c && '[' != top) {
                    return false;
                }
                if ('}' == c && '{' != top) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(matcher("{([])}"));
    }
}
