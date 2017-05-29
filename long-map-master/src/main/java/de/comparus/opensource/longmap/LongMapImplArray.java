package de.comparus.opensource.longmap;

public class LongMapImplArray<V> implements LongMap<V> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int RESIZE_FACTOR = 2;
    private long[] keys;
    private V[] values;
    private int size;

    public LongMapImplArray() {
        clear();
    }

    public V put(long key, V value) {
        int index = getKeyIndex(key);
        if (index == -1) {
            if (size == keys.length) {
                resizeArrays();
            }
            keys[size] = key;
            values[size] = value;
            size++;
        } else {
            values[index] = value;
        }
        return value;
    }

    public V get(long key) {
        int index = getKeyIndex(key);
        if (index != -1) {
            return (V) values[index];
        }
        return null;
    }

    public V remove(long key) {
        V value = null;
        int index = getKeyIndex(key);
        if (index != -1) {
            value = (V) values[index];
            removeByIndex(index);
        }
        return value;
    }

    private void removeByIndex(int index) {
        size--;
        final int tailSize = size - index;
        if (tailSize > 0) {
            System.arraycopy(keys, index + 1, keys, index, tailSize);
            System.arraycopy(values, index + 1, values, index, tailSize);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        return getKeyIndex(key) != -1;
    }

    public boolean containsValue(V value) {
        return getValueIndex(value) != -1;
    }

    public long[] keys() {
        long[] array = new long[size];
        System.arraycopy(keys, 0, array, 0, size);
        return array;
    }

    public V[] values() {
        V[] array = (V[]) new Object[size];
        System.arraycopy(values, 0, array, 0, size);
        return array;
    }

    public long size() {
        return size;
    }

    public void clear() {
        if (keys == null || INITIAL_CAPACITY != keys.length) {
            keys = new long[INITIAL_CAPACITY];
            values = (V[]) new Object[INITIAL_CAPACITY];
        }
        size = 0;
    }

    private int getKeyIndex(long key) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) {
                index = i;
                break;
            }
        }
        return index;
    }

    private int getValueIndex(V value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void resizeArrays() {
        int smallSize = keys.length;
        int largerSize = smallSize * RESIZE_FACTOR;
        long[] largerKeysArray = new long[largerSize];
        V[] largerValuesArray = (V[]) new Object[largerSize];
        System.arraycopy(keys, 0, largerKeysArray, 0, smallSize);
        System.arraycopy(values, 0, largerValuesArray, 0, smallSize);
        keys = largerKeysArray;
        values = largerValuesArray;
    }

}