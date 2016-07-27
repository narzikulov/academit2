package ru.academit.narzikulov;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by tim on 28.12.2015.
 */
public class HashTableMain {
    public static void main() {
        HashTable<Object> hTable = new HashTable<>();

        hTable.add(-5);
        hTable.add("two");
        hTable.add("three");
        hTable.add(4);
        hTable.add(5);
        hTable.add(23);
        hTable.add("two");
        hTable.add("three");
        hTable.add("three");
        hTable.add(34);
        hTable.add("three");
        hTable.add("two");
        hTable.add(1);
        hTable.add(34);
        hTable.add("Hello world!");

        System.out.println(hTable.toString());
        System.out.println("Num of elements in Hash Table: " + hTable.size());

        ArrayList<Object> retainCollection = new ArrayList<>();
        retainCollection.add("three");
        retainCollection.add("two");
        retainCollection.add(34);
        hTable.retainAll(retainCollection);
        System.out.println("retain collection:");
        System.out.println(retainCollection.toString());
        System.out.println(hTable.toString());
        System.out.println();

        System.out.println(hTable.contains(5));
        System.out.println(hTable.contains("5"));
        hTable.remove(5);
        hTable.remove("two");
        System.out.println("Deleted elements: " + "5" + " and " + "two");
        System.out.println(hTable.toString());
        System.out.println("Num of elements in Hash Table: " + hTable.size());

        //hTable.clear();
        System.out.println(hTable.toString());
        System.out.println(hTable.isEmpty());

        Object[] array = hTable.toArray();
        for (Object anArray : array) System.out.print(anArray + "; ");

        System.out.println();
        System.out.println("Iterator");
        Iterator iterator = hTable.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.println(element.toString());
        }
    }
}
