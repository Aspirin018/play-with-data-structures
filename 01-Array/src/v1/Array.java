package v1;

/**
 * 在Java数组的基础上二次封装动态数组
 */
public class Array {
    private int[] data;
    private int size; //元素个数

    //传入数组容量构造
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    //不传容量构造，使用默认容量10
    public Array() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //向数组末尾添加元素
    public void addLast(int item) {
        //原始实现方案
//        if (size >= data.length) {
//            throw new IllegalArgumentException("Add last item failed. v1.Array is full.");
//        }
//        data[size] = item;
//        size++;

        //实现了add方法后，调用add
        add(size, item);
    }

    //在第index位置插入元素
    public void add(int index, int item) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Required index >= 0 and <= size.");
        }
        if (size >= data.length) {
            throw new IllegalArgumentException("Add failed. Array is full.");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = item;
        size++;
    }

    //在数组首位添加元素
    public void addFirst(int item) {
        add(0, item);
    }

    //查找元素
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index id illegal.");
        }
        return data[index];
    }

    //替换元素
    public void set(int index, int item) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index id illegal.");
        }
        data[index] = item;
    }

    //是否包含某元素
    public boolean contains(int item) {
        for (int i = 0; i < size; i++) {
            if (data[i] == item) {
                return true;
            }
        }
        return false;
    }

    //查找某元素所在位置，如果不存在返回-1
    public int find(int item) {
        for (int i = 0; i < size; i++) {
            if (data[i] == item) {
                return i;
            }
        }
        return -1;
    }

    //删除指定索引的元素, 返回删除的元素
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index id illegal.");
        }
        int origin = data[index];
        //从index+1开始到size-1 向前移动， 注意原始数组最后一个元素扔保持原状，但是size--后永远访问不到这个值，保留即可
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return origin;
    }

    //快捷删除首元素
    public int removeFirst() {
        return remove(0);
    }

    //快捷删除尾元素
    public int removeLast() {
        return remove(size - 1);
    }

    //删除指定元素
    public void removeElement(int item) {
        int index = find(item);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("v1.Array : size = %d , capacity = %d\n", size, data.length));
        sb.append("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }
}