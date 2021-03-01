package queueof03module;


public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity) {
        //设计上有意浪费数组的一个位置，这里实际数组的长度比传入参数（应用层期望）多1
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        //将旧的data中的元素迁移至新的newData中时，即便旧data front不等于0，也将其迁移至newData的首位
        //即newData[0] = data[0+front]
        //但是旧data中元素也是循环排列的，想象超过data.length的元素排在前面
        //因此准确的newData[i] = data[(i + front) % data.length]
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size; //因为从队列头开始放元素，这里tail不用考虑取余
    }

    @Override
    public void enqueue(E e) {
        //队列满的条件
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length; //新的tail值要考虑取余
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E returnElement = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        //getCapacity() / 2 != 0的判断保证缩容后的size不为0
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return returnElement;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        //实际持有的元素数量
        return size;
    }

    public int getCapacity() {
        //数组有一个位置浪费，实际容量比数组长度小1
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        //front=tail时表示队列为空
        return front == tail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue : size = %d , capacity = %d\n", size, getCapacity()));
        sb.append("Front [ ");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) {
                sb.append(", ");
            }
        }
        sb.append(" ] tail");
        return sb.toString();
    }
}
