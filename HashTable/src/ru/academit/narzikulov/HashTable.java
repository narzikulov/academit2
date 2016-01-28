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
        this.hTable = new ArrayList[hTableSize];
    }

    public String toString() {
        StringBuilder s = new StringBuilder("{");
        for (int i = 0; i < lastElementIndex; i++) {
            E currentElement = (E) hTable[i];
            s.append(currentElement);
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
        if (element != null) {
            int curElementIndex = countHashCode(element);
            if (hTable[curElementIndex] == null) {
                hTable[curElementIndex] = new ArrayList<E>();
                hTable[curElementIndex].add(element);
            } else {
                hTable[curElementIndex].add(element);
            }
            lastElementIndex = Math.max(lastElementIndex, curElementIndex);
        }
    }

    public int countNumOfElements() {
        int num = 0;
        for (int i = 0; i < HT_DIM; ++i) {
            if (hTable[i] != null) {
                for (int j = 0; j < hTable[i].size(); ++j) {
                    if (hTable[i].get(j) != null) {
                        ++num;
                    }
                }
            }
        }
        return num;
    }

    public boolean findElement(E element) {
        boolean elementIsInTable = false;
        for (int i = 0; i < HT_DIM; ++i) {
            if (hTable[i] != null) {
                if (hTable[i].contains(element)) {
                    elementIsInTable = true;
                }
            }
        }
        return elementIsInTable;
    }

    public void delElement(E element) {
        if (element != null) {
            int curElementIndex = countHashCode(element);
            hTable[curElementIndex].remove(element);
            lastElementIndex = Math.max(lastElementIndex, curElementIndex);
        }
    }
}
