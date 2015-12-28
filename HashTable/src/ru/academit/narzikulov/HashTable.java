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

    public int getLastIndex() {
        return lastElementIndex;
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

    public void addElement(E element) {
        if (element != null) {
            hTable[lastElementIndex].add(lastElementIndex, element);
            System.out.println(hTable[lastElementIndex]);
            ++lastElementIndex;
        }
    }
}
