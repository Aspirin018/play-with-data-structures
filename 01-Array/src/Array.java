public class Array {

    private final int[] data;
    //    private int capacity;
    private int size;

    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public Array() {
//        data = new int[10];
//        size = 0;
        this(10);
    }

    public static void main(String[] args) {
        Array array = new Array();
        array.addLast(10);
        array.addLast(20);
        array.addLast(30);
        System.out.println(array.toString());
        array.add(1, 100);
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

    public void addLast(int e) {
        //V1
//        if (size == data.length) {
//            throw new IllegalArgumentException("AddLast failed. Array is already full.");
//        }
//        data[size] = e;
//        size++;

        //V2
        add(size, e);
    }

    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("Add failed. Array is already full.");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Index invalid.");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addFirst(int e) {
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

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index invalid.");
        }
        return data[index];
    }

    public void set(int index, int e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index invalid.");
        }
        data[index] = e;
    }

    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index invalid.");
        }
        int removed = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return removed;
    }

    public int removeFirst() {
        return remove(0);
    }

    public int removeLast() {
        return remove(size - 1);
    }

    public void removeElement(int e) {
        int index = this.find(e);
        if (index != -1) {
            remove(index);
        }
    }
}
