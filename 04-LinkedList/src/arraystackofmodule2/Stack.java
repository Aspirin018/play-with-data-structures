package arraystackofmodule2;

/**
 * 基于链表实现栈
 * 这里引入Stack接口
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
