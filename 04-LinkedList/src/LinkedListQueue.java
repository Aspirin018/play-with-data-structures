import queueof03module.ArrayQueue;
import queueof03module.LoopQueue;
import queueof03module.Queue;

import java.util.Random;

/**
 * 基于链表实现队列
 * 对链表来说因为有head头节点，从头节点添加元素和删除元素的复杂度都为O(1)
 * 另对链表设置tail尾节点，从尾节点添加元素的复杂度为O(1)
 * 删除元素的原理是先找到前一个节点，因此从尾节点删除元素的复杂度为O(n)
 * 基于链表实现队列时，适合从尾结点添加元素，从头节点删除元素
 * 因此头节点作为队首，尾结点作为队尾，队尾进，队首出
 *
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {

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
    private Node tail;
    private int size;

    /**
     * 没有虚拟头节点，因此head直接为null 而不是new Node(null,null)
     */
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        Node returnNode = head;
        head = head.next;
        returnNode.next = null;
        //针对原来对列中只有一个元素的场景，出队前tail指向唯一元素，出队后，head为null, tail也应该指向null
        if (head == null) {
            tail = null;
        }
        size--;
        return returnNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LinkedListQueue : Head ");
        Node cur = head;
        while (cur != null) {
            builder.append(cur + " -> ");
            cur = cur.next;
        }
        builder.append("Tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        //基本测试
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }

        //测试基于数组的队列、基于循环数组的队列、基于链表的队列的性能对比
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + " s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3 + " s");
    }

    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0; //单位秒
    }

}
