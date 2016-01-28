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
        StringBuilder s = new StringBuilder("{");
        for (int i = 0; i < lastElementIndex; i++) {
            s.append(hTable[i]);
            s.append(", ");
        }
        s.append(hTable[lastElementIndex]);
        s.append("}");
        return s.toString();
    }

    private int countHashCode(E element) {
        return Math.abs(element.hashCode() % HT_DIM);
    }

    public void addElement(E element) {
        if (element == null) {
            return;
        }
        int curElementIndex = countHashCode(element);
        if (hTable[curElementIndex] == null) {
            hTable[curElementIndex] = new ArrayList<E>();
        }
        hTable[curElementIndex].add(element);
        lastElementIndex = Math.max(lastElementIndex, curElementIndex);
    }

    public int countNumOfElements() {
        int num = 0;
        for (int i = 0; i < HT_DIM; ++i) {
            if (hTable[i] != null) {
                num += hTable[i].size();
            }
        }
        return num;
    }

    public boolean findElement(E element) {
        for (int i = 0; i < HT_DIM; ++i) {
            if (hTable[i] != null) {
                if (hTable[i].contains(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void delElement(E element) {
        if (element == null) {
            return;
        }
        int curElementIndex = countHashCode(element);
        if (hTable[curElementIndex] != null) {
            hTable[curElementIndex].remove(element);
        }
        lastElementIndex = Math.max(lastElementIndex, curElementIndex);
    }
}
