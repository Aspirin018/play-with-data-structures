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
    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //从链表头添加元素
    public void addFirst(E e) {
        //古早写法
//        Node newNode = new Node(e, head);
//        head = newNode;

        //优雅写法
        head = new Node(e, head);

        size++;
    }

    //在链表中间添加元素  实际场景中如果从设计上考虑用链表，一般都是从链表头添加元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        if (index == 0) {
            addFirst(e);
        } else {
            Node prev = head;
            for (int i = 0; i < index-1; i++) {
                prev = prev.next;
            }
            Node newNode = new Node(e);
            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }
    }

    //在链表尾添加元素
    public void addLast(E e){
        add(size, e);
    }


}
