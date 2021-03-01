public class LinkedList<E> {

    /**
     * Node设计成私有的，外部不需要知道链表的底层数据结构
     */
    private class Node {
        //属性设计成public的，在LinkedList中可以直接访问Node的属性
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

    //属性私有 外部不可以直接访问底层结构
    //版本一：真实的头结点
//    private Node head;
    //版本二：设置虚拟头节点，用虚拟节点代替原始版本的头节点;
    // 虚拟头节点用于解决版本一中的向头节点添加元素的特殊情况，但是虚拟头节点不对用户暴露
    private Node dummyHead;
    private int size;

    //版本一
//    public LinkedList() {
//        head = null;
//        size = 0;
//    }

    //版本二
    public LinkedList() {
        //有了虚拟头节点，初始时，虚拟头节点本身时null， next也是null
        dummyHead = new Node(null, null);
        //虚拟节点不对用户暴露，实际size仍然是0
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //从链表头添加元素
//    public void addFirst(E e) {
//        //古早写法
////        Node newNode = new Node(e, head);
////        head = newNode;
//
//        //优雅写法
//        head = new Node(e, head);
//
//        size++;
//    }


    //版本一：使用真实的头节点
    // 在链表中间添加元素  实际场景中如果从设计上考虑用链表，一般都是从链表头添加元素
//    public void add(int index, E e) {
//        if (index < 0 || index > size) {
//            throw new IllegalArgumentException("Add failed. Illegal index.");
//        }
//        //在链表头添加元素和在其他位置添加元素有区别
//        //因为在其他位置添加元素是需要先找到prev节点，但是链表头没有prev
//        if (index == 0) {
//            addFirst(e);
//        } else {
//            Node prev = head;
//            for (int i = 0; i < index - 1; i++) {
//                prev = prev.next;
//            }
//            Node newNode = new Node(e);
//            newNode.next = prev.next;
//            prev.next = newNode;
//            size++;
//        }
//    }

    //版本二：使用虚拟头节点
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node newNode = new Node(e);
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    //版本二：
    public void addFirst(E e) {
        add(0, e);
    }


    //在链表尾添加元素
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 查找
     * 获取链表第index位置的元素
     * 并不常用，这里作为遍历链表的练习
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    /**
     * 设置链表第index位置的元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }

        Node current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.e = e;
    }

    /**
     * 版本一：查找链表是否包含某元素
     * 两种方式皆可
     * @param e
     * @return
     */
//    public boolean contains(E e) {
//        Node current = dummyHead.next;
//        for (int i = 0; i < size; i++) {
//            current = current.next;
//            if (e.equals(current.e)) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * 版本二：查找链表是否包含某元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node current = dummyHead.next;
        while (current != null) {
            if (e.equals(current.e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * toString
     * 也可以使用这种遍历方式
     * for (Node cur = dummyHead.next; cur != null ; cur = cur.next) {...}
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node current = dummyHead.next;
        while (current != null) {
            builder.append(current.toString() + " -> ");
            current = current.next;
        }
        builder.append("NULL");
        return builder.toString();
    }

    /**
     * remove
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node current = prev.next;
        prev.next = current.next;
        current.next = null;
        size--;
        return current.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }
}
