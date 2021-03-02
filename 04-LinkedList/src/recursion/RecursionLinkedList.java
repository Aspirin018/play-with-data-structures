package recursion;

import javafx.util.Pair;

/**
 * 用递归实现链表的增删改查
 */
public class RecursionLinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public RecursionLinkedList(Node dummyHead, int size) {
        this.head = null;
        this.size = 0;
    }

    /**
     * 递归实现 增
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        head = addRecursion(head, index, e);
        size++;
    }

    private Node addRecursion(Node node, int index, E e) {
        if (index == 0) {
            return new Node(e, node);
        }
        node.next = addRecursion(node.next, index - 1, e);
        return node;
    }

    /**
     * 递归实现 删
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Pair<Node, E> pair = removeRecursion(head, index);
        head = pair.getKey();
        return pair.getValue();
    }

    //返回头节点和被删除元素的值
    private Pair<Node, E> removeRecursion(Node node, int index) {
        if (index == 0) {
            return new Pair<>(node.next, node.e);
        }
        Pair<Node, E> result = removeRecursion(node.next, index - 1);
        node.next = result.getKey();
        return new Pair<>(node, result.getValue());
    }

    /**
     * 递归实现 改
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        set(head, index, e);
    }

    private void set(Node node, int index, E e) {
        if (index == 0) {
            node.e = e;
            return; //别忘了return
        }
        set(node.next, index - 1, e);
    }

    /**
     * 递归实现 查
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        return getRecursion(head, index);
    }

    private E getRecursion(Node node, int index) {
        if (index == 0) {
            return node.e;
        }
        return getRecursion(node.next, index - 1);
    }

    /**
     * 递归实现 查
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return containsRecursion(head, e);
    }

    private boolean containsRecursion(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.equals(e)) {
            return true;
        }
        return containsRecursion(node.next, e);
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
