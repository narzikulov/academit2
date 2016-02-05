package ru.academit.narzikulov;

import java.util.ArrayList;

/**
 * Created by tim on 28.12.2015.
 */
public class HashTable<E> {
    private ArrayList<E>[] hTable;
    private final int HT_DIM = 100;
    private int lastElementIndex = 0;

    public HashTable() {
        this.hTable = new ArrayList[HT_DIM];
    }

    public HashTable(int hTableSize) {
        if (hTableSize <= 0) throw new IllegalArgumentException("Некорректно задана размерность хэш-таблицы");
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

    private int hashCode(E element) {
        return Math.abs(element.hashCode() % HT_DIM);
    }

    public void add(E element) {
        if (element == null) {
            return;
        }
        int curElementIndex = hashCode(element);
        if (hTable[curElementIndex] == null) {
            hTable[curElementIndex] = new ArrayList<E>();
        }
        hTable[curElementIndex].add(element);
        lastElementIndex = Math.max(lastElementIndex, curElementIndex);
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
            throw new ArrayIndexOutOfBoundsException("Список пуст");
        }
        int k = 0;
        for (int i = 0; i <= lastElementIndex; ++i) {
            if (hTable[i] == null) {
                continue;
            }
            for (int j = 0; j < hTable[i].size(); ++j) {
                if (hTable[i].get(j) == null) {
                    continue;
                }
                array[k] = hTable[i].get(j);
                ++k;
            }
        }
        return array;
    }

    public boolean isEmpty() {
        return this.lastElementIndex <= 0;
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

    public boolean contains(E element) {
        for (int i = 0; i < HT_DIM; ++i) {
            if (hTable[i] != null) {
                if (hTable[i].contains(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void remove(E element) {
        if (element == null) {
            return;
        }
        int curElementIndex = hashCode(element);
        if (hTable[curElementIndex] != null) {
            hTable[curElementIndex].remove(element);
        }
        lastElementIndex = Math.max(lastElementIndex, curElementIndex);
    }
}
