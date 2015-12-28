package ru.academit.narzikulov;

import java.util.ArrayList;
import java.util.Vector;

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
        for (int i = 0; i < lastElementIndex; ++i) {
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

    public boolean equals(Object element) {
        if (element == null) {
            return false;
        }

        E comparedElement = (E) element;

        if (comparedElement != element) {
            return false;
        }


        return true;
    }

    public boolean findElement(E element) {
        boolean elementIsInTable = false;
        for (int i = 0; i < lastElementIndex; ++i) {
            if (hTable[i] != null) {
                for (int j = 0; j < hTable[i].size(); ++j) {
                    if (hTable[i].get(j).equals(element))  {
                        elementIsInTable = true;
                    }
                }
            }
        }
        return elementIsInTable;
    }

}
