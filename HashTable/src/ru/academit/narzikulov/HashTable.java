package ru.academit.narzikulov;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private ArrayList<E>[] hashTable;
    private final static int HASH_TABLE_DIM = 100;
    private int size = 0;
    private int lastElementIndex = 0;
    private long version;

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
        ++version;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            throw new NullPointerException("Unable remove NULL element");
        }

        int curElementIndex = hashCode(element);
        if (hashTable[curElementIndex].remove(element)) {
            --size;
            ++version;
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
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean result = false;
        Object[] thisCollection = this.toArray();
        for (Object element : thisCollection) {
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
        if (arraySize == 0) {
            return array;
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

        if (a.length < this.size) {
            array = (T[]) new Object[this.size];
        } else {
            array = a;
        }

        int arrayIndex = 0;
        for (E element : this) {
            array[arrayIndex] = (T) element;
            ++arrayIndex;
        }

        if (array.length > this.size) {
            array[this.size] = null;
        }

        return array;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int hashCode = hashCode(o);
        return this.hashTable[hashCode] != null && this.hashTable[hashCode].contains(o);
    }

    public void clear() {
        for (int i = 0; i < lastElementIndex; ++i) {
            if (hashTable[i] != null) {
                hashTable[i].clear();
            }
        }
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentListElementIndex;
        private int currentArrayIndex;
        private int returnedElementsCounter;
        private long hashTableVersion;

        public HashTableIterator() {
            hashTableVersion = version;
        }

        @Override
        public boolean hasNext() {
            return returnedElementsCounter < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (hashTableVersion != version) {
                throw new ConcurrentModificationException();
            }

            E element = null;

            for (int i = currentArrayIndex; i < hashTable.length; ++i) {
                ArrayList<E> list = hashTable[i];
                if (list == null || list.isEmpty()) {
                    continue;
                }

                element = list.get(currentListElementIndex);
                ++currentListElementIndex;
                if (currentListElementIndex >= list.size()) {
                    currentListElementIndex = 0;
                    ++i;
                }
                ++returnedElementsCounter;
                currentArrayIndex = i;
                break;
            }

            return element;
        }
    }
}
