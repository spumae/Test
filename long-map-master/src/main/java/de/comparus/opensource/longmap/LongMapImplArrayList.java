package de.comparus.opensource.longmap;

import java.util.ArrayList;

public class LongMapImplArrayList<V> implements LongMap<V> {

    private final ArrayList<Long> keys;
    private final ArrayList<V> values;
    private int size = 0;

    public LongMapImplArrayList() {
        keys = new ArrayList();
        values = new ArrayList();
    }

    public V put(long key, V value) {
        int index = getIndex(key);
        if (index == -1) {
            keys.add(size, key);
            values.add(size, value);
            size++;
        } else {
            values.set(index, value);
        }
        return value;
    }

    public V get(long key) {
        int index = getIndex(key);
        if (index != -1) {
            return values.get(index);
        }
        return null;
    }

    public V remove(long key) {
        int index = getIndex(key);
        keys.remove(index);
        size--;
        return values.remove(index);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        return keys.contains(key);
    }

    public boolean containsValue(V value) {
        return values.contains(value);
    }

    public long[] keys() {
        long[] keysArray = new long[size];
        for (int i = 0; i < size; i++) {
            keysArray[i] = keys.get(i);
        }
        return keysArray;
    }

    public V[] values() {
        return (V[]) values.toArray();
    }

    public long size() {
        return size;
    }

    public void clear() {
        keys.clear();
        values.clear();
        size = 0;
    }

    private int getIndex(long key) {
        return keys.indexOf(key);
    }
}