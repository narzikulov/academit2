package ru.academit.narzikulov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<E> implements Collection<E> {
    private ArrayList<E>[] hashTable;
    private final int HASH_TABLE_DIM = 100;
    private int size = 0;
    private int lastElementIndex = 0;

    public HashTable() {
        //noinspection unchecked
        this.hashTable = new ArrayList[HASH_TABLE_DIM];
    }

    public HashTable(int hTableSize) {
        if (hTableSize <= 0) {
            throw new IllegalArgumentException("Некорректно задана размерность хэш-таблицы");
        }
        //noinspection unchecked
        this.hashTable = new ArrayList[hTableSize];
    }

    public String toString() {
        if (lastElementIndex == 0) {
            return "{}";
        }
        StringBuilder s = new StringBuilder("{");
        for (int i = 0; i < lastElementIndex; i++) {
            s.append(hashTable[i]);
            s.append(", ");
        }
        s.append(hashTable[lastElementIndex]);
        s.append("}");
        return s.toString();
    }

    private int hashCode(Object element) {
        return Math.abs(element.hashCode() % hashTable.length);
    }

    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException("Unable add NULL element");
        }
        int curElementIndex = hashCode(element);
        if (hashTable[curElementIndex] == null) {
            hashTable[curElementIndex] = new ArrayList<>();
        }
        hashTable[curElementIndex].add(element);
        lastElementIndex = Math.max(lastElementIndex, curElementIndex);
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            throw new NullPointerException("Unable remove NULL element");
        }
        int curElementIndex = hashCode(element);
        if (hashTable[curElementIndex] != null) {
            hashTable[curElementIndex].remove(element);
            lastElementIndex = Math.max(lastElementIndex, curElementIndex);
            --size;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        boolean result = false;
        for (E element : collection) {
            if (this.add(element)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean result = false;
        for (Object element : collection) {
            if (this.remove(element)) {
                result = true;
            }
        }
        size = 0;
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean result = false;
        for (Object element : this) {
            if (!collection.contains(element)) {
                this.remove(element);
                result = true;
            }
        }
        return result;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        int arraySize = this.size();
        Object[] array = new Object[arraySize];
        if (this.isEmpty()) {
            return new Object[0];
        }
        int k = 0;
        for (int i = 0; i <= lastElementIndex; ++i) {
            if (hashTable[i] == null) {
                continue;
            }
            for (int j = 0; j < hashTable[i].size(); ++j) {
                array[k] = hashTable[i].get(j);
                ++k;
            }
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] array;

        if (a.length < this.lastElementIndex) {
            array = (T[]) new Object[this.size()];
        } else {
            array = a;
        }

        int arrayIndex = 0;
        for (E element : this) {
            array[arrayIndex] = (T) element;
            ++arrayIndex;
        }

        return array;
    }

    public boolean isEmpty() {
        return this.lastElementIndex == 0;
    }

    @Override
    public boolean contains(Object o) {
        int hashCode = hashCode(o);
        return this.hashTable[hashCode] != null && this.hashTable[hashCode].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentElementIndex = 0;

            @Override
            public boolean hasNext() {
                return currentElementIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                ++currentElementIndex;
                E currentElement = null;
                int k = 0;

                for (int i = 0; i <= lastElementIndex; ++i) {
                    if (hashTable[i] == null) {
                        continue;
                    }
                    for (int j = 0; j < hashTable[i].size(); ++j) {
                        if (currentElementIndex == k) {
                            currentElement = hashTable[i].get(j);
                            ++currentElementIndex;
                            break;
                        }
                        ++k;
                    }
                    if (currentElement != null) {
                        break;
                    }
                }

                return currentElement;
            }
        };
    }

    public void clear() {
        for (int i = 0; i < lastElementIndex; ++i) {
            if (hashTable[i] != null) {
                hashTable[i].clear();
            }
        }
        lastElementIndex = 0;
    }
}
