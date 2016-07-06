package ru.academit.narzikulov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HashTable<E> implements Collection<E> {
    private ArrayList<E>[] hTable;
    private final int HT_DIM = 100;
    private int lastElementIndex = 0;

    public HashTable() {
        //noinspection unchecked
        this.hTable = new ArrayList[HT_DIM];
    }

    public HashTable(int hTableSize) {
        if (hTableSize <= 0) {
            throw new IllegalArgumentException("Некорректно задана размерность хэш-таблицы");
        }
        //noinspection unchecked
        this.hTable = new ArrayList[hTableSize];
    }

    public String toString() {
        if (lastElementIndex == 0) return "{empty}";
        StringBuilder s = new StringBuilder("{");
        for (int i = 0; i < lastElementIndex; i++) {
            s.append(hTable[i]);
            s.append(", ");
        }
        s.append(hTable[lastElementIndex]);
        s.append("}");
        return s.toString();
    }

    private int hashCode(Object element) {
        return Math.abs(element.hashCode() % HT_DIM);
    }

    public boolean add(E element) {
        if (element == null) {
            return false;
        }
        int curElementIndex = hashCode(element);
        if (hTable[curElementIndex] == null) {
            hTable[curElementIndex] = new ArrayList<E>();
        }
        hTable[curElementIndex].add(element);
        lastElementIndex = Math.max(lastElementIndex, curElementIndex);
        return false;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            return false;
        }
        int curElementIndex = hashCode(element);
        if (hTable[curElementIndex] != null) {
            hTable[curElementIndex].remove(element);
            return true;
        }
        lastElementIndex = Math.max(lastElementIndex, curElementIndex);
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] collection = c.toArray();
        for (Object aCollection : collection) {
            if (!this.contains(aCollection)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (Object aCollection : c) {
            if (this.add((E) aCollection)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        Object[] collection = c.toArray();
        for (Object aCollection : collection) {
            if (this.contains(aCollection)) {
                this.remove(aCollection);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        Object[] collection = c.toArray();
        for (Object aCollection : collection) {
            if (!this.contains(aCollection)) {
                this.remove(aCollection);
                result = true;
            }
        }
        return result;
    }

    public int size() {
        int num = 0;
        for (int i = 0; i < HT_DIM; ++i) {
            if (hTable[i] != null) {
                num += hTable[i].size();
            }
        }
        return num;
    }

    public Object[] toArray() {
        int arraySize = this.size();
        Object[] array = new Object[arraySize];
        if (this.isEmpty()) {
            return new Object[0];
        }
        int k = 0;
        for (int i = 0; i <= lastElementIndex; ++i) {
            if (hTable[i] == null) {
                continue;
            }
            for (int j = 0; j < hTable[i].size(); ++j) {
                /*if (hTable[i].get(j) == null) {
                    continue;
                }*/
                array[k] = hTable[i].get(j);
                ++k;
            }
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean isEmpty() {
        return this.lastElementIndex <= 0;
    }

    @Override
    public boolean contains(Object o) {
        int hashCode = hashCode(o);
        return this.hTable[hashCode] != null && this.hTable[hashCode].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public void clear() {
        for (int i = 0; i < lastElementIndex; ++i) {
            if (hTable[i] == null) {
                continue;
            }
            int hTableCurElementSize = hTable[i].size();
            for (int j = 0; j < hTableCurElementSize; ++j) {
                hTable[i].remove(j);
            }
        }
        lastElementIndex = 0;
    }
}
