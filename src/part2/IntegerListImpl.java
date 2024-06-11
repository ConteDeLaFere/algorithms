package part2;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private static final int INITIAL_CAPACITY = 10;
    private static final int FACTOR = 2;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    private Integer[] elements;

    public IntegerListImpl() {
        elements = new Integer[INITIAL_CAPACITY];
    }

    @Override
    public Integer add(Integer item) {
        if (size == capacity) {
            increaseCapacity();
        }
        elements[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            increaseCapacity();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Integer item = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        if (size * FACTOR < capacity) {
            reduceCapacity();
        }
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        for (Integer str : elements) {
            if (str.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullPointerException();
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        elements = new Integer[INITIAL_CAPACITY];
    }

    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    private void increaseCapacity() {
        capacity *= FACTOR;
        Integer[] newElements = new Integer[capacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void reduceCapacity() {
        capacity /= FACTOR;
        Integer[] newElements = new Integer[capacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void quicksort()
    {
        Arrays.sort(elements);
    }

    private Integer binarySearch(Integer item) {
        return Arrays.binarySearch(elements, item);
    }
}
