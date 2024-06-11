package part1;

public class StringListImpl implements StringList {
    private static final int INITIAL_CAPACITY = 10;
    private static final int FACTOR = 2;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    private String[] elements;

    public StringListImpl() {
        elements = new String[INITIAL_CAPACITY];
    }

    @Override
    public String add(String item) {
        if (size == capacity) {
            increaseCapacity();
        }
        elements[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
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
    public String set(int index, String item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index] = item;
    }

    @Override
    public String remove(String item) {
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        String item = elements[index];
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
    public boolean contains(String item) {
        for (String str : elements) {
            if (str.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
        elements = new String[INITIAL_CAPACITY];
    }

    @Override
    public String[] toArray() {
        String[] array = new String[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    private void increaseCapacity() {
        capacity *= FACTOR;
        String[] newElements = new String[capacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void reduceCapacity() {
        capacity /= FACTOR;
        String[] newElements = new String[capacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
