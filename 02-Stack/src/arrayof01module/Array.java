package arrayof01module;

/**
 * 在Java数组的基础上二次封装动态数组 在上一版本基础上使用泛型
 */
public class Array<E> {
    private E[] data;
    private int size; //元素个数

    //传入数组容量构造
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
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
    public void addLast(E e) {
        //原始实现方案
//        if (size >= data.length) {
//            throw new IllegalArgumentException("Add last e failed. v1.Array is full.");
//        }
//        data[size] = e;
//        size++;

        //实现了add方法后，调用add
        add(size, e);
    }

    //在第index位置插入元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Required index >= 0 and <= size.");
        }
        if (size == data.length) {
//            throw new IllegalArgumentException("Add failed. Array is full.");
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    //在数组首位添加元素
    public void addFirst(E e) {
        add(0, e);
    }

    //查找元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index id illegal.");
        }
        return data[index];
    }

    //快捷方法
    public E getFirst() {
        return get(0);
    }

    //快捷方法
    public E getLast() {
        return get(size - 1);
    }

    //替换元素
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index id illegal.");
        }
        data[index] = e;
    }

    //是否包含某元素
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    //查找某元素所在位置，如果不存在返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    //删除指定索引的元素, 返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index id illegal.");
        }
        E origin = data[index];
        //从index+1开始到size-1 向前移动， 注意原始数组最后一个元素扔保持原状，但是size--后永远访问不到这个值，保留即可
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //为了避免永远无法访问到的引用类型元素（即原数组中最后一个元素）占用空间，将其置null
        //这种不会被访问到的元素因为引用存在不能被垃圾回收，叫做loitering objects闲散对象
        data[size] = null;

//        //缩容
//        if (size == data.length / 2) {
//            resize(data.length / 2);
//        }

        //缩容  防止复杂度震荡
        //当data.lenght=1时，data.length/2=0；不能讲数组resize为0， 因此添加一个条件
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return origin;
    }

    //快捷删除首元素
    public E removeFirst() {
        return remove(0);
    }

    //快捷删除尾元素
    public E removeLast() {
        return remove(size - 1);
    }

    //删除指定元素
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    //resize
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array : size = %d , capacity = %d\n", size, data.length));
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