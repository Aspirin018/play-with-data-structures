public class Array<E> {

    private E[] data;
    //    private int capacity;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
//        data = new int[10];
//        size = 0;
        this(10);
    }

    public static void main(String[] args) {
        Array<Integer> array = new Array<>(3);
        array.addLast(10);
        array.addLast(20);
        array.addLast(30);
        System.out.println(array.toString());
        array.add(1, 100);
        System.out.println(array.toString());
        array.remove(1);
        System.out.println(array.toString());
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {
        //V1
//        if (size == data.length) {
//            throw new IllegalArgumentException("AddLast failed. Array is already full.");
//        }
//        data[size] = e;
//        size++;

        //V2
        add(size, e);
    }

    public void add(int index, E e) {
        //V1 before resize
//        if (size == data.length) {
//            throw new IllegalArgumentException("Add failed. Array is already full.");
//        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Index invalid.");
        }

        //V2 resize
        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void resize(int newCapacity) {
//        if (newCapacity <= data.length) {
//            throw new IllegalArgumentException("Resize failed. new capacity invalid.");
//        }
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("Array: size = %d, capacity = %d.\n", size, data.length));
        stringBuilder.append(" [ ");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i < size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" ] ");
        return stringBuilder.toString();
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index invalid.");
        }
        return data[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index invalid.");
        }
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index invalid.");
        }
        E removed = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        //V2 resize eager
//        if (size == data.length / 2) {
//            resize(data.length / 2);
//        }
        //V3 resize lazy
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return removed;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = this.find(e);
        if (index != -1) {
            remove(index);
        }
    }
}
